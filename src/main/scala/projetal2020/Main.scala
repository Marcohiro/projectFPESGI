package projetal2020

import main.scala.projetal2020.Classes.{DataParser, FileParser, ResWriter}
import main.scala.projetal2020.Exceptions.DonneesIncorectesException
import better.files._
import play.api.libs.json._

object Main extends App {
  println("Ici le programme principal")

  val fileParser = new FileParser(
    "C:\\Users\\petit\\OneDrive\\Bureau\\SCALA test\\valide.txt"
  )

  try {
    val data = fileParser.getData()

    val dataParser = new DataParser(data)

    val resWriter = new ResWriter(
      dataParser.getInit(),
      dataParser.getMowersStart(),
      dataParser.getInstructions(),
      dataParser.executeInstructionsSet()
    )

    val outputFile: File = File(
      "C:\\\\Users\\\\petit\\\\OneDrive\\\\Bureau\\\\SCALA test\\\\output2.json"
    )

    outputFile.createIfNotExists().appendLine(Json.stringify(resWriter.write()))

    ()
  } catch {
    case donneesIncorectesException: DonneesIncorectesException =>
      println(donneesIncorectesException)
  }
}
