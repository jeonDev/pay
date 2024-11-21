package com.pay.core.domain.fee.repository.impl

import com.pay.core.domain.fee.repository.FeeRepository
import com.pay.core.domain.fee.repository.jpa.Fee
import com.pay.core.domain.fee.repository.jpa.FeeJpaRepository
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.Optional

@Repository
class FeeRepositoryImpl(
    val feeJpaRepository: FeeJpaRepository
):FeeRepository {

    override fun create(feeType: FeeType, feeWay: FeeWay, fee: BigDecimal): Fee {
        val optionalFee = feeJpaRepository.findByFeeTypeAndUseYn(feeType, true)
        if (optionalFee.isPresent) {
            val entity = optionalFee.get()
            entity.update(feeWay, fee)
            return feeJpaRepository.save(entity)
        } else {
            return feeJpaRepository.save(
                Fee(
                    feeType = feeType,
                    feeWay = feeWay,
                    fee = fee,
                    useYn = true
                )
            )
        }
    }

    override fun findByFeeType(feeType: FeeType): Optional<Fee> =
        feeJpaRepository.findByFeeTypeAndUseYn(feeType, true)
}