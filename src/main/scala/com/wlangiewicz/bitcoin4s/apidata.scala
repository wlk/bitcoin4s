package com.wlangiewicz.bitcoin4s

import argonaut._, Argonaut._

case class Info(version: Long, protocolversion: Long, walletversion: Long, balance: BigDecimal, blocks: Long, timeoffset: Long,
                 connections: Long, proxy: String, difficulty: BigDecimal, testnet: Boolean, keypoololdest: Long, keypoolsize: Long,
                 paytxfee: BigDecimal, relayfee: BigDecimal, errors: String)

object Info {
  implicit def InfoCodecJson: CodecJson[Info] = casecodec15(Info.apply, Info.unapply)("version", "protocolversion", "walletversion", "balance", "blocks",
  "timeoffset", "connections", "proxy", "difficulty", "testnet", "keypoololdest", "keypoolsize", "paytxfee", "relayfee", "errors")
}

case class WalletInfo(walletversion: Long, balance: BigDecimal, txcount: Long, keypoololdest: Long, keypoolsize: Long)

object WalletInfo{
  implicit def WalletInfoCodecJson: CodecJson[WalletInfo] = casecodec5(WalletInfo.apply, WalletInfo.unapply)("walletversion", "balance", "txcount", "keypoololdest", "keypoolsize")
}