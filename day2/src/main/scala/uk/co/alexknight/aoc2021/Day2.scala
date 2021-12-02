package uk.co.alexknight.aoc2021

import scala.io.Source

object Day2 extends App {
  case class Instruction(direction: String, amount: Int)
  case class Position(depth: Int, horizontal: Int, aim: Int=0)

  val baseFile: Seq[Instruction] = Source
    .fromResource("input.txt")
    .getLines
    .toSeq
    .map { str =>
    val parts = str.split(" ")
    Instruction(parts(0), parts(1).toInt)
  }

  val result1: Position = baseFile.foldLeft(Position(0,0)) { (acc, instr) =>
    instr match {
      case Instruction("forward", amount) =>
        acc.copy(horizontal = acc.horizontal + amount)
      case Instruction("down", amount) =>
        acc.copy( depth=acc.depth + amount)
      case Instruction("up", amount) =>
        acc.copy( depth=acc.depth - amount)
    }
  }
  println(s"Part1: ${result1.depth * result1.horizontal}")

  val result2: Position = baseFile.foldLeft(Position(0,0,0)) { (acc, instr) =>
    instr match {
      case Instruction("forward", amount) =>
        acc.copy(horizontal = acc.horizontal + amount, depth = acc.depth + (acc.aim*amount))
      case Instruction("down", amount) =>
        acc.copy(aim = acc.aim + amount)
      case Instruction("up", amount) =>
        acc.copy(aim = acc.aim - amount)
    }
  }
  println(s"Part2: ${result2.depth*result2.horizontal} from $result2")
}
