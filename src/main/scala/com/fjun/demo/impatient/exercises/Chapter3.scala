package com.fjun.demo.impatient.exercises

import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer
import scala.util.Random

/**
  * <p>
  * <br> =========================
  * <br> 公司：阿里移动事业群-国际业务部-国际研发部
  * <br> 系统：U盟业务线-中台系统
  * <br> @author：陈飞君（feijun.cfj@alibaba-inc.com）
  * <br> @date： 2017-09-14
  * <br> =========================
  */
object Chapter3 {
  def main(args: Array[String]): Unit = {
    arrayBufferTest()
    println("*" * 20)
    printArray(randomPositiveArray(100))
    println
    println("*" * 20)
    val array:Array[Int] = randomPositiveArray(17)
    printArray(array)
    swapArray(array)
    printArray(array)
    println("*" * 20)
    printArray(swapNewArray(array))
    println("*" * 20)
    val newArray:Array[Int] = randomArray(12)
    printArray(newArray)
    printArray(splitArray(newArray))
    println("*" * 20)
    println(avg(newArray))
    println("*" * 20)
    printArray(reverse(newArray))
    println("*" * 20)
    sortAmerica()
    println("*" * 20)
  }

  def sortAmerica(): Unit = {
    val zones: Array[String] = java.util.TimeZone.getAvailableIDs
    val sorted: Array[String] = zones.filter(_.startsWith("America/")).map(_.replace("America/", "")).sorted
    printArray(sorted)
  }

  def reverse(array: Array[_ <: AnyVal]): Array[_ <: AnyVal] = {
    array.reverse
  }

  def avg(array: Array[_ <: Int]): Double = {
    val sum:Double = array.foldLeft(0.0)(_ + _)
    sum / array.length
  }

  def splitArray(array: Array[Int]): Array[Int] = {
    val above:Int = array.count(x => x > 0)
    var left: Int = 0
    var right: Int = above
    val result: Array[Int] = Array.ofDim(array.length)
    for (x <- array) {
      if (x > 0) {
        result(left) = x
        left += 1
      } else {
        result(right) = x
        right += 1
      }
    }
    result
  }

  def swapNewArray(array: Array[Int]): Array[Int] = {
    val seq: IndexedSeq[Int] = for (i <- array.indices) yield {
      if (i + 1 >= array.length) array(i)
      else if (i % 2 == 0) array(i + 1)
      else array(i - 1)
    }
    seq.toArray
  }

  def printArray(array: Array[_ <: Any]): Unit = {
    array.foreach(x => print(x + " "))
    println
  }

  def swapArray(array: Array[Int]): Unit = {
    val max:Int = if (array.length % 2 == 0) array.length else array.length - 1
    for (i <- 0 until max by 2) {
      val temp: Int = array(i);
      array(i) = array(i + 1)
      array(i + 1) = temp
    }
  }


  def randomArray(n: Int): Array[Int] = {
    val a: Array[Int] = Array.ofDim[Int](n)
    for (i <- 0 until n) a(i) = Random.nextInt(2 * n) - n
    a
  }

  def randomPositiveArray(n: Int): Array[Int] = {
    val a: Array[Int] = Array.ofDim[Int](n)
    for (i <- 0 until n) a(i) = Random.nextInt(n)
    a
  }

  def arrayBufferTest(): Unit = {
    val buffer: ArrayBuffer[String] = ArrayBuffer[String]()
    buffer += "a"
    println(buffer)
    buffer += ("a", "b", "c", "d", "e")
    println(buffer)
    buffer.remove(2)
    println(buffer)
    buffer.remove(3, 2)
    println(buffer)
  }
}
