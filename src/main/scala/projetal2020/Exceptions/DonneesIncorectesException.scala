package main.scala.projetal2020.Exceptions

class DonneesIncorectesException(message: String) extends Exception(message: String){

  def this(message: String, cause: Throwable) {
    this(message)
    initCause(cause)
  }

  def this(cause: Throwable) {
    this(Option(cause).map(_.toString).orNull, cause)
  }

  def this() {
    this(null: String)
  }

}
