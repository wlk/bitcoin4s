package com.wlangiewicz.bitcoin4s

case class Result()

case class GetWalletInfo(
  walletVersion: Int,
  balance: BigDecimal,
  txcount: Int,
  keypoololdest: Int,
  keypoolsize: Int,
  unlocked_until: Option[Int]
)

case class Address(
  address: String,
  port: Int,
  score: Int
)

case class Network(
  name: String,
  limited: Boolean,
  reachable: Boolean,
  proxy: String,
  localaddress: Vector[Address]
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