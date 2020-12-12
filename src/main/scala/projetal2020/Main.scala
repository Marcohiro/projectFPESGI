package projetal2020

import main.scala.projetal2020.Classes.{FileParser, Grid, Mower, ResWriter}

import better.files._
import play.api.libs.json._

object Main extends App {
  println("Ici le programme principal")

  val fileParser = new FileParser(
    "C:\\Users\\petit\\OneDrive\\Bureau\\SCALA test\\valide.txt"
  )

  val data = fileParser.getData()

  def print(array: List[String]): Unit = array match {
    case Nil => System.out.println("END")
    case head :: tail =>
      System.out.println(head)
      print(tail)
  }

  print(data.toList)

  def toMower(mower: String, instructions: String): Mower = {
    val coords = mower.split(" ")
    new Mower(coords(0).toInt, coords(1).toInt, coords(2), instructions)
  }

  def mowerArrayToStringArray(mowers: Array[Mower]): Array[String] = {
    def helper(mowers: List[Mower], res: Array[String]): Array[String] = {
      mowers match {
        case List()       => res
        case head :: tail => helper(tail, res :+ head.toString())
      }
    }
    helper(mowers.toList, Array())
  }

  def executeInstructionsSet(data: List[String]): Array[Mower] = {
    val maxX = data(0).split(" ")(0).toInt
    val maxY = data(0).split(" ")(1).toInt
    def helper(data: List[String], res: Array[Mower]): Array[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head, "")
        case mower :: instructions :: tail =>
          val grid =
            new Grid(maxX, maxY, toMower(mower, instructions), instructions)
          helper(tail, res :+ grid.executeInstructionSet())
      }
    helper(data.drop(1), Array())
  }

  def getInit(data: Array[String]): Array[String] = {
    data(0).split(" ")
  }

  def getMowersStart(data: List[String]): Array[Mower] = {
    def helper(data: List[String], res: Array[Mower]): Array[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head, "")
        case mower :: instructions :: tail =>
          helper(tail, res :+ toMower(mower, instructions))
      }
    helper(data.drop(1), Array())
  }

  def getInstructions(data: List[String]): Array[String] = {
    def helper(data: List[String], res: Array[String]): Array[String] =
      data match {
        case List() => res
        case _ :: instructions :: tail =>
          helper(tail, res :+ instructions)
      }
    helper(data.drop(1), Array())
  }

  val res = executeInstructionsSet(data.toList)

  val resWriter = new ResWriter(
    getInit(data),
    getMowersStart(data.toList),
    res
  )

  print(mowerArrayToStringArray(res).toList)

  val outpuFile: File = File(
    "C:\\\\Users\\\\petit\\\\OneDrive\\\\Bureau\\\\SCALA test\\\\output2.json"
  ) // using constructor

  outpuFile.createIfNotExists().appendLine(Json.stringify(resWriter.write()))

}
