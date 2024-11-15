package com.pay.core.domain.member.service.impl

import com.pay.core.domain.member.repository.Member
import com.pay.core.domain.member.repository.MemberRepository
import com.pay.core.domain.member.request.MemberCreateRequest
import com.pay.core.domain.member.response.MemberCreateResponse
import com.pay.core.domain.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
class MemberServiceImpl(
    val memberRepository: MemberRepository
):MemberService {

    @Transactional
    override fun create(memberCreateRequest: MemberCreateRequest): MemberCreateResponse {
        val member:Member = Member(
            name = "a",
            createDt = LocalDateTime.now()
        )
        memberRepository.save(member)
        return MemberCreateResponse()
    }

    @Transactional(readOnly = true)
    override fun findByMember(id: Long): Optional<Member> {
        return memberRepository.findById(id)
    }
}