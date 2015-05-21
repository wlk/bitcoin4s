package com.wlangiewicz.bitcoin4s

import java.net.URL

import org.specs2.mutable.Specification


class ClientSpec extends Specification {

  val localhostURL = new URL("http://localhost:8332/")
  val c = new BitcoinClient(localhostURL, "dev", "test")

  "client" should {
    "create simple client" in {
      c.url mustEqual localhostURL
    }

    "getinfo return correct object" in  {
      val info = c.getInfo.get
      info.balance mustEqual BigDecimal("5.85821723")
      info.testnet mustEqual true
    }

    "getwalletinfo return correct object" in {
      val walletInfo = c.getWalletInfo.get
      walletInfo.balance mustEqual BigDecimal("5.85821723")
    }

    "getblockchain return correct object" in {
      val blockchainInfo = c.getBlockchainInfo.get
      blockchainInfo.bestblockhash mustEqual "000000000209962577f54c3f42b51b9501272fb4c9de36b54db16f01883b8541"
    }

    "getblock return correct object" in {
      val getblock = c.getBlock("000000000209962577f54c3f42b51b9501272fb4c9de36b54db16f01883b8541").get
      getblock.hash mustEqual "000000000209962577f54c3f42b51b9501272fb4c9de36b54db16f01883b8541"
      getblock.confirmations mustEqual 250
    }
  }
}
