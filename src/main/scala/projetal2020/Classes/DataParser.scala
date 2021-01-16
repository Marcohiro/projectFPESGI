package main.scala.projetal2020.Classes
import main.scala.projetal2020.Enums.ValidDirection
import main.scala.projetal2020.Exceptions.DonneesIncorectesException

import scala.util.{Failure, Success}

class DataParser(data: List[String]) {

  val donnes = data

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def getInit(): Coordinate = {
    Coordinate.apply((donnes(0).split(" ")).toList) match {
      case Success(i) => i
      case Failure(s) => throw new DonneesIncorectesException(s.getMessage)
    }
  }

  def toMower(mower: String): Mower = {
    val coords = mower.split(" ")
    new Mower(
      new Coordinate(coords(0).toInt, coords(1).toInt),
      ValidDirection.parse(coords(2).charAt(0))
    )
  }

  def getInstructions(): List[String] = {
    def helper(data: List[String], res: List[String]): List[String] =
      data match {
        case List() => res
        case _ :: instructions :: tail =>
          helper(tail, res :+ instructions)
      }
    helper(donnes.drop(1), List())
  }

  def getMowersStart(): List[Mower] = {
    def helper(data: List[String], res: List[Mower]): List[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head)
        case mower :: _ :: tail =>
          helper(tail, res :+ toMower(mower))
      }
    helper(data.drop(1), List())
  }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def executeInstructionsSet(): List[Mower] = {
    val maxX = donnes(0).split(" ")(0).toInt
    val maxY = donnes(0).split(" ")(1).toInt
    def helper(data: List[String], res: List[Mower], line: Int): List[Mower] =
      data match {
        case List()         => res
        case head :: List() => res :+ toMower(head)
        case mower :: instructions :: tail =>
          val grid =
            new Grid(new Coordinate(maxX, maxY), toMower(mower), instructions)
          try {
            val newMower = grid.executeInstructionSet()
            helper(tail, res :+ newMower, line + 2)
          } catch {
            case donneesIncorectesException: DonneesIncorectesException =>
              throw new DonneesIncorectesException(
                donneesIncorectesException.getMessage + " a la ligne " + line.toString
              )
          }
      }
    helper(donnes.drop(1), List(), 1)
  }

  def mowerArrayToStringArray(mowers: List[Mower]): List[String] = {
    def helper(mowers: List[Mower], res: List[String]): List[String] = {
      mowers match {
        case List()       => res
        case head :: tail => helper(tail, res :+ head.toString())
      }
    }
    helper(mowers, List())
  }

  def getMowersRes(): List[MowersRes] = {
    def helper(
        mowersStart: List[Mower],
        instructions: List[String],
        mowersEnd: List[Mower],
        res: List[MowersRes]
    ): List[MowersRes] = {
      (mowersStart, instructions, mowersEnd) match {
        case (List(), List(), List()) => res
        case (head :: tail, head1 :: tail1, head2 :: tail2) =>
          val toAdd = new MowersRes(head, head1, head2)
          helper(tail, tail1, tail2, (res :+ toAdd))
      }
    }
    helper(
      getMowersStart(),
      getInstructions(),
      executeInstructionsSet(),
      List()
    )
  }

}
