package com.pay.core.domain.pay.service.impl

import com.pay.core.domain.account.request.AccountTransactionRequest
import com.pay.core.domain.account.service.AccountService
import com.pay.core.domain.pay.repository.PaySendRepository
import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.response.PaySendResponse
import com.pay.core.domain.pay.service.PaySendService
import com.pay.core.domain.type.TransactionType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

        val sendAccountRequest = AccountTransactionRequest(
            amount = entity.amount,
            transactionType = TransactionType.WITHDRAW,
            memberSeq = request.sendMemberSeq
        )
        val sendAccountResponse = accountService.transaction(sendAccountRequest)

        val receiveAccountRequest = AccountTransactionRequest(
            amount = entity.amount,
            transactionType = TransactionType.DEPOSIT,
            memberSeq = request.receiveMemberSeq
        )
        val receiveAccountResponse = accountService.transaction(receiveAccountRequest)

        return PaySendResponse(
            success = true,
            amount = entity.amount,
            feeAmount = entity.feeAmount,
            balance = sendAccountResponse.balance
        )
    }
}