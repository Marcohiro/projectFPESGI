package main.scala.projetal2020.Classes

import scala.io.Source

class FileParser(filePath: String) {

  val path: String = filePath

  val validInstructions: Array[Char] = Array[Char]('A', 'G', 'D')

  def isAllDigits(x: String) = x forall Character.isDigit

  def isValidDirection(direction: String): Boolean = {
    direction.equals("N") || direction.equals("E") || direction.equals("W") || direction
      .equals("S")
  }

  def isPositive(number: String): Boolean = {
    if (number.length == 1 && isAllDigits(number)) {
      number.toInt > 0
    } else {
      false
    }
  }

  def isValidInstructionsSet(instructions: String): Boolean = {
    val chars = instructions.toCharArray
    chars.forall(c => validInstructions.contains(c))
  }

  def getAmountOfChar(line: String): Int = {
    line.replaceAll("\\s", "").length
  }

  def parseLine(nbChar: Int, line: String): String = {
    def chars = line.split(" ")
    if (chars.length != nbChar && chars.length > 1) {
      System.out.println("Erreur : Le nombre de characteres de correspond pas")
      "Erreur : Le nombre de characteres de correspond pas"
    } else {
      nbChar match {
        case 1 => {
          if (isValidDirection(chars(0))) {
            chars.mkString(" ")
          } else {
            System.out.println(
              "Fichier invalide : Les instructions ne sont pas valides"
            )
            "Fichier invalide : Les instructions ne sont pas valides"
          }
        }
        case 2 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            chars.mkString(" ")
          } else {
            System.out.println("Fichier invalide : La grille est mal definie")
            "Fichier invalide : La grille est mal definie"
          }
        }
        case 3 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            if (isValidDirection(chars(2))) {
              chars.mkString(" ")
            } else {
              System.out.println(
                "Fichier invalide : La direction de la tondeuse est mal définie"
              )
              "Fichier invalide : La direction de la tondeuse est mal définie"
            }
          } else {
            System.out.println(
              "Fichier invalide : Les coordonnees de la tondeuse sont mal définies"
            )
            "Fichier invalide : Les coordonnees de la tondeuse sont mal définies"
          }
        }
        case _ => {
          if (isValidInstructionsSet(chars(0))) {
            chars.mkString(" ")
          } else {
            System.out.println("Erreur : Le fichier ne correspond a rien")
            "Erreur : Le fichier ne correspond a rien"
          }
        }
      }
    }
  }

  def getData(): Array[String] = {
    val lines = Source.fromFile(path).getLines().toArray
    if (lines.length % 2 == 0) {
      System.out.println("Fichier Invalide : Il manque des instructions")
      Array()
    } else if (lines.length < 2) {
      System.out.println("Fichier Invalide : Il n'y a aucune tondeuse")
      Array()
    } else {
      System.out.println("Fichier valide")
      def helper(
          arg: List[String],
          res: Array[String]
      ): Array[String] = arg match {
        case List() => res
        case head :: tail =>
          helper(tail, res :+ parseLine(getAmountOfChar(head), head))
      }
      helper(lines.toList, Array())
    }
  }
}
