package com.pay.core.domain.fee.response

import com.pay.core.domain.type.FeeWay
import java.math.BigDecimal

data class FeeResponse(
    val feeWay: FeeWay,
    val fee: BigDecimal
)