package org.idiot.driven.dev

import java.io.{File, FileWriter, IOException}
import scala.io.Source
import scala.util.Try
//import java.io.IOException

import scala.io

object FileIO {

  def writeFile(fileName: String, data: String): Unit =

    Try {
      val file = new File(fileName)

      if !file.exists() then
        file.createNewFile()

      val fileWriter = new FileWriter(file)
      fileWriter.write(data)
      fileWriter.close()
    }.toEither match
      case Left(ex) => println(ex.getMessage)
      case Right(_) =>

  def readFile(fileName: String): String =
    var str = ""
    Try {
      val bufferedSource = Source.fromFile(fileName)
      str = bufferedSource.mkString("")
      bufferedSource.close()
    }.toEither match
      case Left(ex) =>
        println(ex.getMessage)
        null
      case Right(_) => str
}
