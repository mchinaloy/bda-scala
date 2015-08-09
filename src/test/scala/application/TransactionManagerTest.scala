package application

import domain.{Transaction, TransactionProcessorMock}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class TransactionManagerTest extends FunSuite with MockFactory with BeforeAndAfter {

  private var transactionManager: TransactionManager = _
  private val transactionProcessor = stub[TransactionProcessorMock]

  test("aggregateByPartner") {
    // setup
    transactionManager = new TransactionManager(TransactionManagerTest.BASE_CURRENCY)
    setTransactionProcessor()

    // act
    transactionManager.aggregateByPartner()

    // assert
    (transactionProcessor.aggregateByPartner _).verify().once()
  }

  test("aggregateOfPartner") {
    // setup
    transactionManager = new TransactionManager(TransactionManagerTest.PARTNER, TransactionManagerTest.BASE_CURRENCY)
    setTransactionProcessor()

    val partnerField = classOf[TransactionManager].getDeclaredField("partner")
    partnerField.setAccessible(true)
    partnerField.set(transactionManager, TransactionManagerTest.PARTNER)

    (transactionProcessor.aggregateOfPartner _).when(TransactionManagerTest.PARTNER).returns(TransactionManagerTest.TRANSACTION)

    // act
    transactionManager.aggregateOfPartner()

    // assert
    (transactionProcessor.aggregateOfPartner _).verify(TransactionManagerTest.PARTNER).once()
  }

  private def setTransactionProcessor(): Unit = {
    val field = classOf[TransactionManager].getDeclaredField("transactionProcessor")
    field.setAccessible(true)
    field.set(transactionManager, transactionProcessor)
  }

  object TransactionManagerTest {
    val BASE_CURRENCY = "GBP"
    val PARTNER = "Defence ltd."
    val AMOUNT = 10.00
    val TRANSACTION = new Transaction(PARTNER, BASE_CURRENCY, AMOUNT)
  }

}
