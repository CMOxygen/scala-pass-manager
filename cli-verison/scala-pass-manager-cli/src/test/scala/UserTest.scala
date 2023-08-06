package org.idiot.driven.dev

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable

class UserTest extends AnyFunSuite {

  test("User Test Init") {
    val user = User(id = 1, username = "debug_user1", password = "debug_user1", userData = null)
    assert(user.id == 1)
    assert(user.username == "debug_user1")
    assert(user.password == "debug_user1")
    assert(user.userData == null)
  }

  test("User Test Copy") {
    val user = User(id = 1, username = "debug_user1", password = "debug_user1", userData = null)
    val userCopy = user.copy(id = 2, username = "debug_user2", password = "debug_user2")

    println(user)
    println(userCopy)
    assert(user != userCopy)
  }

  test("User Test Append userData") {
    val user = User(id = 1, username = "debug_user1", password = "debug_user1", userData = new mutable.ListBuffer[Password])

    for i <- 1 to 10 do
      user.userData += Password(id = i, title = s"debug_title$i", data = s"debug_data$i")

    println(user)
  }
}
