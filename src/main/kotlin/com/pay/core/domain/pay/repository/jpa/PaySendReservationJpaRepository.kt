package com.pay.core.domain.pay.repository.jpa

import com.pay.core.domain.pay.repository.dto.PaySendReservationDto
import com.pay.core.domain.type.TransactionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PaySendReservationJpaRepository:JpaRepository<PaySendReservation, Long> {

    @Query(value =
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
    """,
        nativeQuery = true
    )
    fun findByReservationPaySend(@Param("sendDate") sendDate:String,
                                 @Param("sendTime") sendTime:String,
                                 @Param("transactionStatus") transactionStatus: TransactionStatus): List<PaySendReservationDto>
}