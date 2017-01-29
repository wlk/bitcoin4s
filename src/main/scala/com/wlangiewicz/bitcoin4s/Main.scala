package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val client = new Client("user", "password", "localhost", 18332)

  client.walletInfo

  system.terminate()

}
