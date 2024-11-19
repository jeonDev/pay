package com.pay.core.domain.pay.service.impl

import com.pay.core.domain.account.request.AccountTransactionRequest
import com.pay.core.domain.account.response.AccountTransactionResponse
import com.pay.core.domain.account.service.AccountService
import com.pay.core.domain.fee.service.FeeService
import com.pay.core.domain.pay.repository.PaySendRepository
import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.response.PaySendResponse
import com.pay.core.domain.pay.service.PaySendService
import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.PayType
import com.pay.core.domain.type.TransactionType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

@Service
class PaySendServiceImpl(
    val paySendRepository: PaySendRepository,
    val accountService: AccountService,
    val feeService: FeeService
):PaySendService {

    @Transactional
    override fun send(request: PaySendRequest): PaySendResponse {

        val sendAccount = accountService.findByMemberSeq(request.sendMemberSeq).orElseThrow()

        val feeAmount = feeService.findFeeAmount(FeeType.PAY_SEND, request.amount.toBigDecimal())
            .toBigInteger()
        val amount = request.amount.add(feeAmount)
        if (sendAccount.amount < amount) throw RuntimeException("잔액 부족")

        val receiveAccount = accountService.findByMemberSeq(request.receiveMemberSeq).orElseThrow()

        val entity = paySendRepository.save(request.toEntity(sendAccount, receiveAccount, feeAmount))

        val sendAccountResponse = this.transaction(amount, TransactionType.WITHDRAW, request.sendMemberSeq, PayType.PAY_SEND, entity.id)
        val receiveAccountResponse = this.transaction(amount, TransactionType.DEPOSIT, request.receiveMemberSeq, PayType.PAY_SEND, entity.id)

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