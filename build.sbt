name := "scala-cats-intro"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.12"

idePackagePrefix := Some("dev.khusanjon")

val catsVersion: String = "2.9.0"

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % catsVersion
)

scalacOptions ++= Seq(
  "-language:higherKinds"
)
