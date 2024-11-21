package com.pay.core.domain.member.service

import com.pay.core.domain.member.repository.jpa.Member
import com.pay.core.domain.member.request.MemberCreateRequest
import com.pay.core.domain.member.response.MemberCreateResponse
import java.util.Optional

interface MemberService {

    fun create(request: MemberCreateRequest): MemberCreateResponse
    fun findByMember(id:Long):Optional<Member>
}