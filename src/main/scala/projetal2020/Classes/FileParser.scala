package main.scala.projetal2020.Classes

import main.scala.projetal2020.Exceptions.DonneesIncorectesException

import scala.io.Source

class FileParser(filePath: String) {

  val path: String = filePath

  val validInstructions: Array[Char] = Array[Char]('A', 'G', 'D')

  val validDirections: Array[Char] = Array[Char]('N', 'E', 'W', 'S')

  def isAllDigits(x: String) = x forall Character.isDigit

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

  def isValidDirectionSet(direction: String): Boolean = {
    val chars = direction.toCharArray
    chars.forall(c => validDirections.contains(c))
  }

  def getAmountOfChar(line: String): Int = {
    line.replaceAll("\\s", "").length
  }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def parseLine(nbChar: Int, line: String): String = {
    def chars = line.split(" ")
    if (chars.length != nbChar && chars.length > 1) {
      throw new DonneesIncorectesException(
        "Erreur : Le nombre de characteres de correspond pas"
      )
    } else {
      nbChar match {
        case 1 => {
          if (isValidDirectionSet(chars(0))) {
            chars.mkString(" ")
          } else {
            throw new DonneesIncorectesException(
              "Fichier invalide : Les instructions ne sont pas valides"
            )
          }
        }
        case 2 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            chars.mkString(" ")
          } else {
            throw new DonneesIncorectesException(
              "Fichier invalide : La grille est mal definie"
            )
          }
        }
        case 3 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            if (isValidDirectionSet(chars(2))) {
              chars.mkString(" ")
            } else {
              throw new DonneesIncorectesException(
                "Fichier invalide : La direction de la tondeuse est mal définie"
              )
            }
          } else {
            throw new DonneesIncorectesException(
              "Fichier invalide : Les coordonnees de la tondeuse sont mal définies"
            )
          }
        }
        case _ => {
          if (isValidInstructionsSet(chars(0))) {
            chars.mkString(" ")
          } else {
            throw new DonneesIncorectesException(
              "Fichier invalide : Les instructions ne sont pas valides"
            )
          }
        }
      }
    }
  }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def getData(): Array[String] = {
    val lines = Source.fromFile(path).getLines().toArray
    if (lines.length % 2 == 0) {
      throw new DonneesIncorectesException(
        "Fichier Invalide : Il manque des instructions"
      )
    } else if (lines.length < 2) {
      throw new DonneesIncorectesException(
        "Fichier Invalide : Il n'y a aucune tondeuse"
      )
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
