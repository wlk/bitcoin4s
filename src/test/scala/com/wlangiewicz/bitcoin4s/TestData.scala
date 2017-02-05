package com.wlangiewicz.bitcoin4s

import spray.json._

object TestData {
  val walletInfoResponse: JsValue =
    """{
      |    "result": {
      |        "walletversion": 60000,
      |        "balance": 1.65751751,
      |        "unconfirmed_balance": 0,
      |        "immature_balance": 0,
      |        "txcount": 195,
      |        "keypoololdest": 1483630921,
      |        "keypoolsize": 100,
      |        "paytxfee": 0
      |    },
      |    "error": null,
      |    "id": null
      |}
      |""".stripMargin.parseJson

}
