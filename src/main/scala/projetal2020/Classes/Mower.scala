package main.scala.projetal2020.Classes

class Mower(x: Int, y: Int, orientation: String) {

  val abscissa: Int = x
  val ordinate: Int = y
  val direction: String = orientation

  def addLeft(direction: String): String = direction match {
    case "N" => "W"
    case "E" => "N"
    case "W" => "S"
    case "S" => "E"
    case _   => "ERROR"
  }

  def addRight(direction: String): String = direction match {
    case "N" => "E"
    case "E" => "S"
    case "W" => "N"
    case "S" => "W"
    case _  => "ERROR"
  }

  def forward(): Mower = direction match {
    case "N" => new Mower(x, y + 1, direction)
    case "E" => new Mower(x + 1, y, direction)
    case "W" => new Mower(x - 1, y, direction)
    case "S" => new Mower(x, y - 1, direction)
    case _   => new Mower(x, y, direction)
  }

  def executeOrder(order: String): Mower = order match {
    case "D" => new Mower(x, y, addRight(direction))
    case "G" => new Mower(x, y, addLeft(direction))
    case "A" => forward()
    case _   => new Mower(x, y, direction)
  }
}
