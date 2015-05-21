package com.wlangiewicz.bitcoin4s

import argonaut._, Argonaut._

case class Info(
  version: Long,
  protocolversion: Long,
  walletversion: Long,
  balance: BigDecimal,
  blocks: Long,
  timeoffset: Long,
  connections: Long, proxy: String,
  difficulty: BigDecimal,
  testnet: Boolean,
  keypoololdest: Long,
  keypoolsize: Long,
  paytxfee: BigDecimal,
  relayfee: BigDecimal,
  errors: String)

object Info {
  implicit def InfoCodecJson: CodecJson[Info] = casecodec15(Info.apply, Info.unapply)(
    "version",
    "protocolversion",
    "walletversion",
    "balance",
    "blocks",
    "timeoffset",
    "connections",
    "proxy",
    "difficulty",
    "testnet",
    "keypoololdest",
    "keypoolsize",
    "paytxfee",
    "relayfee",
    "errors")
}

case class WalletInfo(
  walletversion: Long,
  balance: BigDecimal,
  txcount: Long,
  keypoololdest: Long,
  keypoolsize: Long)

object WalletInfo {
  implicit def WalletInfoCodecJson: CodecJson[WalletInfo] = casecodec5(WalletInfo.apply, WalletInfo.unapply)(
    "walletversion",
    "balance",
    "txcount",
    "keypoololdest",
    "keypoolsize")
}

case class BlockchainInfo(chain: String,
  blocks: Long,
  headers: Long,
  bestblockhash: String,
  difficulty: BigDecimal,
  verificationprogress: BigDecimal,
  chainwork: String)

object BlockchainInfo {
  implicit def BlockchainInfoCodecJson: CodecJson[BlockchainInfo] = casecodec7(BlockchainInfo.apply, BlockchainInfo.unapply)(
    "chain",
    "blocks",
    "headers",
    "bestblockhash",
    "difficulty",
    "verificationprogress",
    "chainwork")
}

case class GetBlock(
  hash: String,
  confirmations: Long,
  size: Long,
  height: Long,
  version: Long,
  merkleroot: String,
  tx: List[String],
  time: Long,
  nonce: Long,
  bits: String,
  difficulty: BigDecimal,
  chainwork: String,
  previousblockhash: String,
  nextblockhash: String)

object GetBlock {
  implicit def GetBlockCodecJson: CodecJson[GetBlock] = casecodec14(GetBlock.apply, GetBlock.unapply)(
    "hash",
    "confirmations",
    "size",
    "height",
    "version",
    "merkleroot",
    "tx",
    "time",
    "nonce",
    "bits",
    "difficulty",
    "chainwork",
    "previousblockhash",
    "nextblockhash")
}

