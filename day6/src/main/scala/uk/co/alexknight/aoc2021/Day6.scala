package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.{Try, Using}

object Day6 extends App {

  val rawFile: Seq[String] = Using(Source.fromResource("test.txt"))(_.getLines().toSeq).getOrElse(Nil)

  
}
