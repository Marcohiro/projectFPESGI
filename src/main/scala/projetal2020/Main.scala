package projetal2020

import main.scala.projetal2020.Classes.{DataParser, FileParser, ResWriter}

import better.files._
import play.api.libs.json._

object Main extends App {
  println("Ici le programme principal")

  val fileParser = new FileParser(
    "C:\\Users\\petit\\OneDrive\\Bureau\\SCALA test\\valide.txt"
  )

  val data = fileParser.getData()

  val dataParser = new DataParser(data)

  val init = dataParser.getInit()

  val start = dataParser.getMowersStart()

  val instructions = dataParser.getInstructions()

  val res = dataParser.executeInstructionsSet()

  val resWriter = new ResWriter(
    init,
    start,
    instructions,
    res
  )

  val outpuFile: File = File(
    "C:\\\\Users\\\\petit\\\\OneDrive\\\\Bureau\\\\SCALA test\\\\output2.json"
  )

  outpuFile.createIfNotExists().appendLine(Json.stringify(resWriter.write()))

}
