package org.idiot.driven.dev

import org.scalatest.funsuite.AnyFunSuite

class PasswordTest extends AnyFunSuite {

  test("Password Test Init") {
    val pass = Password(id = 1, title = "debug_title1", data = "debug_password1")
    assert(pass.id == 1)
    assert(pass.title == "debug_title1")
    assert(pass.data == "debug_password1")
  }
}
