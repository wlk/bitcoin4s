package com.wlangiewicz.bitcoin4s

import java.net.URL
import argonaut._, Argonaut._

case class BitcoinClient(url: URL, username: String, password: String) {
  def getInfo: Option[Info] = {
    val input = """
          {
          "version" : 100000,
          "protocolversion" : 70002,
          "walletversion" : 60000,
          "balance" : 5.85821723,
          "blocks" : 394817,
          "timeoffset" : 0,
          "connections" : 1,
          "proxy" : "",
          "difficulty" : 262144.00000000,
          "testnet" : true,
          "keypoololdest" : 1428661059,
          "keypoolsize" : 101,
          "paytxfee" : 0.00000000,
          "relayfee" : 0.00001000,
          "errors" : ""
          }
    """
    input.decodeOption[Info]
  }

  def getBestBlockHash: Option[String] = {
    Some("0000000020a94e872d7e033e788d90af1ddd638b5c3596c6f59ccc70254831dc")
  }

  def getWalletInfo: Option[WalletInfo] = {
    val input =
      """
        |{
        |"walletversion" : 60000,
        |"balance" : 5.85821723,
        |"txcount" : 353,
        |"keypoololdest" : 1428661059,
        |"keypoolsize" : 101
        |}
      """.stripMargin

    input.decodeOption[WalletInfo]
  }

}
