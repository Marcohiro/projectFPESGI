package main.scala.projetal2020.Classes

import main.scala.projetal2020.const.{ValidDirection, ValidInstruction}

class Mower(x: Int, y: Int, orientation: ValidDirection.Value) {

  val abscissa: Int = x
  val ordinate: Int = y
  val direction: ValidDirection.Value = orientation

  def addLeft(direction: ValidDirection.Value): ValidDirection.Value = {
    direction match {
      case ValidDirection.North => ValidDirection.West
      case ValidDirection.East  => ValidDirection.North
      case ValidDirection.West  => ValidDirection.South
      case ValidDirection.South => ValidDirection.East
    }
  }

  def addRight(direction: ValidDirection.Value): ValidDirection.Value = {
    direction match {
      case ValidDirection.North => ValidDirection.East
      case ValidDirection.East  => ValidDirection.South
      case ValidDirection.West  => ValidDirection.North
      case ValidDirection.South => ValidDirection.West
    }
  }

  def forward(): Mower = direction match {
    case ValidDirection.North => new Mower(x, y + 1, direction)
    case ValidDirection.East  => new Mower(x + 1, y, direction)
    case ValidDirection.West  => new Mower(x - 1, y, direction)
    case ValidDirection.South => new Mower(x, y - 1, direction)
  }

  def executeOrder(order: ValidInstruction.Value): Mower = order match {
    case ValidInstruction.Right   => new Mower(x, y, addRight(direction))
    case ValidInstruction.Left    => new Mower(x, y, addLeft(direction))
    case ValidInstruction.Forward => forward()
  }

}
