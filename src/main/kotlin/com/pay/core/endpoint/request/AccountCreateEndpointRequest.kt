package com.pay.core.endpoint.request

import com.pay.core.domain.account.request.AccountCreateRequest

data class AccountCreateEndpointRequest(
    val memberSeq:Long
) {

    fun toRequest():AccountCreateRequest {
        return AccountCreateRequest(
            memberSeq = memberSeq
        )
    }
}