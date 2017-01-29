package com.wlangiewicz.bitcoin4s

import spray.json._

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

case class Softfork(
  id: String,
  version: Int,
  enforce: Option[Int],
  status: Boolean,
  found: Option[Int],
  required: Option[Int],
  window: Option[Int],
  bip9_softforks: JsObject
)
