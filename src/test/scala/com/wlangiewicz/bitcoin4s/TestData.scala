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

  val miningInfoResponse =
    """
      |{
      |    "result": {
      |        "blocks": 1089632,
      |        "currentblocksize": 0,
      |        "currentblockweight": 0,
      |        "currentblocktx": 0,
      |        "difficulty": 1300727.855652248,
      |        "errors": "Warning: unknown new rules activated (versionbit 28)",
      |        "networkhashps": 3269881530063.402,
      |        "pooledtx": 6,
      |        "testnet": true,
      |        "chain": "test"
      |    },
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

  val memPoolInfoResponse =
    """
      |{
      |    "result": {
      |        "size": 4,
      |        "bytes": 1343,
      |        "usage": 5312,
      |        "maxmempool": 300000000,
      |        "mempoolminfee": 0
      |    },
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

  val blockchainInfoResponse =
    """
      |{
      |    "result": {
      |        "chain": "test",
      |        "blocks": 1089634,
      |        "headers": 1089634,
      |        "bestblockhash": "0000000000000732220dd6a674c12f91e2b9cd21817338708f6de5b56f75a7d3",
      |        "difficulty": 1300727.855652248,
      |        "mediantime": 1487591936,
      |        "verificationprogress": 0.999999957737826,
      |        "chainwork": "00000000000000000000000000000000000000000000001f12183d2453fbca7d",
      |        "pruned": false,
      |        "softforks": [
      |            {
      |                "id": "bip34",
      |                "version": 2,
      |                "enforce": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 51,
      |                    "window": 100
      |                },
      |                "reject": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 75,
      |                    "window": 100
      |                }
      |            },
      |            {
      |                "id": "bip66",
      |                "version": 3,
      |                "enforce": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 51,
      |                    "window": 100
      |                },
      |                "reject": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 75,
      |                    "window": 100
      |                }
      |            },
      |            {
      |                "id": "bip65",
      |                "version": 4,
      |                "enforce": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 51,
      |                    "window": 100
      |                },
      |                "reject": {
      |                    "status": true,
      |                    "found": 100,
      |                    "required": 75,
      |                    "window": 100
      |                }
      |            }
      |        ],
      |        "bip9_softforks": {
      |            "csv": {
      |                "status": "active",
      |                "startTime": 1456790400,
      |                "timeout": 1493596800
      |            },
      |            "segwit": {
      |                "status": "active",
      |                "startTime": 1462060800,
      |                "timeout": 1493596800
      |            }
      |        }
      |    },
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

  val estimateFeeResponse =
    """
      |{
      |    "result": 0.00010244,
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

  val listUnspentResponse =
    """
      |{
      |    "result": [
      |        {
      |            "txid": "b13eae2ff0f833321cfb58fd69724b0388160208fc38a8879551afab06f39900",
      |            "vout": 1,
      |            "address": "mxC1MksGZQAARADNQutrT5FPVn76bqmgZW",
      |            "account": "account1",
      |            "scriptPubKey": "73a914b6e5b09be4bea8f85b075486e293765d0907ee2f88ac",
      |            "amount": 0.00286345,
      |            "confirmations": 411134,
      |            "spendable": true,
      |            "solvable": true
      |        },
      |        {
      |            "txid": "31fab313cd7dcdd57373e50c662cd7de87df6a1137cbea6493947c1540b33103",
      |            "vout": 0,
      |            "address": "n2LFEBSkiJreLLqnjbTP31TiQd4eBt6S3K",
      |            "account": "account2",
      |            "scriptPubKey": "76a914e4535d337068c27e4cda6384b64e2024694e99d388ac",
      |            "amount": 0.00005477,
      |            "confirmations": 25842,
      |            "spendable": true,
      |            "solvable": true
      |        }
      |    ],
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson

  val listAccountsResponse =
    """
      |{
      |    "result": {
      |        "": -0.00025328,
      |        "account1": 10.00041465,
      |        "account2": 0
      |    },
      |    "error": null,
      |    "id": null
      |}
    """.stripMargin.parseJson
}
