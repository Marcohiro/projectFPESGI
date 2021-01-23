package test.scala.example

import main.scala.projetal2020.Enums.ValidInstruction
import org.scalatest.funsuite.AnyFunSuite

class ValidInstructionSpec extends AnyFunSuite {

  test("Testing isValid instruction function") {
    assert(ValidInstruction.isValid('A') === true)
    assert(ValidInstruction.isValid('G') === true)
    assert(ValidInstruction.isValid('D') === true)
    assert(ValidInstruction.isValid('F') === false)
    assert(ValidInstruction.isValid('H') === false)
    assert(ValidInstruction.isValid('J') === false)
  }

  test("Testing parsing function") {
    assert(ValidInstruction.parse('A') === ValidInstruction.Forward)
    assert(ValidInstruction.parse('G') === ValidInstruction.Left)
    assert(ValidInstruction.parse('D') === ValidInstruction.Right)
  }

}
