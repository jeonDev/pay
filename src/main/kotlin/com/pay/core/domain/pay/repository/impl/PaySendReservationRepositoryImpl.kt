package com.pay.core.domain.pay.repository.impl

import com.pay.core.domain.pay.repository.PaySendReservationRepository
import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.pay.repository.jpa.PaySendReservation
import com.pay.core.domain.pay.repository.jpa.PaySendReservationJpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class PaySendReservationRepositoryImpl(
    val paySendReservationJpaRepository: PaySendReservationJpaRepository
):PaySendReservationRepository {

    override fun save(sendDate: String, sendTime: String, paySend: PaySend): PaySendReservation =
        paySendReservationJpaRepository.save(PaySendReservation(
            createDt = LocalDateTime.now(),
            sendDate = sendDate,
            sendTime = sendTime,
            paySend = paySend
        ))

}