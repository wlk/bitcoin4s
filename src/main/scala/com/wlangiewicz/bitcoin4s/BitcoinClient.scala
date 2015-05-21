package com.wlangiewicz.bitcoin4s

import java.net.URL
import argonaut._, Argonaut._

class BitcoinClient(val url: URL, val username: String, val password: String, val client: BitcoinHttpClient) {

  def this(url: URL, username: String, password: String) {
    this(url, username, password, new RealBitcoinHttpClient(url, username, password))
  }

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

  def getBlockchainInfo: Option[BlockchainInfo] = {
    val input =
      """
      |{
      |"chain" : "test",
      |"blocks" : 416026,
      |"headers" : 416026,
      |"bestblockhash" : "000000000209962577f54c3f42b51b9501272fb4c9de36b54db16f01883b8541",
      |"difficulty" : 64.00000000,
      |"verificationprogress" : 0.99999999,
      |"chainwork" : "0000000000000000000000000000000000000000000000040e67d2033e3d3517"
      |}
    """.stripMargin

    input.decodeOption[BlockchainInfo]
  }

  def getBlock(hash: String): Option[GetBlock] = {
    val input =
      """
      |{
      |"hash" : "000000000209962577f54c3f42b51b9501272fb4c9de36b54db16f01883b8541",
      |"confirmations" : 250,
      |"size" : 208,
      |"height" : 416026,
      |"version" : 3,
      |"merkleroot" : "eed4a70e2d83aa776800a5c8bf09dea8a1fcdb8225931577420879e18a7c541a",
      |"tx" : [
      |"eed4a70e2d83aa776800a5c8bf09dea8a1fcdb8225931577420879e18a7c541a"
      |],
      |"time" : 1432219029,
      |"nonce" : 3081405769,
      |"bits" : "1c03fffc",
      |"difficulty" : 64.00000000,
      |"chainwork" : "0000000000000000000000000000000000000000000000040e67d2033e3d3517",
      |"previousblockhash" : "0000000001d02bb98ddbd283a24ed73eb9687f23455ede393d3939908f578b54",
      |"nextblockhash" : "00000000035e025b78a5c7bdfcc91ad297740f7af24d8450ea3928fafad7afe9"
      |}
    """.stripMargin

    input.decodeOption[GetBlock]
  }

}
