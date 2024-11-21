package com.pay.core.domain.account.repository.jpa

import com.pay.core.domain.member.repository.jpa.Member
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.util.Optional

interface AccountJpaRepository:JpaRepository<Account, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByMember(member: Member):Optional<Account>
}