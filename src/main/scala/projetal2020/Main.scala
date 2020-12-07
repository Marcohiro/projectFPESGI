package projetal2020

import main.scala.projetal2020.Classes.FileParser

object Main extends App {
  println("Ici le programme principal")

  val fileParser = new FileParser(
    "C:\\Users\\petit\\OneDrive\\Bureau\\SCALA test\\valide.txt"
  )

  val data = fileParser.getData()

  def printArray(array: Array[String]): Unit = {
    array.foreach(x => println(x.mkString(", ")))
  }

  def print(array: List[Array[String]]): Unit = array match {
    case Nil => System.out.println("END")
    case head :: tail =>
      printArray(head)
      System.out.println(" ")
      print(tail)
  }

  print(data.toList)
  // Le code suivant ne compilera pas.
  // var tmp = null;
  // var tmp2 = if (tmp == 1) "yes" else 1

  // println(s"tmp: $tmp, tmp2: $tmp2")
}
