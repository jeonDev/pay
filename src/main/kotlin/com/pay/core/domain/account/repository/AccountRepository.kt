package com.pay.core.domain.account.repository

import com.pay.core.domain.member.repository.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface AccountRepository:JpaRepository<Account, Long> {

    fun findByMember(member: Member):Optional<Account>
}