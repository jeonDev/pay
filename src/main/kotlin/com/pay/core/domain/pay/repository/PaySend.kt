package com.pay.core.domain.pay.repository

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import org.hibernate.annotations.DynamicUpdate
import java.math.BigInteger

@DynamicUpdate
@Entity
data class PaySend(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAY_SEND_SEQ")
    var id:Long? = null,

    @Column(name = "AMOUNT", nullable = false)
    @ColumnDefault("0")
    var amount:BigInteger,

    @Column(name = "FEE_AMOUNT", nullable = false)
    @ColumnDefault("0")
    var feeAmount:BigInteger
    // TODO: 보냄 받음
)
