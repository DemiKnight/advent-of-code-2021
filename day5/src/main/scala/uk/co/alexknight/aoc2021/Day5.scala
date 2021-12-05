package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.Using

object Day5 extends App {
  case class Point(x: Int, y: Int)
  case class Vector(p1: Point, p2: Point)

  def materialiseVector(vec: Vector): Seq[Point] = {
    val yStep = if (vec.p1.y > vec.p2.y) -1 else 1
    val xStep = if (vec.p1.x > vec.p2.x) -1 else 1

    val yRange = vec.p1.y to vec.p2.y by yStep
    val xRange = vec.p1.x to vec.p2.x by xStep
    val horizontalLines = if(vec.p1.y == vec.p2.y || vec.p1.x == vec.p2.x) {
      val betweenPoints = for {
        x <- xRange
        y <- yRange
      } yield Point(x,y)
      betweenPoints
    } else {
      yRange.zip(xRange).map { case (yIndex, xIndex) =>
        Point(xIndex, yIndex)
      }
    }
    (horizontalLines ++ Seq(vec.p1,vec.p2)).distinct
  }

  // Load data
  val rawFile: Seq[String] = Using(Source.fromResource("input.txt"))(_.getLines().toSeq).getOrElse(Nil)

  val vectorList = rawFile.map {
    case s"$xPoint1,$yPoint1 -> $xPoint2,$yPoint2" =>
      val point1 = Point(xPoint1.toInt, yPoint1.toInt)
      val point2 = Point(xPoint2.toInt, yPoint2.toInt)
      Vector(point1,point2)
  }

  // Convert to vectors
  val materialisePoints: Seq[Point] = vectorList.flatMap(materialiseVector)
  val plottedGraph = materialisePoints.foldLeft(Array.ofDim[Int](1000, 1000)) { (acc, pnt) =>
    acc(pnt.x)(pnt.y) += 1
    acc
  }

  val highPoints = plottedGraph.flatten.filter(_ >= 2).toSeq
  println(highPoints.length)
}
