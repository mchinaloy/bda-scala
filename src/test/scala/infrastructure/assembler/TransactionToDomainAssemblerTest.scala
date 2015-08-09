package infrastructure.assembler

import domain.Transaction
import org.scalatest.FunSuite

class TransactionToDomainAssemblerTest extends FunSuite {

  private val transactionToDomainAssembler = new TransactionToDomainAssembler

  test("assemble") {
    // assert
    assertResult(TransactionToDomainAssemblerTest.EXPECTED_RESULT) {
      transactionToDomainAssembler.assemble(TransactionToDomainAssemblerTest.TRANSACTION)
    }
  }

  object TransactionToDomainAssemblerTest {
    val TRANSACTION = "Defence ltd.,GBP,827.4293638063384"
    val EXPECTED_RESULT = new Transaction("Defence ltd.", "GBP", 827.4293638063384)
  }

}
