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

  private def extractMethod(entityJson: JsObject): (String, Vector[String]) = {
    val method = entityJson.fields("method") match {
      case JsString(m) => m.toString
      case other       => deserializationError(s"expected method as String but got: $other")
    }

    val params = entityJson.fields.get("params").map {
      case JsArray(values) => values.map {
        case JsString(s) => s
        case JsNumber(n) => n.toString
        case other       => deserializationError(s"expected JsArray to be String but got: $other")
      }
      case other => deserializationError(s"expected params as JsArray but got: $other")
    }

    (method, params.getOrElse(Vector.empty[String]))
  }

  private def loadJsonResponseFromTestData(arg: (String, Vector[String])): JsValue = {
    arg match {
      case (method, params) =>
        method match {
          case _ if params.contains("parseError") => TestData.parseErrorResponse
          case "getwalletinfo" => TestData.walletInfoResponse
          case "getnetworkinfo" => TestData.networkInfoResponse
          case "getmininginfo" => TestData.miningInfoResponse
          case "getmempoolinfo" => TestData.memPoolInfoResponse
          case "getblockchaininfo" => TestData.blockchainInfoResponse
          case "estimatefee" => TestData.estimateFeeResponse
          case "listunspent" => TestData.listUnspentResponse
          case "listaccounts" => TestData.listAccountsResponse
          case "getnewaddress" => TestData.getNewAddressResponse
          case "generate" => TestData.generateResponse
          case "sendfrom" if params.contains("insufficientFunds") => TestData.insufficientFundsResponse
          case "sendfrom" => TestData.sendFromResponse
          case "sendtoaddress" if params(1).toDouble > 100 => TestData.insufficientFundsResponse
          case "sendtoaddress" => TestData.sendToAddressResponse
          case "settxfee" if params(0).toDouble < 0 => TestData.setTxFeeOutOfRangeResponse
          case "settxfee" => TestData.setTxFeeResponse
          case _ => JsNumber(-1)
        }
    }
  }

  override def performRequest(request: HttpRequest): Future[HttpResponse] = {
    val entityJson = Unmarshal(request).to[String].map { r =>
      r.parseJson.asJsObject
    }

    entityJson
      .map(extractMethod)
      .map(loadJsonResponseFromTestData)
      .map(response => HttpResponse(entity = HttpEntity(`application/json`, response.prettyPrint)))
  }
}
