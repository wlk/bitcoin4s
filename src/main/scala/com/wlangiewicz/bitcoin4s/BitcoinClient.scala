package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import spray.json._

class BitcoinClient(httpClient: HttpClient)(implicit system: ActorSystem, materializer: ActorMaterializer) extends JsonFormats {

  def this(user: String, password: String, host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer) {
    this(new AkkaHttpClient(user, password, host, port))
  }

  private def unmarshalResponse[T](httpResponse: HttpResponse)(implicit executionContext: ExecutionContext, reader: JsonReader[T]): Future[T] = {
    Unmarshal(httpResponse).to[String].map { r =>
      r.parseJson.asJsObject.getFields("result").head.convertTo[T]
    }
  }

  def walletInfo(implicit executionContext: ExecutionContext): Future[GetWalletInfo] = {
    val request = httpClient.httpRequest("getwalletinfo")
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetWalletInfo])
  }

  def networkInfo(implicit executionContext: ExecutionContext): Future[GetNetworkInfo] = {
    val request = httpClient.httpRequest("getnetworkinfo")
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetNetworkInfo])
  }

  def miningInfo(implicit executionContext: ExecutionContext): Future[GetMiningInfo] = {
    val request = httpClient.httpRequest("getmininginfo")
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetMiningInfo])
  }

  def memPoolInfo(implicit executionContext: ExecutionContext): Future[GetMemPoolInfo] = {
    val request = httpClient.httpRequest("getmempoolinfo")
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetMemPoolInfo])
  }

  def blockchainInfo(implicit executionContext: ExecutionContext): Future[GetBlockChainInfo] = {
    val request = httpClient.httpRequest("getblockchaininfo")
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetBlockChainInfo])
  }

  def estimateFee(blocks: Option[Int] = None)(implicit executionContext: ExecutionContext): Future[EstimateFee] = {
    val request = httpClient.httpRequestWithParams("estimatefee", Vector(blocks.getOrElse(6)))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[EstimateFee])
  }

  def listUnspentTransactions(minimumConfirmations: Option[Int] = None, maximumConfirmations: Option[Int] = None)(implicit executionContext: ExecutionContext): Future[Vector[UnspentTransaction]] = {
    val request = httpClient.httpRequestWithParams("listunspent", Vector(minimumConfirmations.getOrElse(0), maximumConfirmations.getOrElse(0)))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[Vector[UnspentTransaction]])
  }

  def listAccounts(confirmations: Option[Int] = None)(implicit executionContext: ExecutionContext): Future[Vector[Account]] = {
    val request = httpClient.httpRequestWithParams("listaccounts", Vector(confirmations.getOrElse(0)))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[Vector[Account]])
  }

  def getNewAddress(account: String)(implicit executionContext: ExecutionContext): Future[GetNewAddress] = {
    val request = httpClient.httpRequestWithParams("getnewaddress", Vector(account))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[GetNewAddress])
  }

  def sendFrom(account: String, to: String, amount: BigDecimal, confirmations: Option[Int])(implicit executionContext: ExecutionContext): Future[SentTransactionId] = {
    val request = httpClient.httpRequestWithParams("sendfrom", Vector(account, to, amount, confirmations.getOrElse(0)))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[SentTransactionId])
  }

  def generate(number: Int)(implicit executionContext: ExecutionContext): Future[HeaderHashes] = {
    val request = httpClient.httpRequestWithParams("generate", Vector(number))
    val response = httpClient.performRequest(request)
    response.flatMap(unmarshalResponse[HeaderHashes])
  }
}
