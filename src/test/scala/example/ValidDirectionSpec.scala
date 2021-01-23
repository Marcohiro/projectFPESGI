package test.scala.example

import main.scala.projetal2020.Enums.ValidDirection
import org.scalatest.funsuite.AnyFunSuite

class ValidDirectionSpec extends AnyFunSuite {

  test("testing is ValidDirection function") {
    assert(ValidDirection.isValid('N') === true)
    assert(ValidDirection.isValid('E') === true)
    assert(ValidDirection.isValid('W') === true)
    assert(ValidDirection.isValid('S') === true)
    assert(ValidDirection.isValid('A') === false)
    assert(ValidDirection.isValid('G') === false)
    assert(ValidDirection.isValid('D') === false)
  }

  test("testing ValidDirection parsing function") {
    assert(ValidDirection.parse('N') === ValidDirection.North)
    assert(ValidDirection.parse('E') === ValidDirection.East)
    assert(ValidDirection.parse('W') === ValidDirection.West)
    assert(ValidDirection.parse('S') === ValidDirection.South)
  }

}
