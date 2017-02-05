package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.{FlatSpec, Matchers}

class BitcoinClientTest extends FlatSpec with Matchers {
  implicit val system = ActorSystem("unit-tests")
  implicit val materializer = ActorMaterializer()

  it should "a" in {
    val testHttpClient = new BitcoinTestClient("user", "password", "localhost", 1337)
    val bitcoinClient = new BitcoinClient(testHttpClient)
    1 shouldBe 2
  }

}
