package test.scala.example

import main.scala.projetal2020.Classes.{Coordinate, Mower}
import org.scalatest.funsuite.AnyFunSuite
import main.scala.projetal2020.Enums.{ValidDirection, ValidInstruction}

class MowerSpec extends AnyFunSuite {

  test("Testing mower going forward on every direction") {
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North)
        .forward() === new Mower(
        new Coordinate(1, 2),
        ValidDirection.North
      )
    )
    assert(
      new Mower(new Coordinate(2, 2), ValidDirection.East)
        .forward() === new Mower(
        new Coordinate(3, 2),
        ValidDirection.East
      )
    )
    assert(
      new Mower(new Coordinate(3, 3), ValidDirection.West)
        .forward() === new Mower(
        new Coordinate(2, 3),
        ValidDirection.West
      )
    )
    assert(
      new Mower(new Coordinate(4, 4), ValidDirection.South)
        .forward() === new Mower(
        new Coordinate(4, 3),
        ValidDirection.South
      )
    )
  }

  test("Adding right to every single direction") {
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North)
        .addRight(ValidDirection.North) === ValidDirection.East
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.East)
        .addRight(ValidDirection.East) === ValidDirection.South
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.West)
        .addRight(ValidDirection.West) === ValidDirection.North
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.South)
        .addRight(ValidDirection.South) === ValidDirection.West
    )
  }

  test("Adding left to every single direction") {
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North)
        .addLeft(ValidDirection.North) === ValidDirection.West
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.East)
        .addLeft(ValidDirection.East) === ValidDirection.North
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.West)
        .addLeft(ValidDirection.West) === ValidDirection.South
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.South)
        .addLeft(ValidDirection.South) === ValidDirection.East
    )
  }

  test("Mower is in bounds") {
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North)
        .outOfBounds(new Coordinate(2, 2)) === false
    )
    assert(
      new Mower(new Coordinate(2, 1), ValidDirection.North)
        .outOfBounds(new Coordinate(3, 3)) === false
    )
  }

  test("Mower is out of bounds") {
    assert(
      new Mower(new Coordinate(5, 5), ValidDirection.North)
        .outOfBounds(new Coordinate(1, 1)) === true
    )
    assert(
      new Mower(new Coordinate(-1, 1), ValidDirection.North)
        .outOfBounds(new Coordinate(1, 1)) === true
    )
  }

  test("Testing execute instruction function") {
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North).executeOrder(
        ValidInstruction.Right
      ) === new Mower(new Coordinate(1, 1), ValidDirection.East)
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North).executeOrder(
        ValidInstruction.Left
      ) === new Mower(new Coordinate(1, 1), ValidDirection.West)
    )
    assert(
      new Mower(new Coordinate(1, 1), ValidDirection.North).executeOrder(
        ValidInstruction.Forward
      ) === new Mower(new Coordinate(1, 2), ValidDirection.North)
    )
  }
}
