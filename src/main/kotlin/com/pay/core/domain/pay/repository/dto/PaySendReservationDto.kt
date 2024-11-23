package com.pay.core.domain.pay.repository.dto

import com.pay.core.domain.type.TransactionStatus
import java.math.BigInteger

data class PaySendReservationDto(
    val paySendSeq:Long,
    val amount: BigInteger,
    val feeAmount: BigInteger,
    val transactionStatus: TransactionStatus,
    val sendAccountSeq: Long,
    val receiveAccountSeq: Long
)
