package com.pay.core.domain.account.repository

import com.pay.core.domain.member.repository.Member
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.util.Optional

interface AccountRepository:JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByMember(member: Member):Optional<Account>
}