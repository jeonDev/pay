package com.pay.core.domain.member.repository

import com.pay.core.domain.member.repository.jpa.Member
import java.util.*

interface MemberRepository {
    fun create(name: String): Member
    fun findById(id: Long): Optional<Member>
}