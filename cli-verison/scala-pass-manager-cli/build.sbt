ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.0"

lazy val root = (project in file("."))
  .settings(
    name := "scala-pass-manager-cli",
    idePackagePrefix := Some("org.idiot.driven.dev")
  )
libraryDependencies += "org.scala-lang" %% "toolkit" % "0.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % Test
