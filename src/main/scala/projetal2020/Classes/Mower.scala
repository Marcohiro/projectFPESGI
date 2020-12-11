package main.scala.projetal2020.Classes

class Mower(x: Int, y: Int, orientation: String, instructions: String) {

  val abscissa: Int = x
  val ordinate: Int = y
  val direction: String = orientation
  val orders: String = instructions

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
    case _   => "ERROR"
  }

  def forward(): Mower = direction match {
    case "N" => new Mower(x, y + 1, direction, orders)
    case "E" => new Mower(x + 1, y, direction, orders)
    case "W" => new Mower(x - 1, y, direction, orders)
    case "S" => new Mower(x, y - 1, direction, orders)
    case _   => new Mower(x, y, direction, orders)
  }

  def executeOrder(order: String): Mower = order match {
    case "D" => new Mower(x, y, addRight(direction), orders)
    case "G" => new Mower(x, y, addLeft(direction), orders)
    case "A" => forward()
    case _   => new Mower(x, y, direction, orders)
  }

  override def toString(): String = {
    "X : " + abscissa.toString + " Y : " + ordinate.toString + " Direction : " + direction
  }
}
