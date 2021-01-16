package main.scala.projetal2020.Classes

import main.scala.projetal2020.Exceptions.DonneesIncorectesException

import scala.util.{Success, Try}
import play.api.libs.json.{Json, Writes}

case class Coordinate(x: Int, y: Int)

object Coordinate {

  def toInt(value: String): Option[Int] = {
    try {
      Some(Integer.parseInt(value))
    } catch {
      case _: Throwable => None
    }
  }

  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def apply(limit: List[String]): Try[Coordinate] = {
    limit match {
      case List() =>
        throw new DonneesIncorectesException(
          "Erreur : les coordonnées ne doivent pas être vides"
        )
      case head :: tail :: List() => {
        toInt(head) match {
          case None =>
            throw new DonneesIncorectesException(
              "Erreur : l'abscisse des coordonnées n'est pas définie"
            )
          case Some(i) =>
            if (i > 0) toInt(tail) match {
              case None =>
                throw new DonneesIncorectesException(
                  "Erreur : l'ordonnée des coordonnées n'est pas définie"
                )
              case Some(j) =>
                if (j > 0) Success[Coordinate](new Coordinate(i, j))
                else {
                  throw new DonneesIncorectesException(
                    "Erreur : l'ordonnée de la coordonnée est invalide : Nulle ou négative"
                  )
                }
            }
            else {
              throw new DonneesIncorectesException(
                "Erreur : l'abcsisse de la coordonnée est invalide : Nulle ou négative"
              )
            }
        }
      }
      case _ =>
        throw new DonneesIncorectesException(
          "Erreur : la limite doi avoir exactement 2 caractères"
        )
    }
  }

  implicit val writes: Writes[Coordinate] = Json.writes[Coordinate]

}
