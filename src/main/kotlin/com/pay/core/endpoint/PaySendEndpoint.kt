package com.pay.core.endpoint

import com.pay.core.domain.pay.service.PaySendService
import com.pay.core.endpoint.request.PaySendEndpointRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaySendEndpoint(
    val paySendService: PaySendService
) {

    @PostMapping("/v1/pay/send")
    fun paySend(@RequestBody request:PaySendEndpointRequest) =
        request.toRequest()
            .let { paySendService.send(it) }
}