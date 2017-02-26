package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import scala.concurrent.Future

class AkkaHttpClient(user: String, password: String, host: String, port: Int)(implicit system: ActorSystem, materializer: ActorMaterializer)
    extends HttpClient(user, password, host, port) {
  override def performRequest(request: HttpRequest): Future[HttpResponse] = {
    Http().singleRequest(request)
  }
}
