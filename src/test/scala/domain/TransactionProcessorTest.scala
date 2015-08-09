package domain

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class TransactionProcessorTest extends FunSuite with MockFactory with BeforeAndAfter {

  private val transactionProcessor = new TransactionProcessor(TransactionProcessorTest.BASE_CURRENCY)

  before {
    val transactionsField = classOf[TransactionProcessor].getDeclaredField("transactions")
    transactionsField.setAccessible(true)
    transactionsField.set(transactionProcessor, TransactionProcessorTest.TRANSACTIONS)

    val currencyPairsField = classOf[TransactionProcessor].getDeclaredField("currencyPairs")
    currencyPairsField.setAccessible(true)
    currencyPairsField.set(transactionProcessor, TransactionProcessorTest.CURRENCY_PAIRS)
  }

  test("aggregateByPartner") {
    // setup
    val expectedResult = Map(TransactionProcessorTest.PARTNER -> TransactionProcessorTest.AMOUNT)

    // assert
    assertResult(expectedResult) {
      transactionProcessor.aggregateByPartner()
    }
  }

  test("aggregateOfPartner") {
    // setup
    val expectedResult = new Transaction(TransactionProcessorTest.PARTNER, TransactionProcessorTest.BASE_CURRENCY, TransactionProcessorTest.AMOUNT)

    // assert
    assertResult(expectedResult) {
      transactionProcessor.aggregateOfPartner(TransactionProcessorTest.PARTNER)
    }
  }

  object TransactionProcessorTest {
    val BASE_CURRENCY = "GBP"
    val QUOTE_CURRENCY = "EUR"
    val PARTNER = "Defence ltd."
    val AMOUNT = 10.00
    val TRANSACTIONS = Array(new Transaction(PARTNER, BASE_CURRENCY, AMOUNT))
    val CURRENCY_PAIRS = Array(new CurrencyPair(BASE_CURRENCY, QUOTE_CURRENCY, AMOUNT))
  }

}
