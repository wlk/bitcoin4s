package com.wlangiewicz.bitcoin4s

case class GetWalletInfo(
  walletversion: Int,
  balance: BigDecimal,
  txcount: Int,
  keypoololdest: Int,
  keypoolsize: Int,
  unlocked_until: Option[Int]
)

case class GetNetworkInfo(
  version: Int,
  subversion: String,
  protocolversion: Int,
  timeoffset: Int,
  connections: Int,
  proxy: Option[String],
  relayfee: BigDecimal,
  localservices: String,
  networks: Vector[Network]
)

case class GetMiningInfo(
  blocks: Int,
  currentblocksize: Int,
  currentblocktx: Int,
  difficulty: Int,
  errors: String,
  genproclimit: Option[Int],
  networkhashps: Int,
  pooledtx: Int,
  testnet: Boolean,
  chain: String,
  generate: Option[Boolean],
  hashespersec: Option[Int]
)

case class GetMemPoolInfo(
  size: Int,
  bytes: Int,
  usage: Int,
  maxmempool: Int,
  mempoolminfee: Int
)

case class GetBlockChainInfo(
  chain: String,
  blocks: Int,
  headers: Int,
  bestblockhash: String,
  difficulty: BigDecimal,
  mediantime: Int,
  verificationprogress: BigDecimal,
  chainwork: String,
  pruned: Boolean,
  pruneheight: Option[Int]
)