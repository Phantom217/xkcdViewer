import android._
import android.Keys._
import sbt._
import sbt.Keys._

import Libraries._
import ReplacePropertiesGenerator.{replaceValuesTask, setDebugTask}

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = AndroidApp

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      name := "xkcd Viewer",
      organization := "com.paoerful",
      organizationName := "paoerful",
      version := Versions.appV,
      javacOptions in Compile ++= "-target" :: "1.7" :: "-source" :: "1.7" :: Nil,
      scalaVersion := Versions.scalaV,
      scalacOptions ++= "-feature" :: "-deprecation" :: Nil
    ) ++ dependenciesSettings ++ aliasSettings ++ androidSettings

  private[this] val dependenciesSettings = Seq(
    resolvers ++= Seq(
      Resolver.mavenLocal,
      DefaultMavenRepository,
      Resolver.typesafeRepo("releases"),
      Resolver.typesafeRepo("snapshots"),
      Resolver.typesafeIvyRepo("snapshots"),
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots"),
      Resolver.defaultLocal,
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
    ),
    libraryDependencies ++= Seq(
      aar(macroidRoot),
      aar(macroidExtras),
      aar(macroidViewable),
      aar(androidSupportv4),
      aar(androidAppCompat),
      aar(androidCardView),
      aar(androidRecyclerview),
      androidTest,
      specs2,
      mockito
    )
  )

  private[this] val aliasSettings: Seq[Def.Setting[_]] = Seq(
    run <<= (run in Android).dependsOn(setDebugTask(true)),
    packageRelease <<= (packageRelease in Android).dependsOn(
      setDebugTask(false)),
    packageResources in Android <<= (packageResources in Android).dependsOn(replaceValuesTask)
  )

  private[this] val androidSettings: Seq[Def.Setting[_]] = Seq(
    platformTarget in Android := Versions.androidPlatformV,
    proguardCache in Android := Seq.empty,
    proguardScala in Android := true,
    useProguard in Android := true,
    proguardOptions in Android ++=
      "-ignorewarnings" ::
      "-keep class scala.Dynamic" ::
      "-keep class macroid.** { *; }" ::
      Nil,
    packagingOptions in Android := PackagingOptions(
      excludes = Nil
    )
  )
}
