import sbtrelease._
import ReleaseStateTransformations._

releaseSettings

sonatypeSettings

name := "content-authorisation-common"

organization := "com.gu"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.6", scalaVersion.value)

unmanagedResourceDirectories in Compile += baseDirectory.value / "conf"

scmInfo := Some(ScmInfo(
  url("https://github.com/guardian/content-authorisation-common"),
  "scm:git:git@github.com:guardian/content-authorisation-common.git"
))

description := "Extracts some behaviours from content-authorisation"

licenses := Seq("Apache V2" -> url("http://www.apache.org/licenses/LICENSE-2.0.html"))

resolvers += "Guardian Github Releases" at "http://guardian.github.io/maven/repo-releases"

libraryDependencies ++= Seq(
  "commons-io" % "commons-io" % "2.4",
  "joda-time" % "joda-time" % "2.9.1",
  "com.typesafe" % "config" % "1.3.0",
  "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test"
)

pomExtra := (
  <url>https://github.com/guardian/content-authorisation-common</url>
    <developers>
      <developer>
        <id>rtyley</id>
        <name>Roberto Tyley</name>
        <url>https://github.com/rtyley</url>
      </developer>
      <developer>
        <id>ostapneko</id>
        <name>Thomas Franquelin</name>
        <url>https://github.com/ostapneko</url>
      </developer>
    </developers>
  )

lazy val root = project in file(".")

ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(
    action = state => Project.extract(state).runTask(PgpKeys.publishSigned, state)._1,
    enableCrossBuild = true
  ),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(state => Project.extract(state).runTask(SonatypeKeys.sonatypeReleaseAll, state)._1),
  pushChanges
)
