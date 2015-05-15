name := "bitcoin4s"

version := "1.0"

scalaVersion := "2.11.6"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies ++= Seq(
  "com.propensive" %% "rapture-json-json4s" % "1.1.0",
  "org.specs2" %% "specs2-core" % "3.6" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")

