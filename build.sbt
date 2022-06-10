import sbtrelease._
import ReleaseStateTransformations._

name := "content-authorisation-common"
description := "Extracts some behaviours from content-authorisation"
organization := "com.gu"
scalaVersion := "2.12.16"

crossScalaVersions := Seq(scalaVersion.value, "2.13.8")
unmanagedResourceDirectories in Compile += baseDirectory.value / "conf"
resolvers += "Guardian Github Releases" at "https://guardian.github.io/maven/repo-releases"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.11.0",
  "joda-time" % "joda-time" % "2.10.13",
  "com.typesafe" % "config" % "1.4.2",
  "org.scalatest" %% "scalatest" % "3.1.4" % "test"
)

lazy val root = project in file(".")

sources in doc in Compile := List()
releasePublishArtifactsAction := PgpKeys.publishSigned.value
releaseCrossBuild := true
releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommandAndRemaining("+publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
