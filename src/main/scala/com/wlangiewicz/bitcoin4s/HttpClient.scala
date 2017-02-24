package com.wlangiewicz.bitcoin4s

import akka.http.scaladsl.model._
import akka.http.scaladsl.model.headers.BasicHttpCredentials

import scala.concurrent.Future
import scala.language.postfixOps

abstract class HttpClient(val user: String, val password: String, val host: String, val port: Int) {
  val connectionUri = s"http://$host:$port/"
  val authorization = headers.Authorization(BasicHttpCredentials(user, password))

  def httpRequest(method: String): HttpRequest = {
    HttpRequest(
      uri = connectionUri,
      method = HttpMethods.POST,
      entity = HttpEntity(
        s"""
           |{ "method": "$method" }
        """.stripMargin
      ),
      headers = List(authorization)
    )
  }

  def httpRequestWithParams(method: String, params: Vector[Any]): HttpRequest = {
    val formattedParams: Vector[String] = params.map {
      case param: Int        => param.toString
      case param: BigDecimal => param.toString
      case param: String     => "\"" + param + "\""
    }

    HttpRequest(
      uri = connectionUri,
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

  def performRequest(request: HttpRequest): Future[HttpResponse]
}

