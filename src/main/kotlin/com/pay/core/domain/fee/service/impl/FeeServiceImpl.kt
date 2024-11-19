package com.pay.core.domain.fee.service.impl

import com.pay.core.domain.fee.repository.FeeRepository
import com.pay.core.domain.fee.request.FeeCreateRequest
import com.pay.core.domain.fee.service.FeeService
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class FeeServiceImpl(
    val feeRepository: FeeRepository
):FeeService {

    @Transactional
    override fun create(request: FeeCreateRequest) {
        val optionalFee = feeRepository.findByFeeTypeAndUseYn(request.feeType, true)
        if (optionalFee.isPresent) {
            val fee = optionalFee.get()
            fee.update(request.feeWay, request.fee)
            feeRepository.save(fee)
        } else {
            feeRepository.save(request.toEntity())
        }
    }

    override fun findFeeAmount(feeType: FeeType, amount: BigDecimal): BigDecimal {
        val optionalFee = feeRepository.findByFeeTypeAndUseYn(feeType, true)
        if (optionalFee.isEmpty) return BigDecimal.ZERO
        val fee = optionalFee.get()

        return when (fee.feeWay) {
            FeeWay.NONE -> BigDecimal.ZERO
            FeeWay.RATE -> amount.multiply(fee.fee).divide(BigDecimal(100))
            FeeWay.FIXED -> fee.fee
        }
    }

}