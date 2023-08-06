package org.idiot.driven.dev

import scala.collection.mutable
import upickle.default.*

case class User(id: Int = 0, username: String = null, password: String = null, userData: mutable.ListBuffer[Password] = null)derives ReadWriter {
//    require(id > 0 && username.nonEmpty && password.nonEmpty)
//    require(3 <= username.length && username.length <= 16)
//    require(8 <= password.length && password.length <= 16)

  override def toString: String =
    s"""User
       |  id: $id
       |  username: $username
       |  password: $password
       |  Password List:
       |    ${if userData != null then userData.mkString("\n\t") else null}
       |""".stripMargin
}
