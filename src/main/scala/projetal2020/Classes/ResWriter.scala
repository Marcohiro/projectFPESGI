package main.scala.projetal2020.Classes

import play.api.libs.json.{Json, Writes}

case class ResWriter(
    Limit: Coordinate,
    Mowers: List[MowersRes]
) {}

object ResWriter {

  implicit val writes: Writes[ResWriter] = Json.writes[ResWriter]

}
