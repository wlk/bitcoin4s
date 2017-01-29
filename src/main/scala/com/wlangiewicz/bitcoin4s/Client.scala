package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps
import akka.http.scaladsl.model._
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import spray.json._

class Client(user: String, password: String, host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer) extends JsonFormats {
  val connectionString = s"http://$host:$port/"
  val authorization = headers.Authorization(BasicHttpCredentials(user, password))

  private def httpRequest(method: String) = {
    HttpRequest(
      uri = connectionString,
      method = HttpMethods.POST,
      entity = HttpEntity(
        s"""
          |{ "method": "$method" }
        """.stripMargin
      ),
      headers = List(authorization)
    )
  }

  private def httpRequestIntParams(method: String, params: Vector[Int]) = {
    HttpRequest(
      uri = connectionString,
      method = HttpMethods.POST,
      entity = HttpEntity(
        s"""
          |{
          | "method": "$method",
          | "params": [${params.mkString(",")}]
          |}
        """.stripMargin
      ),
      headers = List(authorization)
    )
  }

  private def httpRequestStringParams(method: String, params: Vector[String]) = {
    val formattedParams = params.map(p => "\"" + p + "\"")

    HttpRequest(
      uri = connectionString,
      method = HttpMethods.POST,
      entity = HttpEntity(
        s"""
           |{
           | "method": "$method",
           | "params": [${formattedParams.mkString(",")}]
           |}
        """.stripMargin
      ),
      headers = List(authorization)
    )
  }

  private def performRequest(request: HttpRequest): Future[HttpResponse] = {
    Http().singleRequest(request)
  }

  private def unmarshalResponse[T](httpResponse: HttpResponse)(implicit executionContext: ExecutionContext, reader: JsonReader[T]): Future[T] = {
    // TODO: Error handling
    Unmarshal(httpResponse).to[String].map { r =>
      println(r)
      r.parseJson.asJsObject.getFields("result").head.convertTo[T]
    }
  }

  def walletInfo(implicit executionContext: ExecutionContext): Future[GetWalletInfo] = {
    val request = httpRequest("getwalletinfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetWalletInfo])
  }

  def networkInfo(implicit executionContext: ExecutionContext): Future[GetNetworkInfo] = {
    val request = httpRequest("getnetworkinfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetNetworkInfo])
  }

  def miningInfo(implicit executionContext: ExecutionContext): Future[GetMiningInfo] = {
    val request = httpRequest("getmininginfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetMiningInfo])
  }

  def memPoolInfo(implicit executionContext: ExecutionContext): Future[GetMemPoolInfo] = {
    val request = httpRequest("getmempoolinfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetMemPoolInfo])
  }

  def blockchainInfo(implicit executionContext: ExecutionContext): Future[GetBlockChainInfo] = {
    val request = httpRequest("getblockchaininfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetBlockChainInfo])
  }

  def estimateFee(implicit executionContext: ExecutionContext): Future[EstimateFee] = {
    val request = httpRequestIntParams("estimatefee", Vector(6))
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[EstimateFee])
  }

  def listUnspentTransactions(implicit executionContext: ExecutionContext): Future[Vector[UnspentTransaction]] = {
    val request = httpRequest("listunspent")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[Vector[UnspentTransaction]])
  }

  def listAccounts(implicit executionContext: ExecutionContext): Future[Vector[Account]] = {
    val request = httpRequest("listaccounts")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[Vector[Account]])
  }

  def getNewAddress(account: String)(implicit executionContext: ExecutionContext): Future[GetNewAddress] = {
    val request = httpRequestStringParams("getnewaddress", Vector(account))
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetNewAddress])
  }
}
