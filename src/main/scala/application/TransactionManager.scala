package application

import domain.TransactionProcessor

class TransactionManager(baseCurrency: String) {

  var partner: String = _
  val transactionProcessor: TransactionProcessor = new TransactionProcessor(baseCurrency)

  def this(partner: String, baseCurrency: String) {
    this(baseCurrency)
    this.partner = partner
  }

  def aggregateByPartner(): Unit = {
    val aggregatedResult: Map[String, Double] = transactionProcessor.aggregateByPartner()
    println(aggregatedResult)
  }

  def aggregateOfPartner(): Unit = {
    val aggregatedResult = transactionProcessor.aggregateOfPartner(partner)
    println(aggregatedResult.partner + " : " + aggregatedResult.amount)
  }

}
