package domain

import infrastructure.FileReader
import infrastructure.assembler.{CurrencyToDomainAssembler, TransactionToDomainAssembler}

class TransactionProcessor(val baseCurrency: String) {

  private val dataReader = new FileReader
  private val currencyToDomainAssembler = new CurrencyToDomainAssembler
  private val transactionToDomainAssembler = new TransactionToDomainAssembler

  private var transactions: Array[Transaction] = _
  private var currencyPairs: Array[CurrencyPair] = _

  {
    loadCurrencies()
    loadTransactions()
  }

  def aggregateByPartner(): Map[String, Double] = {
    transactions
      .map(transaction => aggregateOfPartner(transaction.partner))
      .map(transaction => (transaction.partner, transaction.amount))
      .toMap
  }

  def aggregateOfPartner(partner: String): Transaction = {
    val aggregatedResult = transactions
      .filter(transaction => transaction.partner.equals(partner))
      .map(transaction => calculateTransactionValue(transaction))
      .sum
    new Transaction(partner, baseCurrency, aggregatedResult)
  }

  private def calculateTransactionValue(transaction: Transaction): Double = {
    if (transaction.currency.equals(baseCurrency)) {
      return transaction.amount
    }
    currencyPairs
      .filter(currencyPair => currencyPair.baseCurrency.equals(transaction.currency))
      .filter(currencyPair => currencyPair.quoteCurrency.equals(baseCurrency))
      .map(currencyPair => currencyPair.exchangeRate)
      .last
  }

  private def loadCurrencies(): Unit = {
    val rawExchangeRates = dataReader.readData(TransactionProcessor.EXCHANGE_RATE_FILE)
    currencyPairs = rawExchangeRates.map(exchangeRate => currencyToDomainAssembler.assemble(exchangeRate))
  }

  private def loadTransactions(): Unit = {
    val rawCurrencies = dataReader.readData(TransactionProcessor.TRANSACTION_FILE)
    transactions = rawCurrencies.map(transaction => transactionToDomainAssembler.assemble(transaction))
  }

  object TransactionProcessor {
    val EXCHANGE_RATE_FILE = "exchangerates.csv"
    val TRANSACTION_FILE = "transactions.csv"
  }

}
