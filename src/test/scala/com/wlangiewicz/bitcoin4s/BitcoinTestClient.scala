package com.wlangiewicz.bitcoin4s

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}

import scala.concurrent.Future

class BitcoinTestClient(user: String, password: String, host: String, port: Int) extends HttpClient(user, password, host, port) {
  val User = "user"
  val Password = "password"
  val Host = "localhost"
  val Port = 8332

  override def performRequest(request: HttpRequest): Future[HttpResponse] = {
    null
  }
}
