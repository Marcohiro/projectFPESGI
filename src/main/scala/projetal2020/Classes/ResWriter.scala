package main.scala.projetal2020.Classes

import play.api.libs.json._

class ResWriter(
    init: Array[String],
    mowersStart: Array[Mower],
    mowersEnd: Array[Mower]
) {

  val initW = init
  val data: Array[Array[Mower]] = Array(mowersStart, mowersEnd)

  def write(): JsValue = {
    val res: JsValue = Json.obj(
      "Limite" -> Json.obj(
        "x" -> init(0),
        "y" -> init(1)
      ),
      "Tondeuses" ->
        data.map(
          el =>
            Json.obj(
              "debut" -> Json.obj(
                "point" -> Json.obj(
                  "x" -> el(0).abscissa,
                  "y" -> el(0).ordinate
                ),
                "direction" -> el(0).direction
              ),
              "instructions" -> el(0).orders,
              "fin" -> Json.obj(
                "point" -> Json.obj(
                  "x" -> el(1).abscissa,
                  "y" -> el(1).ordinate
                ),
                "direction" -> el(1).direction
              )
            )
        )
    )
    res
  }
}
