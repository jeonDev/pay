package com.pay.core.domain.pay.repository.jpa

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class PaySendReservation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_SEND_RESERVATION_SEQ")
    var id:Long? = null,

    @Column(name = "CREATE_DT", nullable = false)
    val createDt: LocalDateTime,

    @Column(name = "SEND_DATE")
    val sendDate: String,

    @Column(name = "SEND_TIME")
    val sendTime: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PAY_SEND_SEQ")
    val paySend: PaySend
)