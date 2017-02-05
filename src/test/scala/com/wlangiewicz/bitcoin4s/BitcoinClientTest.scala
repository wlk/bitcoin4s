package com.wlangiewicz.bitcoin4s

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.ScalaFutures
import scala.concurrent.ExecutionContext.Implicits.global

class BitcoinClientTest extends FlatSpec with Matchers with ScalaFutures {
  implicit val system = ActorSystem("unit-tests")
  implicit val materializer = ActorMaterializer()

  val testHttpClient = new BitcoinTestClient("user", "password", "localhost", 1337)
  val bitcoinClient = new BitcoinClient(testHttpClient)

  it should "return walletinfo" in {
    whenReady(bitcoinClient.walletInfo) { walletInfo =>
      walletInfo.balance shouldBe BigDecimal("1.65751751")
    }
  }

}
