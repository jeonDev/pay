package com.pay.core.endpoint

import com.pay.core.domain.fee.service.FeeService
import com.pay.core.endpoint.request.FeeCreateEndpointRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FeeEndpoint(
    val feeService: FeeService
) {

    @PostMapping("/v1/fee/create")
    fun create(@RequestBody request:FeeCreateEndpointRequest) =
        request.toRequest()
            .let { feeService.create(it) }
}