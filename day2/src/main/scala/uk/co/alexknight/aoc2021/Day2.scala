package uk.co.alexknight.aoc2021

import scala.io.Source

object Day2 extends App {
  val baseFile: Seq[String] = Source
    .fromResource("xxx.txt")
    .getLines
    .toSeq
}
