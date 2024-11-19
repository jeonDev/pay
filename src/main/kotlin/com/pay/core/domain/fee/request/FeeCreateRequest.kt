package com.pay.core.domain.fee.request

import com.pay.core.domain.fee.repository.Fee
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import java.math.BigDecimal

data class FeeCreateRequest(
    val feeType:FeeType,
    val feeWay:FeeWay,
    val fee:BigDecimal
) {

    fun toEntity():Fee {
        return Fee(
            feeType = feeType,
            feeWay = feeWay,
            fee = fee,
            useYn = true
        )
    }
}
