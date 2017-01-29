package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.language.postfixOps
import akka.http.scaladsl.model._
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.BasicHttpCredentials
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import spray.json._

import scala.concurrent.duration._

class Client(user: String, password: String, host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer) {
  val connectionString = s"http://$host:$port/"

  private def httpRequest(body: String) = {
    val authorization = headers.Authorization(BasicHttpCredentials(user, password))

    HttpRequest(
      uri = connectionString,
      method = HttpMethods.POST,
      entity = HttpEntity(
        """
          |{ "method": "getwalletinfo" }
        """.stripMargin
      ),
      headers = List(authorization)
    )
  }

  private def performRequest(request: HttpRequest): Future[HttpResponse] = {
    Http().singleRequest(request)
  }

  private def unmarshalResponse(httpResponse: HttpResponse)(implicit executionContext: ExecutionContext): Future[JsValue] = {
    Unmarshal(httpResponse).to[String].map(_.parseJson)
  }

  def walletInfo: GetWalletInfo = {
    val request = httpRequest("")
    val response = performRequest(request)
    val result = Await.result(response, 10 seconds)
    println(result)
    null
  }

}
