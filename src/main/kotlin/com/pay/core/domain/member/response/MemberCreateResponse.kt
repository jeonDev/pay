package com.pay.core.domain.member.response

import com.pay.core.endpoint.response.MemberCreateEndpointResponse

data class MemberCreateResponse(
    val name:String
) {

    fun toResponse():MemberCreateEndpointResponse {
        return MemberCreateEndpointResponse(
            name = name
        )
    }
}