package infrastructure.assembler

import domain.CurrencyPair

class CurrencyToDomainAssembler {

  def assemble(line: String): CurrencyPair = {
    val splitString = line.split(",")
    new CurrencyPair(splitString(0), splitString(1), splitString(2).toDouble)
  }

}
