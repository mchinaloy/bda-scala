import application.TransactionManager

object Main {

  private var transactionManager: TransactionManager = _

  def main(args: Array[String]): Unit = {
    if (args.length == 1) {
      transactionManager = new TransactionManager(args(0))
      transactionManager.aggregateByPartner()
    } else {
      transactionManager = new TransactionManager(args(0), args(1))
      transactionManager.aggregateOfPartner()
    }
  }

}
