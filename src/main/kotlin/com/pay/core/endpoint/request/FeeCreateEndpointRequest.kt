package com.pay.core.endpoint.request

import com.pay.core.domain.fee.request.FeeCreateRequest
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import java.math.BigDecimal

data class FeeCreateEndpointRequest(
    val feeType: FeeType,
    val feeWay: FeeWay,
    val fee: BigDecimal
) {

    fun toRequest():FeeCreateRequest {
        return FeeCreateRequest(
            feeType= feeType,
            feeWay = feeWay,
            fee = fee
        )
    }
}