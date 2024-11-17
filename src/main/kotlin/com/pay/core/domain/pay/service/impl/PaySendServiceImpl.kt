package com.pay.core.domain.pay.service.impl

import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.response.PaySendResponse
import com.pay.core.domain.pay.service.PaySendService
import org.springframework.stereotype.Service

@Service
class PaySendServiceImpl:PaySendService {

    override fun send(request: PaySendRequest): PaySendResponse {
        TODO("Not yet implemented")
    }
}