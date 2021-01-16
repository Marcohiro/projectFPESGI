package main.scala.projetal2020.Classes

import main.scala.projetal2020.Enums.{ValidDirection, ValidInstruction}
import play.api.libs.json.{Json, Writes}

case class Mower(coordinate: Coordinate, orientation: ValidDirection.Value) {

  val position: Coordinate = coordinate
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
    case ValidDirection.North =>
      new Mower(new Coordinate(position.x, position.y + 1), direction)
    case ValidDirection.East =>
      new Mower(new Coordinate(position.x + 1, position.y), direction)
    case ValidDirection.West =>
      new Mower(new Coordinate(position.x - 1, position.y), direction)
    case ValidDirection.South =>
      new Mower(new Coordinate(position.x, position.y - 1), direction)
  }

  def executeOrder(order: ValidInstruction.Value): Mower = order match {
    case ValidInstruction.Right   => new Mower(position, addRight(direction))
    case ValidInstruction.Left    => new Mower(position, addLeft(direction))
    case ValidInstruction.Forward => forward()
  }

  def outOfBounds(coordinate: Coordinate): Boolean = {
    position.x < 0 || position.y < 0 || position.x > coordinate.x || position.y > coordinate.y
  }

}

object Mower {

  implicit val writes: Writes[Mower] = Json.writes[Mower]

}
