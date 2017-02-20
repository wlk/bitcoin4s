package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.http.scaladsl.model._
import spray.json._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer

import scala.concurrent.{ExecutionContext, Future}
import akka.http.scaladsl.model.MediaTypes.`application/json`

class BitcoinTestClient(user: String, password: String, host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer, ec: ExecutionContext) extends HttpClient(user, password, host, port) {
  val User = "user"
  val Password = "password"
  val Host = "localhost"
  val Port = 8332

  private def extractMethod(entityJson: JsObject): String = {
    entityJson.fields("method") match {
      case JsString(m) => m.toString
      case other       => deserializationError(s"expected method as String but got: $other")
    }
  }

  private def loadJsonReponseFromTestData(method: String): JsValue = {
    method match {
      case "getwalletinfo"     => TestData.walletInfoResponse
      case "getnetworkinfo"    => TestData.networkInfoResponse
      case "getmininginfo"     => TestData.miningInfoResponse
      case "getmempoolinfo"    => TestData.memPoolInfoResponse
      case "getblockchaininfo" => TestData.blockchainInfoResponse
      case "estimatefee"       => TestData.estimateFeeResponse
      case "listunspent"       => TestData.listUnspentResponse
      case "listaccounts"      => TestData.listAccountsResponse
      case "generate"          => TestData.generateResponse
      case _                   => JsNumber(-1)
    }
  }

  override def performRequest(request: HttpRequest): Future[HttpResponse] = {
    val entityJson = Unmarshal(request).to[String].map { r =>
      r.parseJson.asJsObject
    }

    entityJson
      .map(extractMethod)
      .map(loadJsonReponseFromTestData)
      .map(response => HttpResponse(entity = HttpEntity(`application/json`, response.prettyPrint)))
  }
}
