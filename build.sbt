import sbtrelease._
import ReleaseStateTransformations._

name := "content-authorisation-common"
description := "Extracts some behaviours from content-authorisation"
organization := "com.gu"
scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.8", scalaVersion.value, "2.13.3")
unmanagedResourceDirectories in Compile += baseDirectory.value / "conf"
resolvers += "Guardian Github Releases" at "http://guardian.github.io/maven/repo-releases"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "joda-time" % "joda-time" % "2.9.1",
  "com.typesafe" % "config" % "1.3.0",
  "org.scalatest" %% "scalatest" % "3.1.1" % "test"
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