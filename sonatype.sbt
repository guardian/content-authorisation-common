sonatypeProfileName := "com.gu"
publishMavenStyle := true
licenses := Seq("APL2" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/guardian/content-authorisation-common"))
scmInfo := Some(ScmInfo(
  url("https://github.com/guardian/content-authorisation-common"),
  "scm:git@github.com:guardian/content-authorisation-common.git"
))
developers := List(
  Developer(id="rtyley", name="Roberto Tyley", email="", url=url("https://github.com/rtyley")),
)
publishTo := sonatypePublishToBundle.value



