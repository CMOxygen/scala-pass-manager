package org.idiot.driven.dev

import scala.io.StdIn

class ConsoleUI {

  def authMenu(): Unit =
    var input = true

    while input do
      println(
        """
          |Welcome!
          |type /signup to sign up
          |type /signin to sign in
          |""".stripMargin)
      StdIn.readLine().filter(_ != ' ').toLowerCase() match
        case "/signup" =>
          signUpMenu()
          input = false
        case "/signin" =>
          signInMenu()
          input = false

  private def signUpMenu(): Unit =
    println("Signing Up...")
    var u: User = null

    while u == null do
      u = usernamePassInput()
      u = UserService.signUp(u.username, u.password)

      if u == null then println("Account with this username is already exists!")
    println("SUCCESS")

  private def signInMenu(): Unit =
    println("Signing In...")
    var u: User = null

    while u == null do
      u = usernamePassInput()
      u = UserService.signIn(u.username, u.password)

      if u == null then println("Account with this username is not exists!")
    println("SUCCESS")
    userHomeMenu(u)

  private def usernamePassInput(): User =

    println("Enter username")
    val username = StdIn.readLine().filter(_ != ' ')
    println("Enter password")
    val password = StdIn.readLine().filter(_ != ' ')
    User(username = username, password = password)

  private def userHomeMenu(u: User): Unit =
    println("User home menu")

    var input = true
    var userData = u.copy()

    while input do
      println(userData)
      println(
        """
          |Type /add to add new password
          |Type /edit to edit password
          |Type /rm to remove password
          |Type /exit to exit
          |""".stripMargin)

      StdIn.readLine().toLowerCase().filter(_ != ' ') match
        case "/add" => userData = addPassMenu(userData)
        case "/edit" => userData = editPassMenu(userData)
        case "/rm" => userData = removePassMenu(userData)
        case "/exit" => input = false

  private def titleDataInput(): Password =
    println("Input title of Password")
    val title = StdIn.readLine()
    println("Input data of Password")
    val data = StdIn.readLine()
    Password(title = title, data = data)

  private def addPassMenu(u: User): User =
    println("Add Password")
    val p = titleDataInput()
    UserService.createPassword(u, p.title, p.data)

  private def editPassMenu(u: User): User =
    println("Edit Password\n")
    println("Enter id of password to edit.")
    val id = StdIn.readInt()
    var p = titleDataInput()
    p = p.copy(id = id)
    UserService.editPassword(u, u.userData(p.id - 1), p)

  private def removePassMenu(u: User): User =
    println("Remove Password\n")
    println("Enter id of password to remove.")
    val id = StdIn.readInt()
    UserService.removePassword(u, u.userData(id - 1))
}


