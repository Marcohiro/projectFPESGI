package main.scala.projetal2020.Classes

import play.api.libs.json._

class ResWriter(
    init: Array[String],
    mowersStart: Array[Mower],
    instructions: Array[String],
    mowersEnd: Array[Mower]
) {

  val initW = init
  val data = mowersStart.zip(mowersEnd).zip(instructions)

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
                  "x" -> el._1._1.abscissa,
                  "y" -> el._1._1.ordinate
                ),
                "direction" -> el._1._1.direction
              ),
              "instructions" -> el._2.split(""),
              "fin" -> Json.obj(
                "point" -> Json.obj(
                  "x" -> el._1._2.abscissa,
                  "y" -> el._1._2.ordinate
                ),
                "direction" -> el._1._2.direction
              )
            )
        )
    )
    res
  }
}
