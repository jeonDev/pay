package com.pay.core.domain.pay.repository.impl

import com.pay.core.domain.account.repository.jpa.Account
import com.pay.core.domain.pay.repository.PaySendRepository
import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.pay.repository.jpa.PaySendJpaRepository
import com.pay.core.domain.type.TransactionStatus
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*

@Repository
class PaySendRepositoryImpl(
    val paySendJpaRepository: PaySendJpaRepository
):PaySendRepository {

    override fun save(sendAccount: Account, receiveAccount: Account, amount:BigInteger, feeAmount: BigInteger): PaySend {
        val paySend = PaySend(
            amount = amount,
            feeAmount = feeAmount,
            transactionStatus = TransactionStatus.REQUEST
        )
        paySend.sendAccount = sendAccount
        paySend.receiveAccount = receiveAccount
        return paySendJpaRepository.save(paySend)
    }

    override fun statusUpdate(paySend: PaySend, transactionStatus: TransactionStatus) {
        paySend.transactionStatus = transactionStatus
        paySendJpaRepository.save(paySend)
    }

    override fun findById(paySendSeq: Long): Optional<PaySend> =
        paySendJpaRepository.findById(paySendSeq)

}