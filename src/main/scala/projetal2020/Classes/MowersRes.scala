package main.scala.projetal2020.Classes
import play.api.libs.json.{Json, Writes}

case class MowersRes(Start: Mower, Instructions: String, End: Mower) {}

object MowersRes {

  implicit val writes: Writes[MowersRes] = Json.writes[MowersRes]

}
