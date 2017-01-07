name := "bitcoin4s"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += Classpaths.sbtPluginReleases

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "com.typesafe.akka" % "akka-http_2.11" % "10.0.1",
  "com.typesafe.akka" % "akka-http-core_2.11" % "10.0.1"
)

scalacOptions in Test ++= Seq("-Yrangepos")

