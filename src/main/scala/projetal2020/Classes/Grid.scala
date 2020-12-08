package main.scala.projetal2020.Classes

class Grid(x: Int, y: Int, mower: Mower, instructions: String) {

  def executeInstructionSet(): Mower = {
    def helper(instructions: List[Char], res: Mower): Mower =
      instructions match {
        case List() => res
        case head :: tail =>
          if (res.abscissa < 0 || res.abscissa > x || res.ordinate < 0 || res.ordinate > y) {
            System.out.println("ERREUR, LA TONDEUSE HORS DE LA GRILLE")
            res
          } else {
            helper(tail, res.executeOrder(head.toString))
          }
      }
    helper(instructions.toCharArray.toList, mower)
  }

}
