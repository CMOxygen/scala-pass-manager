package org.idiot.driven.dev

import upickle.default.*

case class Password(id: Int = 0, title: String, data: String)derives ReadWriter {
  require(title.nonEmpty && data.nonEmpty)
  require(title.length <= 512)
  require(1 <= data.length && data.length <= 512)

  override def toString: String =
    s"\n\tid: $id\ttitle: $title\n\tdata: $data"
}
