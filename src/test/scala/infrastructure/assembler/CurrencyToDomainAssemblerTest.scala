package infrastructure.assembler

import domain.CurrencyPair
import org.scalatest.FunSuite

class CurrencyToDomainAssemblerTest extends FunSuite {

  private val currencyToDomainAssembler = new CurrencyToDomainAssembler

  test("assemble") {
    // assert
    assertResult(CurrencyToDomainAssemblerTest.EXPECTED_RESULT) {
      currencyToDomainAssembler.assemble(CurrencyToDomainAssemblerTest.EXCHANGE_RATE)
    }
  }

  object CurrencyToDomainAssemblerTest {
    val EXCHANGE_RATE = "USD,EUR,0.9251400028"
    val EXPECTED_RESULT = new CurrencyPair("USD", "EUR", 0.9251400028)
  }

}
