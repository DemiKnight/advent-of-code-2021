package uk.co.alexknight.aoc2021

import scala.io.Source

object Day1_1 extends App {
  // Load file & convert to an array of integers 
  val baseFile: Seq[Int] = Source
    .fromResource("depth.txt")
    .getLines
    .map(_.toInt).toSeq

  // To do this, count the number of times a depth measurement increases from the previous measurement.
  val part1 = baseFile
    .sliding(2)
    .count(num => num.head < num(1))

  // Instead, consider sums of a three-measurement sliding window.
  //Your goal now is to count the number of times the sum of measurements in this sliding window increases
  val part2 = baseFile
    .sliding(3)
    .map(_.sum)
    .sliding(2)
    .count(num => num.head < num(1))

  println(s"part1: $part1\nPart2: $part2")
}
