import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / organization     := "uk.co.alexknight"
ThisBuild / organizationName := "alexknight"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2021",
    libraryDependencies ++= Seq(
      scalaTest % Test,

    )
  ).aggregate(day_1)

lazy val day_1 = (project in file("day1-1"))
  .settings(
    name := "day1-1"
  )

lazy val day_2 = (project in file("day2"))
  .settings(name := "day2")
