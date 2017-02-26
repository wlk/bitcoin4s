package com.wlangiewicz.bitcoin4s

import spray.json._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.wlangiewicz.bitcoin4s.Responses._

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
  implicit val AccountFormat: RootJsonFormat[Account] = jsonFormat2(Account)

  implicit object EstimateFeeFormat extends RootJsonReader[EstimateFee] {
    override def read(json: JsValue): EstimateFee = json match {
      case JsNumber(x) => EstimateFee(x)
      case x           => deserializationError("Expected EstimateFee as JsNumber, but got " + x)
    }
  }

  implicit object AccountsFormat extends RootJsonReader[Accounts] {
    override def read(json: JsValue): Accounts = json match {
      case JsObject(x) =>

        Accounts(x.seq.map {
          case (accountId, balance) => Account(accountId, balance.convertTo[BigDecimal])
        }.toVector)
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

  implicit object HeaderHashesFormat extends RootJsonReader[HeaderHashes] {
    override def read(json: JsValue): HeaderHashes = json match {
      case JsArray(hashes) =>
        HeaderHashes(hashes.map {
          case JsString(s) => s
          case other       => deserializationError("Expected header hash value as JsString, but got " + other)
        })
      case x => deserializationError("Expected HeaderHashes as JsArray[HeaderHash], but got " + x)
    }
  }

  implicit object UnspentTransactionsFormat extends RootJsonReader[UnspentTransactions] {
    override def read(json: JsValue): UnspentTransactions = json match {
      case JsArray(unspentTransactions) =>
        UnspentTransactions(
          unspentTransactions.map {
            case unspentTransaction: JsObject => unspentTransaction.convertTo[UnspentTransaction]
            case other                        => deserializationError("Expected unspent transaction value as JsString, but got " + other)
          }
        )
      case x => deserializationError("Expected UnspentTransactions as JsArray[UnspentTransaction], but got " + x)
    }
  }
}
