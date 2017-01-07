package com.wlangiewicz.bitcoin4s

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

case class WalletInfo(
  walletversion: Long,
  balance: BigDecimal,
  txcount: Long,
  keypoololdest: Long,
  keypoolsize: Long)

case class BlockchainInfo(chain: String,
  blocks: Long,
  headers: Long,
  bestblockhash: String,
  difficulty: BigDecimal,
  verificationprogress: BigDecimal,
  chainwork: String)


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
