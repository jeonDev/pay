package com.pay.core.domain.pay.service.impl

import com.pay.core.domain.account.request.AccountTransactionRequest
import com.pay.core.domain.account.response.AccountTransactionResponse
import com.pay.core.domain.account.service.AccountService
import com.pay.core.domain.pay.repository.PaySendRepository
import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.response.PaySendResponse
import com.pay.core.domain.pay.service.PaySendService
import com.pay.core.domain.type.PayType
import com.pay.core.domain.type.TransactionType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

@Service
class PaySendServiceImpl(
    val paySendRepository: PaySendRepository,
    val accountService: AccountService
):PaySendService {

    @Transactional
    override fun send(request: PaySendRequest): PaySendResponse {

        val sendAccount = accountService.findByMemberSeq(request.sendMemberSeq).orElseThrow()
        if (sendAccount.amount < request.amount) throw RuntimeException("잔액 부족")

        val receiveAccount = accountService.findByMemberSeq(request.receiveMemberSeq).orElseThrow()

        val entity = paySendRepository.save(request.toEntity(sendAccount, receiveAccount))


        val sendAccountResponse = this.transaction(entity.amount, TransactionType.WITHDRAW, request.sendMemberSeq, PayType.PAY_SEND, entity.id)
        val receiveAccountResponse = this.transaction(entity.amount, TransactionType.DEPOSIT, request.receiveMemberSeq, PayType.PAY_SEND, entity.id)

        return PaySendResponse(
            success = true,
            amount = entity.amount,
            feeAmount = entity.feeAmount,
            balance = sendAccountResponse.balance
        )
    }

    private fun transaction(amount: BigInteger, transactionType: TransactionType, memberSeq:Long, payType:PayType, paySeq:Long?): AccountTransactionResponse {
        val transactionRequest = AccountTransactionRequest(
            amount = amount,
            transactionType = transactionType,
            memberSeq = memberSeq,
            payType = payType,
            paySeq = paySeq
        )
        return accountService.transaction(transactionRequest)
    }
}