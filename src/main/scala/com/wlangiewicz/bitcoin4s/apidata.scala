package com.wlangiewicz.bitcoin4s

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
  localaddress: Option[Vector[Address]]
)

case class Softfork(
  id: String,
  version: Int,
  enforce: Option[Int],
  status: Boolean,
  found: Option[Int],
  required: Option[Int],
  window: Option[Int]
)

