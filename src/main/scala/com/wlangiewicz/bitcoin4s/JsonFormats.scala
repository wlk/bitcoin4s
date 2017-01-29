package com.wlangiewicz.bitcoin4s

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport

trait JsonFormats extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val AddressFormat: RootJsonFormat[Address] = jsonFormat3(Address)
  implicit val NetworkFormat: RootJsonFormat[Network] = jsonFormat5(Network)
  implicit val SoftforkFormat: RootJsonFormat[Softfork] = jsonFormat7(Softfork)

  implicit val GetWalletInfoFormat: RootJsonFormat[GetWalletInfo] = jsonFormat6(GetWalletInfo)
  implicit val GetNetworkInfoFormat: RootJsonFormat[GetNetworkInfo] = jsonFormat9(GetNetworkInfo)
  implicit val GetMiningInfoFormat: RootJsonFormat[GetMiningInfo] = jsonFormat12(GetMiningInfo)
  implicit val GetMemPoolInfoFormat: RootJsonFormat[GetMemPoolInfo] = jsonFormat5(GetMemPoolInfo)
  implicit val GetBlockChainInfoFormat: RootJsonFormat[GetBlockChainInfo] = jsonFormat10(GetBlockChainInfo)

  implicit val UnspentTransactionFormat: RootJsonFormat[UnspentTransaction] = jsonFormat9(UnspentTransaction)

  implicit object EstimateFeeFormat extends RootJsonFormat[EstimateFee] {
    def write(value: EstimateFee) = JsNumber(value.estimate)

    override def read(json: JsValue): EstimateFee = json match {
      case JsNumber(x) => EstimateFee(x.toInt)
      case x           => deserializationError("Expected EstimateFee as JsNumber, but got " + x)
    }
  }
}
