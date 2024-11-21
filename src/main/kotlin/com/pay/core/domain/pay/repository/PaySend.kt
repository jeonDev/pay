package com.pay.core.domain.pay.repository

import com.pay.core.domain.account.repository.jpa.Account
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

) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEND_ACCOUNT_SEQ")
    lateinit var sendAccount: Account

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECEIVE_ACCOUNT_SEQ")
    lateinit var receiveAccount: Account
}
