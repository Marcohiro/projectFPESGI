package main.scala.projetal2020.Classes

import play.api.libs.json._

class ErrorWriter(cause: String) {

  val reason = cause

  def write(): JsValue = {
    val res: JsValue = Json.obj(
      "Erreur" -> Json.obj(
        "Cause" -> reason
      )
    )
    res
  }
}
