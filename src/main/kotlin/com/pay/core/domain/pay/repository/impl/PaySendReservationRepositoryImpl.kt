package com.pay.core.domain.pay.repository.impl

import com.pay.core.domain.pay.repository.PaySendReservationRepository
import com.pay.core.domain.pay.repository.dto.PaySendReservationDto
import com.pay.core.domain.pay.repository.jpa.PaySend
import com.pay.core.domain.pay.repository.jpa.PaySendReservation
import com.pay.core.domain.pay.repository.jpa.PaySendReservationJpaRepository
import com.pay.core.domain.type.TransactionStatus
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.time.LocalDateTime

@Repository
class PaySendReservationRepositoryImpl(
    val paySendReservationJpaRepository: PaySendReservationJpaRepository,
    val em:EntityManager
):PaySendReservationRepository {

    override fun save(sendDate: String, sendTime: String, paySend: PaySend): PaySendReservation =
        paySendReservationJpaRepository.save(PaySendReservation(
            createDt = LocalDateTime.now(),
            sendDate = sendDate,
            sendTime = sendTime,
            paySend = paySend
        ))

    override fun findByReservationPaySend(sendDate: String, sendTime: String): List<PaySendReservationDto>  {
//        return paySendReservationJpaRepository.findByReservationPaySend("20241124", "1100", TransactionStatus.SEND_COMPLETE.name)
        val sql =
            """
        SELECT PS.PAY_SEND_SEQ
             , PS.AMOUNT
             , PS.FEE_AMOUNT
             , PS.TRANSACTION_STATUS
             , PS.SEND_ACCOUNT_SEQ
             , PS.RECEIVE_ACCOUNT_SEQ
          FROM PAY_SEND PS
          JOIN PAY_SEND_RESERVATION PSR
            ON PS.PAY_SEND_SEQ = PSR.PAY_SEND_SEQ
         WHERE PSR.SEND_DATE = :sendDate
           AND PSR.SEND_TIME = :sendTime
           AND PS.TRANSACTION_STATUS = :transactionStatus
            """
        val result = em.createNativeQuery(sql)
            .setParameter("sendDate", sendDate)
            .setParameter("sendTime", sendTime)
            .setParameter("transactionStatus", TransactionStatus.SEND_COMPLETE.name)
            .resultList as List<Array<Any>>

        return result.map { row ->
            PaySendReservationDto(
                paySendSeq = (row[0] as Number).toLong(),
                amount = (row[1] as Number).toLong(),
                feeAmount = (row[2] as Number).toLong(),
                transactionStatus = row[3] as String,
                sendAccountSeq = (row[4] as Number).toLong(),
                receiveAccountSeq = (row[5] as Number).toLong()
            )
        }
    }
}