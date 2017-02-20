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

  it should "generate blocks" in {
    whenReady(bitcoinClient.generate(2)) { generated =>
      generated should contain theSameElementsAs Seq(
        "36252b5852a5921bdfca8701f936b39edeb1f8c39fffe73b0d8437921401f9af",
        "5f2956817db1e386759aa5794285977c70596b39ea093b9eab0aa4ba8cd50c06"
      )
    }
  }

  it should "return networkinfo" in {
    whenReady(bitcoinClient.networkInfo) { networkInfo =>
      networkInfo.connections shouldBe 8
    }
  }

  it should "return mininginfo" in {
    whenReady(bitcoinClient.miningInfo) { miningInfo =>
      miningInfo.blocks shouldBe 1089632
    }
  }

  it should "return memPoolInfo" in {
    whenReady(bitcoinClient.memPoolInfo) { memPoolInfo =>
      memPoolInfo.size shouldBe 4
    }
  }

  it should "return blockchainInfo" in {
    whenReady(bitcoinClient.blockchainInfo) { blockchainInfo =>
      blockchainInfo.chain shouldBe "test"
    }
  }

  it should "estimate fee" in {
    whenReady(bitcoinClient.estimateFee(Some(6))) { fee =>
      fee.estimate shouldBe BigDecimal("0.00010244")
    }
  }

  it should "return unspent transactions" in {
    whenReady(bitcoinClient.listUnspentTransactions(minimumConfirmations = Some(0), maximumConfirmations = Some(99999999))) { unspentTransactions =>
      unspentTransactions.size shouldBe 2
      unspentTransactions.head.address shouldBe "mxC1MksGZQAARADNQutrT5FPVn76bqmgZW"
    }
  }

  it should "return accounts" in {
    whenReady(bitcoinClient.listAccounts()) { accounts =>
      accounts.size shouldBe 3
    }
  }
}
