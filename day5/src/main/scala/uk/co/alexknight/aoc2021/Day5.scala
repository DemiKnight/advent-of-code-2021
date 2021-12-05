package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.{Try, Using}

object Day5 extends App {
  case class Point(x: Int, y: Int)
  case class Vector(p1: Point, p2: Point)

  val rawFile: Seq[String] = Using(Source.fromResource("test.txt"))(_.getLines().toSeq).getOrElse(Nil)
  val vectorList: Seq[Vector] = rawFile.map { line =>
    val strPoints: Seq[Point] = line.split(" -> ").map(_.split(",").map(_.toInt)).toSeq.map { points =>
      Point(points.head, points.last)
    }
    Vector(strPoints.head, strPoints.last)
  }
  println(vectorList)
}
