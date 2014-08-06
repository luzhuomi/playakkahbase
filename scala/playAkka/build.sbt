name := "playAkka"

version := "1.0-SNAPSHOT"


val akkaVersion = "2.2.3" // not greater than this version for binary compatibility


libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-remote" % akkaVersion,
  "com.typesafe.akka" %% "akka-contrib" % akkaVersion,
  "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "org.fusesource" % "sigar" % "1.6.4",
  jdbc,
  anorm,
  cache
)     

play.Project.playScalaSettings


scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint")

javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation")

javaOptions in run ++= Seq("-Djava.library.path=./sigar",  "-Xms128m", "-Xmx1024m")


Keys.fork in run := true

mainClass in (Compile, run) := Some("actors.TransformationApp")

// make sure that MultiJvm test are compiled by the default test compilation

// compile in MultiJvm <<= (compile in MultiJvm) triggeredBy (compile in Test)

// disable parallel tests

parallelExecution in Test := false

// make sure that MultiJvm tests are executed by the default test target, 
// and combine the results from ordinary test and multi-jvm tests


