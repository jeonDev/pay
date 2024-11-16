package com.pay.core.domain.account.response

import com.pay.core.endpoint.response.AccountCreateEndpointResponse
import java.math.BigInteger

data class AccountCreateResponse(
    val amount:BigInteger
) {

    fun toResponse():AccountCreateEndpointResponse {
        return AccountCreateEndpointResponse(
            amount = amount
        )
    }
}