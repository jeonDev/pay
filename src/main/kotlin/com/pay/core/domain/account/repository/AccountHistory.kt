package com.pay.core.domain.account.repository

import com.pay.core.domain.type.TransactionType
import jakarta.persistence.*
import java.math.BigInteger
import java.time.LocalDateTime

@Entity
data class AccountHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_SEQ")
    var id:Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "TRANSACTION_TYPE")
    var transactionType: TransactionType,

    @Column(name = "AMOUNT")
    var amount: BigInteger,

    @Column(name = "BALANCE")
    var balance: BigInteger,

    @Column(name = "CREATE_DT")
    val createDt: LocalDateTime
)
