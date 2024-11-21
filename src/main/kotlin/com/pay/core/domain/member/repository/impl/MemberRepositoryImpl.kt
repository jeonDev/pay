package com.pay.core.domain.member.repository.impl

import com.pay.core.domain.member.repository.MemberRepository
import com.pay.core.domain.member.repository.jpa.Member
import com.pay.core.domain.member.repository.jpa.MemberJpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class MemberRepositoryImpl(
    val memberJpaRepository: MemberJpaRepository
):MemberRepository {

    override fun create(name: String): Member =
        memberJpaRepository.save(Member(
            name = name,
            createDt = LocalDateTime.now()
        ))

    override fun findById(id: Long): Optional<Member> =
        memberJpaRepository.findById(id)
}