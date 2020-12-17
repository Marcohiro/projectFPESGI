package main.scala.projetal2020.const

object ValidInstruction extends Enumeration {

  val Forward, Left, Right = Value

  def parse(letter: Char): ValidInstruction.Value = letter match {
    case 'A' => Forward
    case 'G' => Left
    case 'D' => Right
  }
}
