name := """ScalaTutorial"""

version := "1.0"

scalaVersion := "2.11.1"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.jsoup" % "jsoup" % "1.8.2",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "junit" % "junit" % "4.11" % "test"
)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % "2.3.3"

