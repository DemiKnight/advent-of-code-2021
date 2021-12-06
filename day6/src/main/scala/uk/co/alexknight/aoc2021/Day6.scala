package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.{Try, Using}
import scala.collection.parallel.CollectionConverters._

object Day6 extends App {

  val file: Seq[Int] = Using(Source.fromResource("input.txt"))(_
    .getLines()
    .flatMap(_
      .split(",")
      .map(_.toInt))
    .toSeq).getOrElse(Nil)

  val result = Range(0,80).par.foldLeft(file.par) { (acc, index: Int) =>
    val output = acc.flatMap {
      case 0 => Seq(6, 8)
      case tick =>
        Seq(tick-1)
    }
    println(s"Day $index -> ${output.length}")
    output
  }

  println(result.length)
}
