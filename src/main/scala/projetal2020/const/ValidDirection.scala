package main.scala.projetal2020.const

object ValidDirection extends Enumeration {

  val North, East, West, South = Value

  def parse(letter: Char): ValidDirection.Value = letter match {
    case 'N' => North
    case 'E' => East
    case 'W' => West
    case 'S' => South
  }

}
