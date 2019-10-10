import sbt._

object Libraries {

  def onCompile(dep: ModuleID): ModuleID = dep % "compile"
  def onTest(dep: ModuleID): ModuleID = dep % "test"

  def androidDep( module: String, version: String = Versions.androidV): ModuleID =
    "com.android.support" % module % version
  def macroidDep(module: String = ""): ModuleID =
    "org.macroid" %% s"macroid${if (!module.isEmpty) s"-$module" else ""}" % Versions.macroidV

  // android libraries
  lazy val androidSupportv4 = androidDep("support-v4")
  lazy val androidAppCompat = androidDep("appcompat-v7")
  lazy val androidSupportCompat = androidDep("support-compat")
  lazy val androidRecyclerview = androidDep("recyclerview-v7")
  lazy val androidCardView = androidDep("cardview-v7")

  // macroid libraries
  lazy val macroidRoot = macroidDep()
  lazy val macroidExtras = macroidDep("extras")

  // testing libraries
  lazy val specs2 = "org.specs2" %% "specs2-core" % Versions.specs2V % "test"
  lazy val mockito = "org.specs2" %% "specs2-mock" % Versions.mockitoV % "test"
  lazy val androidTest = "com.google.android" % "android" % "4.1.1.4" % "test"

}
