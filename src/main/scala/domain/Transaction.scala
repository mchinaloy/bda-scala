package domain

class Transaction(val partner: String, val currency: String, val amount: Double) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[Transaction]

  override def equals(other: Any): Boolean = other match {
    case that: Transaction =>
      (that canEqual this) &&
        partner == that.partner &&
        currency == that.currency &&
        amount == that.amount
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(partner, currency, amount)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

}
