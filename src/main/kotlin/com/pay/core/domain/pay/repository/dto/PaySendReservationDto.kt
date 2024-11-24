package com.pay.core.domain.pay.repository.dto

import com.pay.core.domain.type.TransactionStatus
import java.math.BigInteger


// TODO: Type...
data class PaySendReservationDto(
    val paySendSeq:Long,
    val amount: Long,
    val feeAmount: Long,
    val transactionStatus: String,
    val sendAccountSeq: Long,
    val receiveAccountSeq: Long
) {

}
