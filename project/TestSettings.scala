import sbt.Keys.{libraryDependencies, testFrameworks}
import sbt._

object TestSettings extends AutoPlugin {


  override def requires = Dependencies

  object Versions {
    val http4s = "1.0.0-M3"
  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    libraryDependencies ++= Seq("com.lihaoyi" %% "utest" % "0.7.2" % "test"),
    testFrameworks += new TestFramework("utest.runner.Framework"),
  )
}