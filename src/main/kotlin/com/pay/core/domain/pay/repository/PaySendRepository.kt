package com.pay.core.domain.pay.repository

import com.pay.core.domain.account.repository.jpa.Account
import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.type.TransactionStatus
import java.math.BigInteger

interface PaySendRepository {
    fun save(sendAccount: Account, receiveAccount: Account, amount:BigInteger, feeAmount: BigInteger): PaySend
    fun statusUpdate(paySend: PaySend, transactionStatus: TransactionStatus)
}