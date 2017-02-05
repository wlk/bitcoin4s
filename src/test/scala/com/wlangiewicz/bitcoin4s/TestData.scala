package com.wlangiewicz.bitcoin4s

import spray.json._

object TestData {
  val walletInfoResponse = """{
                             |  "walletversion": 60000,
                             |  "balance": 1.65751751,
                             |  "unconfirmed_balance": 0.00000000,
                             |  "immature_balance": 0.00000000,
                             |  "txcount": 195,
                             |  "keypoololdest": 1483630921,
                             |  "keypoolsize": 100,
                             |  "paytxfee": 0.00000000
                             |}
                             |""".parseJson

}
