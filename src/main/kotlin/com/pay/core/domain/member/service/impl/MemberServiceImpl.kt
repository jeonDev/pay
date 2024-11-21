package com.pay.core.domain.member.service.impl

import com.pay.core.domain.member.repository.MemberRepository
import com.pay.core.domain.member.repository.jpa.Member
import com.pay.core.domain.member.request.MemberCreateRequest
import com.pay.core.domain.member.response.MemberCreateResponse
import com.pay.core.domain.member.service.MemberService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class MemberServiceImpl(
    val memberRepository: MemberRepository
):MemberService {
    private val log: Logger by lazy { LoggerFactory.getLogger(this::class.java) }

    @Transactional
    override fun create(request: MemberCreateRequest): MemberCreateResponse =
        memberRepository.create(request.name)
            .let { MemberCreateResponse(
                name = it.name
            )}

    @Transactional(readOnly = true)
    override fun findByMember(id: Long): Optional<Member> {
        return memberRepository.findById(id)
    }
}