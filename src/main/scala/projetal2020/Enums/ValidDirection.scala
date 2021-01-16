package main.scala.projetal2020.Enums

object ValidDirection extends Enumeration {

  val North, East, West, South = Value

  def isValid(letter: Char): Boolean = letter match {
    case 'N' | 'E' | 'W' | 'S' => true
    case _                     => false
  }

  def parse(letter: Char): ValidDirection.Value = letter match {
    case 'N' => North
    case 'E' => East
    case 'W' => West
    case 'S' => South
  }

}
