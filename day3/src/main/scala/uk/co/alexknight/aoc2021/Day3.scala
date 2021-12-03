package uk.co.alexknight.aoc2021

import scala.io.Source

object Day3 extends App {
  case class Position(zeroCount: Int = 0, oneCount: Int = 0)

  case class Result(gammaRate: String, epsilonRate: String)

  val baseFile: Seq[String] = Source.fromResource("input.txt").getLines().toSeq

  val result1 = baseFile
    .foldLeft(Seq.fill(baseFile.head.length)(Position())) { (acc, value) =>
      value.zipWithIndex.map {
        case ('1', i) =>
          acc(i).copy(oneCount = acc(i).oneCount + 1)
        case ('0', i) =>
          acc(i).copy(zeroCount = acc(i).zeroCount + 1)
      }
    }.foldLeft(Result("", "")) { (acc, value) =>
    if (value.oneCount < value.zeroCount) {
      acc
        .copy(gammaRate = acc.gammaRate + '0',
          epsilonRate = acc.epsilonRate + '1')
    } else acc
      .copy(gammaRate = acc.gammaRate + '1',
        epsilonRate = acc.epsilonRate + '0')
  }
  val result1Int = Integer.parseInt(result1.gammaRate, 2) * Integer.parseInt(result1.epsilonRate, 2)

  println(s"Part 1: ${result1Int} / $result1")
}
