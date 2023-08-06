package org.idiot.driven.dev

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import java.io.File

import upickle.default.*

/*
  UserService object provides of User data and auth manipulation
*/
object UserService {

  //  Generates id for new User cases based on .json files count
  private def generateUserId(): Int =

    //  Init dir with user data
    val dir = new File(s"${System.getProperty("user.dir")}/data")

    //  Getting files from dir and counting .json files number
    dir.listFiles
      .filter(_.isFile)
      .toList
      .count(_.getName.endsWith(".json")) + 1

  private def generatePassId(username: String): Int =
    val fileName = s"${System.getProperty("user.dir")}/data/$username.json"
    if read[User](FileIO.readFile(fileName)).userData.nonEmpty then
      read[User](FileIO.readFile(fileName)).userData.maxBy(_.id).id + 1
    else
      return 1

  //  Creating new user account if it not exists yet
  def signUp(username: String, password: String): User =

    //  Init name of file with data of given user
    val fileName = s"${System.getProperty("user.dir")}/data/$username.json"

    //  Checking if file exists
    if !new File(fileName).exists() then

      //  Creating new User case with given username and password. Init empty userData ListBuffer
      val user = User(id = generateUserId(), username = username, password = password, userData = new ListBuffer[Password])

      //  Converting user case to JSON and writing it to file
      FileIO.writeFile(fileName, write(user))
      return user

    // If user account file already exists, return null
    return null

  def signIn(username: String, password: String): User =
    val fileName = s"${System.getProperty("user.dir")}/data/$username.json"

    if new File(fileName).exists() && read[User](FileIO.readFile(fileName)).password == password then
      return read[User](FileIO.readFile(fileName))
    return null

  def createPassword(u: User, title: String, data: String): User =

    val fileName = s"${System.getProperty("user.dir")}/data/${u.username}.json"

    if new File(fileName).exists() then
      u.userData += Password(id = generatePassId(u.username), title = title, data = data)
      FileIO.writeFile(fileName, write(u))
      return read[User](FileIO.readFile(fileName))
    u

  def removePassword(u: User, passToRemove: Password): User =
    val fileName = s"${System.getProperty("user.dir")}/data/${u.username}.json"

    if new File(fileName).exists() then
      u.userData -= passToRemove
      FileIO.writeFile(fileName, write(u))
      return read[User](FileIO.readFile(fileName))
    u

  def editPassword(u: User, passToEdit: Password, newPass: Password): User =
    val fileName = s"${System.getProperty("user.dir")}/data/${u.username}.json"

    if new File(fileName).exists() then
      u.userData(passToEdit.id - 1) = Password(id = passToEdit.id, title = newPass.title, data = newPass.data)
      FileIO.writeFile(fileName, write(u))
      return read[User](FileIO.readFile(fileName))
    u
}
