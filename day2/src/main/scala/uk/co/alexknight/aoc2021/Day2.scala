package uk.co.alexknight.aoc2021

import scala.io.Source

object Day2 extends App {
  case class Instruction(direction: String, amount: Int)
  case class Position(depth: Int, horizontal: Int)

  val baseFile: Seq[Instruction] = Source
    .fromResource("input.txt")
    .getLines
    .toSeq
    .map { str =>
    val parts = str.split(" ")
    Instruction(parts(0), parts(1).toInt)
  }

  val result: Position = baseFile.foldLeft(Position(0,0)) { (acc, instr) =>
    instr match {
      case Instruction("forward", amount) =>
        acc.copy(horizontal = acc.horizontal + amount)
      case Instruction("down", amount) =>
        acc.copy( depth=acc.depth + amount)
      case Instruction("up", amount) =>
        acc.copy( depth=acc.depth - amount)
    }
  }

  println(s"Part1: ${result.depth * result.horizontal}")
}
