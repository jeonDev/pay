package com.pay.core.endpoint.request

import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.request.PaySendReservationRequest
import java.math.BigInteger

data class PaySendEndpointRequest(
    val amount: BigInteger,
    val sendMemberSeq: Long,
    val receiveMemberSeq: Long,
    val couponStorageSeq: Long?,
    val isReservation: Boolean,
    val paySendReservationRequest: PaySendReservationRequest?
) {

    fun toRequest():PaySendRequest {
        paySendReservationRequest?.let { 
            TODO("Validation 체크")
        }
        return PaySendRequest(
            amount = amount,
            sendMemberSeq = sendMemberSeq,
            receiveMemberSeq = receiveMemberSeq,
            couponStorageSeq = couponStorageSeq,
            isReservation = isReservation,
            paySendReservationRequest = paySendReservationRequest
        )
    }
}

data class PaySendReservationRequest(
    val reservationDate: String,
    val reservationTime: String
)