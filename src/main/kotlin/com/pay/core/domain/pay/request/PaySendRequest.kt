package com.pay.core.domain.pay.request

import com.pay.core.domain.account.repository.Account
import com.pay.core.domain.pay.repository.PaySend
import java.math.BigInteger

data class PaySendRequest(
    val amount: BigInteger,
    val sendMemberSeq: Long,
    val receiveMemberSeq: Long,
    val couponStorageSeq:Long?
) {
    fun toEntity(sendAccount:Account, receiveAccount:Account, feeAmount:BigInteger):PaySend {
        val paySend = PaySend(
            amount = amount,
            feeAmount = feeAmount
        )
        paySend.sendAccount = sendAccount
        paySend.receiveAccount = receiveAccount
        return paySend
    }
}
