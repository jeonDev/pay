package com.pay.core.domain.pay.response

import java.math.BigInteger

data class PaySendResponse(
    val success:Boolean,
    val balance:BigInteger,
    val amount:BigInteger,
    val feeAmount:BigInteger
) {
}