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

  case class LifeSupport(remainingOx: Seq[String] = Nil, remainingCO2: Seq[String] = Nil)

  def createMask(base: Seq[String]): Result = {
    base.foldLeft(Seq.fill(baseFile.head.length)(Position())) { (acc, value) =>
      value.zipWithIndex.map {
        case ('1', i) =>
          acc(i).copy(oneCount = acc(i).oneCount + 1)
        case ('0', i) =>
          acc(i).copy(zeroCount = acc(i).zeroCount + 1)
      }
    }.foldLeft(Result("", "")) { (acc, value) =>
      if(value.oneCount == value.zeroCount) {
        acc
          .copy(gammaRate = acc.gammaRate + '1',
            epsilonRate = acc.epsilonRate + '0')
      }else if (value.oneCount < value.zeroCount) {
        acc
          .copy(gammaRate = acc.gammaRate + '0',
            epsilonRate = acc.epsilonRate + '1')
      } else acc
        .copy(gammaRate = acc.gammaRate + '1',
          epsilonRate = acc.epsilonRate + '0')
    }
  }



  val result2 = baseFile.head.indices.foldLeft(LifeSupport(baseFile, baseFile)) { (acc, index) =>

    val resultMaskOx = createMask(acc.remainingOx).gammaRate
    val resultMaskCO2 = createMask(acc.remainingCO2).epsilonRate

    val (foundOx, _) = if(acc.remainingOx.length != 1) {
      acc.remainingOx.partition(str => str(index) == resultMaskOx(index))
    } else (acc.remainingOx, Nil)
    val (foundCO2, _) = if(acc.remainingCO2.length != 1) {
      acc.remainingCO2.partition(str => str(index) == resultMaskCO2(index))
    } else (acc.remainingCO2, Nil)

    acc.copy(remainingOx = foundOx, remainingCO2 = foundCO2)
  }

  val result2Int = Integer.parseInt(result2.remainingCO2.head, 2) * Integer.parseInt(result2.remainingOx.head, 2)

  println(s"Part 2: $result2Int / $result2")
}
