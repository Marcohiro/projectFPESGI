package main.scala.projetal2020.Classes

import main.scala.projetal2020.Exceptions.DonneesIncorectesException
import main.scala.projetal2020.const.ValidInstruction

class Grid(x: Int, y: Int, mower: Mower, instructions: String) {

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def executeInstructionSet(): Mower = {
    def helper(instructions: List[Char], res: Mower): Mower =
      instructions match {
        case List() => res
        case head :: tail =>
          if (res.abscissa < 0 || res.abscissa > x || res.ordinate < 0 || res.ordinate > y) {
            throw new DonneesIncorectesException(
              "Erreur: La tondeuse est en dehors de la grille"
            )
          } else {
            helper(tail, res.executeOrder(ValidInstruction.parse(head)))
          }
      }
    helper(instructions.toCharArray.toList, mower)
  }

}
