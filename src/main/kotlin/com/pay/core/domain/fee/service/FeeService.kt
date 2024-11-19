package com.pay.core.domain.fee.service

import com.pay.core.domain.fee.request.FeeCreateRequest
import com.pay.core.domain.fee.response.FeeResponse
import com.pay.core.domain.type.FeeType

interface FeeService {

    fun create(request:FeeCreateRequest)
    fun findByFeeType(feeType: FeeType):FeeResponse
}