package main.scala.projetal2020.Classes

import scala.io.Source

class FileParser(filePath: String) {

  val path: String = filePath

  val validInstructions: Array[Char] = Array[Char]('A', 'G', 'D')

  def isAllDigits(x: String) = x forall Character.isDigit

  def isValidDirection(direction: String): Boolean = {
    direction.equals("N") || direction.equals("E") || direction.equals("W") || direction.equals("S")
  }

  def isPositive(number: String): Boolean = {
    if(number.length == 1 && isAllDigits(number)){
      number.toInt > 0
    }
    false
  }

  def isValidInstructionsSet(instructions: String): Boolean ={
    val chars = instructions.toCharArray
    chars.forall(c => validInstructions.contains(c))
  }

  def parseLine(nbChar: Int, line: String): Array[String] = {
    def chars = line.split(" ")
    if (chars.length != nbChar) {
      System.out.println("Erreur : Le nombre de characteres de correspond pas")
      Array[String]()
    } else {
      nbChar match {
        case 1 => {
          if(isValidDirection(chars)) {chars} else {
            System.out.println("Fichier invalide : Les instructions ne sont pas valides")
            Array[String]()
          }
        }
        case 2 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            chars
          } else {
            System.out.println("Fichier invalide : La grille est mal definie")
            Array[String]()
          }
        }
        case 3 => {
          if (isPositive(chars(0)) && isPositive(chars(1))) {
            if (isValidDirection(chars(2))) {
              chars
            } else {
              System.out.println(
                "Fichier invalide : La direction de la tondeuse est mal définie"
              )
              Array[String]()
            }
          } else {
            System.out.println(
              "Fichier invalide : Les coordonnees de la tondeuse sont mal définies"
            )
            Array[String]()
          }
        }
        case _ => {
          System.out.println("Erreur : Le fichier ne correspond a rien")
          Array[String]()
        }
      }
    }
  }

  def getData(): Array[String] = {
    val lines = Source.fromFile(path).getLines().toArray
    if (lines.length % 2 == 0) {
      System.out.println("Fichier Invalide : Il manque des instructions")
    } else if (lines.length < 2) {
      System.out.println("Fichier Invalide : Il n'y a aucune tondeuse")
    } else {
      System.out.println("Fichier valide")
    }
    lines
  }
}
