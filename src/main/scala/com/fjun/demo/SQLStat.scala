package com.fjun.demo

import java.io.FileInputStream

import org.apache.poi.hssf.usermodel.{HSSFSheet, HSSFWorkbook}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, Map}

object SQLStat {
  def main(args: Array[String]): Unit = {
    val list = readSQLs()
    val result = stat(list)
    result.foreach(println)
  }

  def stat(list: ArrayBuffer[SQLInfo]): List[StatInfo] = {
    val map: mutable.Map[String, StatInfo] = mutable.Map()
    list.foreach(sqlInfo => {
      val sql: String = sqlInfo.sql
      val found: Option[String] = map.keys.find(key => editDistance(key, sql) >= 1)
      if (found.isDefined) {
        map(found.get).add(sqlInfo)
      } else {
        map.put(sql, new StatInfo(sql, 1, sqlInfo.time))
      }
    })
    map.valuesIterator.toList
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

  def readSQLs(): ArrayBuffer[SQLInfo] = {
    val filePath: String = "/home/fjun/Data/slow_sql.xls"
    val fis: FileInputStream = new FileInputStream(filePath)
    val workbook: HSSFWorkbook = new HSSFWorkbook(fis)
    val sheet: HSSFSheet  = workbook.getSheetAt(0)
    val list: ArrayBuffer[SQLInfo] = new ArrayBuffer[SQLInfo]()
    sheet.rowIterator().forEachRemaining(row => {
      val sql = new SQLInfo(row.getCell(0).getStringCellValue,
        row.getCell(1).getNumericCellValue.toInt,
        row.getCell(2).getStringCellValue.replaceAll("\\d", "").replaceAll("nttttnttt", "")
          .replaceAll("  ,", "").replaceAll("\\s+", " ").replaceAll(",,", "").replaceAll("nttt", "t"))
      list += sql
    })
    workbook.close()
    fis.close()
    list
  }

  class SQLInfo(val date: String, val time: Int, val sql: String) {
  }

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
