package com.pay.core.domain.account.service.impl

import com.pay.core.domain.account.repository.Account
import com.pay.core.domain.account.repository.AccountRepository
import com.pay.core.domain.account.request.AccountCreateRequest
import com.pay.core.domain.account.response.AccountCreateResponse
import com.pay.core.domain.account.service.AccountService
import com.pay.core.domain.member.service.MemberService
import org.springframework.stereotype.Service
import java.math.BigInteger

@Service
class AccountServiceImpl(
    val accountRepository: AccountRepository,
    val memberService: MemberService
):AccountService {

    override fun create(accountCreateRequest: AccountCreateRequest): AccountCreateResponse {
        val member = memberService.findByMember(accountCreateRequest.memberSeq).orElseThrow()
        var account:Account = Account(
            amount = BigInteger.ZERO
        )
        account.member = member
        accountRepository.save(account)
        return AccountCreateResponse()
    }
}