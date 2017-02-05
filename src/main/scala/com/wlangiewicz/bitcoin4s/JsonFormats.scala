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

  implicit object EstimateFeeFormat extends RootJsonReader[EstimateFee] {
    override def read(json: JsValue): EstimateFee = json match {
      case JsNumber(x) => EstimateFee(x.toInt)
      case x           => deserializationError("Expected EstimateFee as JsNumber, but got " + x)
    }
  }

  implicit object VectorAccountsFormat extends RootJsonReader[Vector[Account]] {
    override def read(json: JsValue): Vector[Account] = json match {
      case JsObject(x) =>
        x.seq.map {
          case (accountId, balance) => Account(accountId, balance.convertTo[BigDecimal])
        }.toVector
      case x => deserializationError("Expected Vector[Account] as Json Map, but got " + x)
    }
  }

  implicit object GetNewAddressFormat extends RootJsonReader[GetNewAddress] {
    override def read(json: JsValue): GetNewAddress = json match {
      case JsString(x) => GetNewAddress(x)
      case x           => deserializationError("Expected GetNewAddress as JsString, but got " + x)
    }
  }

  implicit object SentTransactionIdFormat extends RootJsonReader[SentTransactionId] {
    override def read(json: JsValue): SentTransactionId = json match {
      case JsString(x) => SentTransactionId(x)
      case x           => deserializationError("Expected SentTransactionId as JsString, but got " + x)
    }
  }
}
