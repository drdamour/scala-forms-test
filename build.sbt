name := "formstest"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  cache,
  "com.typesafe.slick" %% "slick" % "1.0.1",
  "com.h2database" % "h2" % "1.3.166",
  "com.typesafe.play" %% "play-slick" % "0.5.0.8"
)     

play.Project.playScalaSettings
