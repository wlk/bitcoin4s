package com.wlangiewicz.bitcoin4s

import java.net.URL

import org.specs2.mutable.Specification


class CreateClientSpec extends Specification {

  val localhostURL = new URL("http://localhost:8332/")
  "create client spec" should {
    "create simple client" in {
      val c = BitcoinClient(localhostURL, "dev", "test")
      c.url mustEqual localhostURL
    }
  }

}
