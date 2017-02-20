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

  val generateResponse: JsValue =
    """{
      |    "result": [
      |      "36252b5852a5921bdfca8701f936b39edeb1f8c39fffe73b0d8437921401f9af",
      |      "5f2956817db1e386759aa5794285977c70596b39ea093b9eab0aa4ba8cd50c06"
      |    ],
      |    "error": null,
      |    "id": null
      |}
      |""".stripMargin.parseJson

  val networkInfoResponse: JsValue =
    """
      |{
      |    "result": {
      |        "version": 130100,
      |        "subversion": "/Satoshi:0.13.1/",
      |        "protocolversion": 70014,
      |        "localservices": "000000000000000d",
      |        "localrelay": true,
      |        "timeoffset": 0,
      |        "connections": 8,
      |        "networks": [
      |            {
      |                "name": "ipv4",
      |                "limited": false,
      |                "reachable": true,
      |                "proxy": "",
      |                "proxy_randomize_credentials": false
      |            },
      |            {
      |                "name": "ipv6",
      |                "limited": false,
      |                "reachable": true,
      |                "proxy": "",
      |                "proxy_randomize_credentials": false
      |            },
      |            {
      |                "name": "onion",
      |                "limited": true,
      |                "reachable": false,
      |                "proxy": "",
      |                "proxy_randomize_credentials": false
      |            }
      |        ],
      |        "relayfee": 0.00001,
      |        "localaddresses": [
      |            {
      |                "address": "1.2.3.4",
      |                "port": 18333,
      |                "score": 1
      |            },
      |            {
      |                "address": "abcd:efef::abcde:adad:1234:5678",
      |                "port": 18333,
      |                "score": 1
      |            }
      |        ],
      |        "warnings": "Warning: unknown new rules activated (versionbit 28)"
      |    },
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

}
