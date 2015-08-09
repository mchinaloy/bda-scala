package infrastructure

import scala.io.Source

class FileReader extends DataReader {

  override def readData(sourceName : String): Array[String]={
    val source = Source.fromFile(sourceName, "UTF-8")
    source.getLines().toArray
  }

}
