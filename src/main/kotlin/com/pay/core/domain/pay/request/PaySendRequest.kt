package com.pay.core.domain.pay.request

import java.math.BigInteger

data class PaySendRequest(
    val amount:BigInteger,
    val accountSeq:Long
)
