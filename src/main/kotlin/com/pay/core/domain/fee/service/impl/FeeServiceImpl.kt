package com.pay.core.domain.fee.service.impl

import com.pay.core.domain.fee.repository.FeeRepository
import com.pay.core.domain.fee.request.FeeCreateRequest
import com.pay.core.domain.fee.service.FeeService
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class FeeServiceImpl(
    val feeRepository: FeeRepository
):FeeService {
    private val log:Logger by lazy { LoggerFactory.getLogger(this::class.java) }

    @Transactional
    override fun create(request: FeeCreateRequest) {
        log.info("수수료 정책 추가 : {} {} {}", request.feeType, request.feeWay, request.fee)
        feeRepository.create(request.feeType, request.feeWay, request.fee)
    }

    override fun findFeeAmount(feeType: FeeType, amount: BigDecimal): BigDecimal {
        log.info("수수료 가져오기 : {} {}", feeType, amount)
        val optionalFee = feeRepository.findByFeeType(feeType)
        if (optionalFee.isEmpty) return BigDecimal.ZERO
        val fee = optionalFee.get()

        return when (fee.feeWay) {
            FeeWay.NONE -> BigDecimal.ZERO
            FeeWay.RATE -> amount.multiply(fee.fee).divide(BigDecimal(100))
            FeeWay.FIXED -> fee.fee
        }
    }

}