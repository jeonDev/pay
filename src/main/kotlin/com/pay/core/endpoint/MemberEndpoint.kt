package com.pay.core.endpoint

import com.pay.core.domain.member.service.MemberService
import com.pay.core.endpoint.request.MemberCreateEndpointRequest
import com.pay.core.endpoint.response.MemberCreateEndpointResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberEndpoint(
    val memberService: MemberService
) {

    @PostMapping("/v1/member/create")
    fun create(@RequestBody request: MemberCreateEndpointRequest):MemberCreateEndpointResponse =
        request.toRequest()
            .let { memberService.create(it) }
            .toResponse()
}