package infrastructure.assembler

import domain.Transaction

class TransactionToDomainAssembler {

  def assemble(line: String): Transaction = {
    val splitString = line.split(",")
    new Transaction(splitString(0), splitString(1), splitString(2).toDouble)
  }

}
