package com.pay.core.domain.fee.service

import com.pay.core.domain.fee.request.FeeCreateRequest
import com.pay.core.domain.type.FeeType
import java.math.BigDecimal

interface FeeService {

    fun create(request:FeeCreateRequest)
    fun findFeeAmount(feeType: FeeType, amount:BigDecimal):BigDecimal
}