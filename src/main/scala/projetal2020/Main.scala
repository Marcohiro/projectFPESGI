package projetal2020

import main.scala.projetal2020.Classes.{
  DataParser,
  ErrorWriter,
  FileParser,
  ResWriter
}
import main.scala.projetal2020.Exceptions.DonneesIncorectesException
import better.files._
import play.api.libs.json._

object Main extends App {

  //On initialise tout d'abord le lecteur du fichier
  val fileParser = new FileParser(
    "C:\\Users\\petit\\OneDrive\\Bureau\\SCALA test\\valide.txt"
  )

  //On met un bloc try en cas d'exception levee
  try {
    //Si aucune erreur n'est detectee:
    //-S'il manque aucune ligne
    //-Si des nombres négatifs ont été renseignés
    // alors le lecteur du fichier renvoie une liste
    //de string contenant les informations à analyser
    val data = fileParser.getData()

    //On analyse ici les string et on détermine si aucune erreur n'est detectée :
    //-Si la tondeuse sort du terrain
    val dataParser = new DataParser(data)

    //On prépare ici l'écriture du résultat
    val resWriter = new ResWriter(
      dataParser.getInit(),
      dataParser.getMowersRes()
    )

    //On prépare ici l'écriture dans un fichier
    val outputFile: File = File(
      "C:\\\\Users\\\\petit\\\\OneDrive\\\\Bureau\\\\SCALA test\\\\output6.json"
    )

    //On écrit enfin le résultat dans le fichier
    outputFile
      .createIfNotExists()
      .write(Json.prettyPrint(ResWriter.writes.writes(resWriter)))

    ()
  } catch {
    //Si un exception DonneesIncorectesException a été levée
    case donneesIncorectesException: DonneesIncorectesException =>
      //Alors on écrit dans le fichier du résultat quelle est l'erreur
      val errorWritter = new ErrorWriter(donneesIncorectesException.getMessage)
      val outputFile: File = File(
        "C:\\\\Users\\\\petit\\\\OneDrive\\\\Bureau\\\\SCALA test\\\\output6.json"
      )
      outputFile
        .createIfNotExists()
        .appendLine(Json.stringify(errorWritter.write()))
      ()
  }
}
