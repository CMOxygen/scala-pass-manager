package org.idiot.driven.dev

import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import scala.util.Try
import scala.collection.mutable
import upickle.default.*

class FileIOTest extends AnyFunSuite {

  val testingData = "DEBUG SUCCESSFUL"
  val testingFileName = s"${System.getProperty("user.dir")}/data/debug.txt"

  test("FileIO Test writeFile") {
    FileIO.writeFile(testingFileName, testingData)
    println(System.getProperty("user.dir"))
    assert(new File(testingFileName).exists())
  }

  test("FileIO Test readFile") {
    val str = FileIO.readFile(s"${System.getProperty("user.dir")}/data/debug.txt")
    println(str)
    assert(str == testingData)
  }

  test("JSON Test") {
    // Creating name of test file
    val fileName = os.pwd / "data" / "testing.txt"

    // Init User as test input
    val user = User(id = 1, username = "debug_user1", password = "debug_password1", userData = new mutable.ListBuffer[Password])

    // Fill userData with Password cases
    for i <- 1 to 10 do
      user.userData += Password(id = i, title = s"debug_title$i", data = s"debug_data$i")

    // Converting user case to JSON and writing it to file
    val userToJson = write(user)
    FileIO.writeFile(fileName.toString, userToJson)
    assert(new File(fileName.toString).exists())

    //Reading a previously written file and write data to user2 case
    val user2 = read[User](FileIO.readFile(fileName.toString))
    assert(user == user2)
  }
}