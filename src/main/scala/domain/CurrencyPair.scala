package domain

class CurrencyPair(val baseCurrency: String, val quoteCurrency: String, val exchangeRate: Double) {

  def canEqual(other: Any): Boolean = other.isInstanceOf[CurrencyPair]

  override def equals(other: Any): Boolean = other match {
    case that: CurrencyPair =>
      (that canEqual this) &&
        baseCurrency == that.baseCurrency &&
        quoteCurrency == that.quoteCurrency &&
        exchangeRate == that.exchangeRate
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(baseCurrency, quoteCurrency, exchangeRate)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

}
