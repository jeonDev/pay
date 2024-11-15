package com.pay.core.endpoint.request

import com.pay.core.domain.member.request.MemberCreateRequest

data class MemberCreateEndpointRequest(
    val name:String
) {

    fun toRequest():MemberCreateRequest {
        return MemberCreateRequest(
            name = name
        )
    }
}