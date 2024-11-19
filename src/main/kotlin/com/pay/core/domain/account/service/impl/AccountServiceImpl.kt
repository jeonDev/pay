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
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger
import java.time.LocalDateTime
import java.util.*

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository,
    val accountHistoryRepository: AccountHistoryRepository,
    val memberService: MemberService
):AccountService {
    private val log: Logger by lazy { LoggerFactory.getLogger(this::class.java) }

    @Transactional
    override fun create(request: AccountCreateRequest): AccountCreateResponse {
        log.info("계좌 생성 : {}", request.memberSeq)
        val member = memberService.findByMember(request.memberSeq).orElseThrow()
        var account:Account = Account(
            amount = BigInteger.ZERO,
            createDt = LocalDateTime.now()
        )
        account.member = member
        accountRepository.save(account)
        return AccountCreateResponse(
            amount = account.amount
        )
    }

    @Transactional
    override fun transaction(request: AccountTransactionRequest): AccountTransactionResponse {
        log.info("계좌 입출금 : {} {} {} {} {}", request.memberSeq, request.amount, request.transactionType, request.payType, request.paySeq)
        val member = memberService.findByMember(request.memberSeq).orElseThrow()

        val account = accountRepository.findByMember(member).orElseThrow()

        val accountHistory = account.transaction(request.transactionType, request.amount, request.payType, request.paySeq)

        accountRepository.save(account)
        accountHistoryRepository.save(accountHistory)

        return AccountTransactionResponse(
            transactionType = request.transactionType,
            balance = account.amount
        )
    }

    @Transactional(readOnly = true)
    override fun findByMemberSeq(memberSeq: Long): Optional<Account> {
        val member = memberService.findByMember(memberSeq).orElseThrow()
        return accountRepository.findByMember(member)
    }
}