package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.util.{Try, Using}

import scala.language.implicitConversions

object Day4 extends App {
  type BingoCard = Seq[Seq[Int]]
  case class WinningCard(card: Option[BingoCard] = None, winningNumbs: Seq[Int] = Seq.empty)

  def hasWon(board: BingoCard, winningNumbers: Seq[Int]): Boolean = {
    val rowComplete: Boolean = board.contains { row: Seq[Int] =>
      row.diff(winningNumbers).isEmpty
    }

    val columnComplete: Boolean = board.indices.map { index =>
      board
        .map(seq => seq(index))
        .diff(winningNumbers)
        .isEmpty
    }.reduce(_ || _)

    rowComplete || columnComplete
  }
  // Load data
  val arrayDepth = 5
  val file: Seq[String] = Using(Source.fromResource("input.txt"))(_.getLines.toSeq).getOrElse(Nil)
  val numberCalls: Seq[Int] = file.head.split(",").map(_.toInt).toSeq
  val bingoCards: Seq[BingoCard] = file.drop(2).filterNot(_ == "").grouped(arrayDepth).map((board: Seq[String]) =>
    board.map { boardLine: String =>
      boardLine
        .split(" ")
        .filter(_.nonEmpty)
        .map(_.toInt)
        .toSeq
    }
  ).toSeq

  val result1Card: WinningCard = numberCalls.foldLeft(WinningCard()) { (acc, number: Int) =>
    if(acc.card.isDefined) {
      acc
    } else {
      val numSeq = acc.copy(winningNumbs = acc.winningNumbs :+ number)
      val result: Option[BingoCard] = bingoCards.find(hasWon(_, numSeq.winningNumbs))
      numSeq.copy(card = result)
    }
  }

  val result1 = result1Card.card.map { card: BingoCard =>
    val numbers: Seq[Int] = card
      .foldLeft(Seq.empty[Int])((acc: Seq[Int], value: Seq[Int]) => acc ++ value)
    val sumOfUnmarked: Int = numbers.diff(result1Card.winningNumbs).sum
    result1Card.winningNumbs.last * sumOfUnmarked
  }

  println(s"Part1: $result1 / $result1Card")

  val result2 = 2
  println(s"Part2: ")
}
