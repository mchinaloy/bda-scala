name := "BigDataAggregatorScala"
version := "1.0"
scalaVersion := "2.11.7"

val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"

libraryDependencies += scalaTest
libraryDependencies += scalaMock
