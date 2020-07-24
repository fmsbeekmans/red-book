import sbt._
import sbt.Keys._

object Dependencies extends AutoPlugin {
  object Versions {
    val circe = "0.13.0"
    val http4s = "1.0.0-M3"
  }

  val circe = "io.circe" -> Seq(
    "io.circe" %% "circe-core" % "0.13.0",
    "io.circe" %% "circe-generic" % "0.13.0",
  )

  val http4s = "org.http4s" -> Seq(
    "org.http4s" %% "http4s-blaze-server" % Versions.http4s,
    "org.http4s" %% "http4s-blaze-client" % Versions.http4s,
    "org.http4s" %% "http4s-circe" % Versions.http4s,
    "org.http4s" %% "http4s-dsl" % Versions.http4s,
  )

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      libraryDependencies ++= Map(
        circe,
        http4s,
      ).values.toList.flatten
    )
}
