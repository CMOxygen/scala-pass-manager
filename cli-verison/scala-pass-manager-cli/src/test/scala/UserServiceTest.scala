package org.idiot.driven.dev

import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import upickle.default.*


class UserServiceTest extends AnyFunSuite {

  test("UserService Test signUp") {
    val u = UserService.signUp("user1", "pass1")
    assert(u != null)
    assert(u == read[User](FileIO.readFile(s"${System.getProperty("user.dir")}/data/user1.json")))
  }

  test("UserService Test signIn") {
    val u = UserService.signIn("user1", "pass1")
    assert(u != null)
    assert(u == read[User](FileIO.readFile(s"${System.getProperty("user.dir")}/data/user1.json")))

    println(u)
  }

  test("UserService Test createPassword") {
    val u = UserService.signIn("user1", "pass1")
    println(u)
    println(UserService.createPassword(u, "title1", "data1"))
  }

  test("UserService Test removePassword") {
    val u = UserService.signIn("user1", "pass1")
    println(u)
    println(UserService.removePassword(u, u.userData.last))
  }

  test("UserService Test editPassword") {
    val u = UserService.signIn("user1", "pass1")
    println(u)
    println(UserService.editPassword(u, u.userData.last, Password(id = u.userData.last.id, title = "title2", data = "data2")))
  }
}
