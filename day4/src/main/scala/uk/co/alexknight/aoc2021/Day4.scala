package uk.co.alexknight.aoc2021

import scala.io.Source
import scala.language.implicitConversions
import scala.util.Using

object Day4 extends App {
  type BingoCard = Seq[Seq[Int]]

  case class WinningCard(card: Option[BingoCard] = None, winningNumbs: Seq[Int] = Seq.empty)

  case class BingoCardFinder(outstandingCards: Seq[BingoCard], foundCards: Seq[BingoCard] = Nil, winningNums: Seq[Int] = Nil)

  def hasWon(board: BingoCard, winningNumbers: Seq[Int]): Boolean = {
    val rowComplete: Boolean = board.exists { row: Seq[Int] =>
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

  def getScore(board: BingoCard, numbers: Seq[Int]): Int = {
    val flatBoardSum = board.foldLeft(Seq.empty[Int])((acc, value) => acc ++ value).diff(numbers).sum
    numbers.last * flatBoardSum
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

  val result1Mirror: BingoCardFinder = numberCalls.foldLeft(BingoCardFinder(bingoCards)) { (acc, value) =>
    if (acc.outstandingCards.isEmpty) {
      acc
    } else {
      val newNumberCalls: Seq[Int] = acc.winningNums :+ value

      val (found: Seq[BingoCard], outstanding: Seq[BingoCard]) = acc.outstandingCards.partition(hasWon(_, newNumberCalls))

      acc.copy(winningNums = newNumberCalls,
        outstandingCards = outstanding,
        foundCards = acc.foundCards ++ found
      )
    }
  }

  val result1Card: WinningCard = numberCalls.foldLeft(WinningCard()) { (acc, number: Int) =>
    if (acc.card.isDefined) {
      acc
    } else {
      val numSeq = acc.copy(winningNumbs = acc.winningNumbs :+ number)
      val result: Option[BingoCard] = bingoCards.find(hasWon(_, numSeq.winningNumbs))
      numSeq.copy(card = result)
    }
  }

  val result1 = result1Card.card.map { card: BingoCard =>
    getScore(card, result1Card.winningNumbs)
  }

  println(s"Part1: $result1}")

  val result2 = 2
  println(s"Part2: ${getScore(result1Mirror.foundCards.last, result1Mirror.winningNums)}")
}
