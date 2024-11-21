package com.pay.core.domain.account.repository.impl

import com.pay.core.domain.account.repository.AccountRepository
import com.pay.core.domain.account.repository.jpa.Account
import com.pay.core.domain.account.repository.jpa.AccountJpaRepository
import com.pay.core.domain.member.repository.jpa.Member
import org.springframework.stereotype.Repository

@Repository
class AccountRepositoryImpl(
    val accountJpaRepository: AccountJpaRepository
): AccountRepository {

    override fun findByMemberLock(member: Member): Account =
        accountJpaRepository.findByMember(member)
            .orElseThrow()

    override fun save(account: Account): Account =
        accountJpaRepository.save(account)
}