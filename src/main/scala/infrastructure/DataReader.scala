package infrastructure

trait DataReader {

  def readData(sourceName: String): Array[String]

}
