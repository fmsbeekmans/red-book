lazy val auth = (project in file("."))
  .settings(
    name := "red-book",
  )
  .enablePlugins(ScalaSettings)
  .enablePlugins(Dependencies)
  .enablePlugins(TestSettings)
  .enablePlugins(ScalafmtSettings)
