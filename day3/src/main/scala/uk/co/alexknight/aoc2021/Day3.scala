package uk.co.alexknight.aoc2021

import scala.io.Source

object Day3 extends App {
  val baseFile: Seq[String] = Source.fromResource("input.txt").getLines().toSeq

  println(s"Part 1: $baseFile")
}
