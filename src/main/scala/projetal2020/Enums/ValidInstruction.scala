package main.scala.projetal2020.Enums

object ValidInstruction extends Enumeration {

  val Forward, Left, Right = Value

  def isValid(letter: Char): Boolean = letter match {
    case 'A' | 'G' | 'D' => true
    case _               => false
  }

  def parse(letter: Char): ValidInstruction.Value = letter match {
    case 'A' => Forward
    case 'G' => Left
    case 'D' => Right
  }
}
