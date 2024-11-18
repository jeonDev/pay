package com.pay.core.domain.pay.request

import com.pay.core.domain.account.repository.Account
import com.pay.core.domain.pay.repository.PaySend
import java.math.BigInteger

data class PaySendRequest(
    val amount: BigInteger,
    val sendMemberSeq: Long,
    val receiveMemberSeq: Long
) {
    fun toEntity(sendAccount:Account, receiveAccount:Account):PaySend {
        val paySend = PaySend(
            amount = amount,
            feeAmount = BigInteger.ZERO
        )
        paySend.sendAccount = sendAccount
        paySend.receiveAccount = receiveAccount
        return paySend
    }
}
