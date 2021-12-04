package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.{Try, Using}

object Day4 extends App {

  val file: Seq[String] = Using(Source.fromResource("input.txt"))(_.getLines.toSeq).getOrElse(Nil)

  val result1 = ???

  println(s"Part1: ${result1}")
}
