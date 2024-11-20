package com.pay.core.endpoint.request

import com.pay.core.domain.pay.request.PaySendRequest
import java.math.BigInteger

data class PaySendEndpointRequest(
    val amount: BigInteger,
    val sendMemberSeq: Long,
    val receiveMemberSeq: Long,
    val couponStorageSeq: Long?
) {

    fun toRequest():PaySendRequest {
        return PaySendRequest(
            amount = amount,
            sendMemberSeq = sendMemberSeq,
            receiveMemberSeq = receiveMemberSeq,
            couponStorageSeq = couponStorageSeq
        )
    }
}