package com.pay.core.domain.account.service.impl

import com.pay.core.domain.account.repository.Account
import com.pay.core.domain.account.repository.AccountHistoryRepository
import com.pay.core.domain.account.repository.AccountRepository
import com.pay.core.domain.account.request.AccountCreateRequest
import com.pay.core.domain.account.request.AccountTransactionRequest
import com.pay.core.domain.account.response.AccountCreateResponse
import com.pay.core.domain.account.response.AccountTransactionResponse
import com.pay.core.domain.account.service.AccountService
import com.pay.core.domain.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.time.LocalDateTime

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository,
    val accountHistoryRepository: AccountHistoryRepository,
    val memberService: MemberService
):AccountService {

    @Transactional
    override fun create(request: AccountCreateRequest): AccountCreateResponse {
        val member = memberService.findByMember(request.memberSeq).orElseThrow()
        var account:Account = Account(
            amount = BigInteger.ZERO,
            createDt = LocalDateTime.now()
        )
        account.member = member
        accountRepository.save(account)
        return AccountCreateResponse()
    }

    @Transactional
    override fun transaction(request: AccountTransactionRequest): AccountTransactionResponse {
        val member = memberService.findByMember(request.memberSeq).orElseThrow()

        val account = accountRepository.findByMember(member).orElseThrow()

        val accountHistory = account.transaction(request.transactionType, request.amount)

        accountRepository.save(account)
        accountHistoryRepository.save(accountHistory)

        return AccountTransactionResponse(
            transactionType = request.transactionType
        )
    }
}