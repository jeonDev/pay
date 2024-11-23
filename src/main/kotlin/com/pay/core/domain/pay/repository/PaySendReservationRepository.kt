package com.pay.core.domain.pay.repository

import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.pay.repository.jpa.PaySendReservation

interface PaySendReservationRepository {

    fun save(sendDate:String, sendTime:String, paySend:PaySend):PaySendReservation
    fun findByReservationPaySend(sendDate: String, sendTime: String): List<PaySend>
}