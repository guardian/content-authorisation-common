package com.gu.cas

import org.joda.time.{Days, Weeks}
import org.scalatest.{FlatSpec, Matchers}

class PrefixedTokensTest extends FlatSpec with Matchers {
  "Token Encoder" should "roundtrip" in {

    val encoder = RawTokenEncoder("secret")
    val periods = 0.until(53).map(Weeks.weeks)
    val creationOffsetDays = 0.until(2047).map(Days.days)

    for (period <- periods) {
      for (creationDateOffset <- creationOffsetDays) {
        val payload = TokenPayload(creationDateOffset, period, "SevenDay")
        val token: String = encoder.encode(payload)
        val decodedPayload = encoder.decode(token).asInstanceOf[Valid].payload

        decodedPayload.period should equal (period)
        decodedPayload.creationDateOffset should equal (creationDateOffset)
      }
    }

  }
}
