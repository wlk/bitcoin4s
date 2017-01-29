package com.wlangiewicz.bitcoin4s

case class GetWalletInfo(
  walletVersion: Int,
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
  proxy: String,
  relayfee: BigDecimal,
  localservices: String,
  networks: Vector[Network]
)

case class GetMiningInfo(
  blocks: Int,
  currentblocksie: Int,
  currentblocktx: Int,
  difficulty: Int,
  errors: Int,
  genproclimit: Int,
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
  mampoolminfee: Int
)

case class GetBlockChainInfo(
  chain: Int,
  blocks: Int,
  headers: Int,
  bestblockhash: String,
  difficulty: BigDecimal,
  mediantime: Int,
  verificationprogress: BigDecimal,
  chainwork: String,
  pruned: Boolean,
  pruneheight: Option[Int],
  softforks: Vector[Softfork]
)