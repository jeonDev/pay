package com.pay.core.domain.fee.repository

import com.pay.core.domain.fee.repository.jpa.Fee
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import java.math.BigDecimal
import java.util.Optional

interface FeeRepository {

    fun create(feeType: FeeType, feeWay: FeeWay, fee:BigDecimal): Fee
    fun findByFeeType(feeType: FeeType): Optional<Fee>
}