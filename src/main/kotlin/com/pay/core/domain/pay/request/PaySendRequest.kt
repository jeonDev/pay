package com.pay.core.domain.pay.request

import java.math.BigInteger

data class PaySendRequest(
    val amount: BigInteger,
    val sendMemberSeq: Long,
    val receiveMemberSeq: Long,
    val couponStorageSeq:Long?,
    val isReservation: Boolean,
    val paySendReservationRequest: PaySendReservationRequest?
)

data class PaySendReservationRequest(
    val reservationDate: String,
    val reservationTime: String
)
