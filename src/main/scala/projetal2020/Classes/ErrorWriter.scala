package main.scala.projetal2020.Classes

import play.api.libs.json.{Json, Writes}

case class ErrorWriter(cause: String) {}

object ErrorWriter {

  implicit val writes: Writes[ErrorWriter] = Json.writes[ErrorWriter]

}
