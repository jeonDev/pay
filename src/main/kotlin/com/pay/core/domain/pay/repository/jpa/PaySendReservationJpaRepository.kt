package com.pay.core.domain.pay.repository.jpa

import com.pay.core.domain.type.TransactionStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PaySendReservationJpaRepository:JpaRepository<PaySendReservation, Long> {

    @Query(value =
    """
        SELECT PS
          FROM PaySend PS
          JOIN FETCH PaySendReservation PSR
            ON PS.id = PSR.paySend.id
         WHERE PSR.sendDate = :sendDate
           AND PSR.sendTime = :sendTime
           AND PS.transactionStatus = :transactionStatus
    """
    )
    fun findByReservationPaySend(@Param("sendDate") sendDate:String,
                                 @Param("sendTime") sendTime:String,
                                 @Param("transactionStatus") transactionStatus: TransactionStatus): List<PaySend>
}