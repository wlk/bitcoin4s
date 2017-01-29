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

  private def performRequest(request: HttpRequest): Future[HttpResponse] = {
    Http().singleRequest(request)
  }

  private def unmarshalResponse[T](httpResponse: HttpResponse)(implicit executionContext: ExecutionContext, reader: JsonReader[T]): Future[T] = {
    // TODO: Error handling
    Unmarshal(httpResponse).to[String].map(_.parseJson.asJsObject.getFields("result").head.asJsObject.convertTo[T])
  }

  def walletInfo(implicit executionContext: ExecutionContext): Future[GetWalletInfo] = {
    val request = httpRequest("getwalletinfo")
    val response = performRequest(request)
    response.flatMap(unmarshalResponse[GetWalletInfo])
  }
}
