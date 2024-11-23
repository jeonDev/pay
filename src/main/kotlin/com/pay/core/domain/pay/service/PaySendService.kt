package com.pay.core.domain.pay.service

import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.response.PaySendResponse

interface PaySendService {

    fun send(request:PaySendRequest):PaySendResponse
    fun reservationSend()
    fun findByReservationTransaction(sendDate:String, sendTime:String):List<PaySend>
}