package com.gu.cas

import org.joda.time.{Days, Weeks}
import org.scalatest.{FlatSpec, Matchers}

class TokenPayloadTest extends FlatSpec with Matchers {

  val epoch = TokenPayload.epoch.toLocalDate
  val wrappedEpoch = epoch.plusDays(2048)
  val startOffset = 2040
  val testData = TokenPayload(Days.days(startOffset), Weeks.weeks(2), SevenDay)

  "Token Payload" should "return the end date on the issue date (with no wrapping)" in {

    testData.expiryDate(epoch.plusDays(startOffset)) should be(epoch.plusDays(startOffset + 14 + 1))

  }

  "Token Payload" should "return the expiry date on expiry date (with no wrapping)" in {

    testData.expiryDate(epoch.plusDays(startOffset + 14 + 1)) should be(epoch.plusDays(startOffset + 14 + 1))

  }

  "Token Payload" should "work on the day before the wrapped creation date (with no wrapping)" in {

    testData.expiryDate(wrappedEpoch.plusDays(startOffset - 1)) should be(epoch.plusDays(startOffset + 14 + 1))

  }

  "Token Payload" should "return the wrapped expiry date on the wrapped creation date" in {

    testData.expiryDate(wrappedEpoch.plusDays(startOffset)) should be(wrappedEpoch.plusDays(startOffset + 14 + 1))

  }

}
