package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

object Main extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val client = new Client("user", "password", "localhost", 18332)

  val walletInfo = client.walletInfo

  val a = Await.result(walletInfo, 10 seconds)
  println(a)

  system.terminate()

}
