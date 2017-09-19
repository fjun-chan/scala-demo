package com.fjun.demo

import com.google.gson.Gson

import scala.io.Source

object SQLStatV2 {
  val JSON_FILE = "/warehouse/Work/datas/slow_sql_20.json";
  def main(args: Array[String]): Unit = {
    val jsonString:String = Source.fromFile(JSON_FILE).mkString
    val sqlList: SQLList = new Gson().fromJson(jsonString, classOf[SQLList])
    println(sqlList)
  }


  def editDistance(str1: String, str2: String): Double = {
    val len1: Int = str1.length
    val len2: Int = str2.length
    val matrix = Array.ofDim[Int](len1 + 1, len2 + 1)
    for (i <- 0 to len1) {
      matrix(i)(0) = i
    }
    for (j <- 1 to len2) {
      matrix(0)(j) = j
    }
    for (row <- 0 until len1) {
      for (col <- 0 until len2) {
        if (str1.charAt(row).equals(str2.charAt(col))) {
          matrix(row + 1)(col + 1) = matrix(row)(col)
        } else {
          matrix(row + 1)(col + 1) = Array(matrix(row + 1)(col) + 1, matrix(row)(col + 1) + 1, matrix(row)(col) + 1).min
        }
      }
    }
    1 - matrix(len1)(len2) * 1.0 / Math.max(len1, len2)
  }
  class SQLList(val rc: Int, val total: Int, val list: List[SQLInfo]){}
  class SQLInfo(val date: String, val time: Int, val sql: String) {}
  class StatInfo(val sql: String, var count: Int, var total: Int) {
    private var max: Int = total
    private var min: Int = total

    def add(sql: SQLInfo) = {
      count = count + 1
      total += sql.time
      if (max < sql.time) {
        max = sql.time
      }
      if (min > sql.time) {
        min = sql.time
      }
    }

    override def toString: String = {
      "" + sql + "\t" + count + "\t" + max + "\t" + min + "\t" + (total * 1.0 / count)
    }
  }
}
