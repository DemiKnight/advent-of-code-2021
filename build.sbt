import Dependencies._

ThisBuild / scalaVersion     := "2.13.7"
ThisBuild / organization     := "uk.co.alexknight"
ThisBuild / organizationName := "alexknight"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2021",
    libraryDependencies ++= Seq(
      scalaTest % Test
    ),
    scalacOptions ++= Seq(
      "-Xfatal-warnings",
      "-deprecation",
      "-unchecked",
    )
  ).aggregate(day_1, day_2, day_3, day_4, day_5, day_6)

lazy val day_1 = (project in file("day1-1"))
  .settings(
    name := "day1"
  )

lazy val day_2 = (project in file("day2"))
  .settings(name := "day2")

lazy val day_3 = (project in file("day3"))
  .settings(name := "day3")

lazy val day_4 = (project in file("day4")).settings(name := "day4")

lazy val day_5 = (project in file("day5")).settings(name := "day5")

lazy val day_6 = (project in file("day6"))
  .settings(
    name := "day6",
    libraryDependencies ++= Seq(
      "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"
    )
  )