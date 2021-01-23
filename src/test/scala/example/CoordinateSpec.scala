package test.scala.example

import main.scala.projetal2020.Classes.Coordinate
import main.scala.projetal2020.Exceptions.DonneesIncorectesException
import org.scalatest.funsuite.AnyFunSuite

import scala.util.{Success}

class CoordinateSpec extends AnyFunSuite {

  test("Passing valid string list should return a coordinate") {
    assert(Coordinate.apply(List("1", "1")) === Success(new Coordinate(1, 1)))
    assert(Coordinate.apply(List("5", "5")) === Success(new Coordinate(5, 5)))
    assert(Coordinate.apply(List("4", "5")) === Success(new Coordinate(4, 5)))
    assert(Coordinate.apply(List("5", "4")) === Success(new Coordinate(5, 4)))
  }

  test("Passing invalid string list should throw exception") {
    assertThrows[DonneesIncorectesException] {
      Coordinate.apply(List("1"))
    }
  }

  test("Passing 1 negative value should throw exception") {
    assertThrows[DonneesIncorectesException] {
      Coordinate.apply(List("1", "-6"))
    }
  }

  test("Passing 2 negative values should throw exception") {
    assertThrows[DonneesIncorectesException] {
      Coordinate.apply(List("-1", "-6"))
    }
  }

}
