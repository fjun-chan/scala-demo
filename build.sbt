name := "scala-demo"
version := "1.0.0"
scalaVersion := "2.11.11"
sbtVersion := "1.0.0"

libraryDependencies += "org.apache.poi" % "poi" % "3.16"
libraryDependencies += "com.google.guava" % "guava" % "23.0"
libraryDependencies += "com.google.code.gson" % "gson" % "2.8.1"

libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.1.1"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.1.1"
