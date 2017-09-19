package com.fjun.demo.impatient.exercises

import scala.collection.immutable.StringOps

object Chapter2 {
  def main(args: Array[String]): Unit = {
    countdown(10)
    println(unicodeMultiply("Hello"))
    println(stringOps("Hello"))
    println(multiplyStr("Hello"))
    println(power(10, 3))
    println(max("Hello"))
  }

  def countdown(n: Int) {
    for (i <- n to 0 by -1) print(i + " ")
    println
  }

  def unicodeMultiply(str: String): Long = {
    if (str == null || str.length <= 0) return 0L
    var result: Long = 1L
    for (c <- str) result *= c
    result
  }

  def stringOps(str: String): Long = {
    if (str == null || str.length <= 0) return 0L
    str.foldLeft(1L)(_*_)
  }

  def multiplyStr(str: String): Long = {
    if (str == null || str.length <= 0) return 0L
    if (str.length == 1)  str.head.toLong
    else str.head.toLong * multiplyStr(str.tail)
  }

  def power(x:Double, n:Int):Double = {
    println(x + "***" + n)
    if (n == 0) 1
    else if (n > 0 && n % 2 == 0) {
      val a:Double = power(x, n / 2)
      a * a
    }
    else if (n > 0 && n % 2 != 0) x * power(x, n - 1)
    else 1 / power(x, -n)
  }

  def max(str: String): Char = {
    str.reduce((x,y) => if (x > y) x else y)
  }
}
