package com.pay.core.domain.fee.repository.jpa

import com.pay.core.domain.type.FeeType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface FeeJpaRepository:JpaRepository<Fee, Long> {

    fun findByFeeTypeAndUseYn(feeType: FeeType, useYn:Boolean): Optional<Fee>
}