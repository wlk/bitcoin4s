package com.wlangiewicz.bitcoin4s

import java.net.URL

import org.specs2.mutable.Specification


class ClientSpec extends Specification {

  val localhostURL = new URL("http://localhost:8332/")
  val c = BitcoinClient(localhostURL, "dev", "test")

  "client" should {
    "create simple client" in {
      c.url mustEqual localhostURL
    }

    "getinfo return info" in  {
      val info = c.getInfo.get
      info.balance mustEqual BigDecimal("5.85821723")
      info.testnet mustEqual true
    }

    "getwalletinfo return getwalletinfo" in {
      val walletInfo = c.getWalletInfo.get
      walletInfo.balance mustEqual BigDecimal("5.85821723")
    }
  }
}
