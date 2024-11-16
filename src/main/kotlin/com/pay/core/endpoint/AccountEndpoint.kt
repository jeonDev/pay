package com.pay.core.endpoint

import com.pay.core.domain.account.service.AccountService
import com.pay.core.endpoint.request.AccountCreateEndpointRequest
import com.pay.core.endpoint.response.AccountCreateEndpointResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountEndpoint(
    val accountService: AccountService
) {

    @PostMapping("/v1/account/create")
    fun create(@RequestBody request:AccountCreateEndpointRequest):AccountCreateEndpointResponse =
        request.toRequest()
            .let { accountService.create(it) }
            .toResponse()

}