package main.scala.projetal2020.Classes

class DataParser(data: Array[String]) {

  val donnes = data

  def getInit(): Array[String] = {
    donnes(0).split(" ")
  }

  def toMower(mower: String): Mower = {
    val coords = mower.split(" ")
    new Mower(coords(0).toInt, coords(1).toInt, coords(2))
  }

  def getInstructions(): Array[String] = {
    def helper(data: List[String], res: Array[String]): Array[String] =
      data match {
        case List() => res
        case _ :: instructions :: tail =>
          helper(tail, res :+ instructions)
      }
    helper(donnes.drop(1).toList, Array())
  }

  def getMowersStart(): Array[Mower] = {
    def helper(data: List[String], res: Array[Mower]): Array[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head)
        case mower :: _ :: tail =>
          helper(tail, res :+ toMower(mower))
      }
    helper(data.drop(1).toList, Array())
  }

  def executeInstructionsSet(): Array[Mower] = {
    val maxX = donnes(0).split(" ")(0).toInt
    val maxY = donnes(0).split(" ")(1).toInt
    def helper(data: List[String], res: Array[Mower]): Array[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head)
        case mower :: instructions :: tail =>
          val grid =
            new Grid(maxX, maxY, toMower(mower), instructions)
          helper(tail, res :+ grid.executeInstructionSet())
      }
    helper(donnes.drop(1).toList, Array())
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

}
