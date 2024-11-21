package com.pay.core.domain.account.repository

import com.pay.core.domain.account.repository.jpa.Account
import com.pay.core.domain.member.repository.jpa.Member

interface AccountRepository {

    fun findByMemberLock(member: Member):Account
    fun save(account: Account):Account
}