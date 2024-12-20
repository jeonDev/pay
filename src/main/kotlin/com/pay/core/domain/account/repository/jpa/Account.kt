package com.pay.core.domain.account.repository.jpa

import com.pay.core.domain.member.repository.jpa.Member
import com.pay.core.domain.type.PayType
import com.pay.core.domain.type.TransactionType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.math.BigInteger
import java.time.LocalDateTime

@DynamicUpdate
@Entity
data class Account (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_SEQ")
    var id:Long? = null,
    
    @Column(name = "AMOUNT", nullable = false, columnDefinition = "INT DEFAULT 0 CHECK (amount >= 0)")
    var amount:BigInteger,

    @Column(name = "CREATE_DT", nullable = false)
    val createDt:LocalDateTime
) {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    lateinit var member: Member


    fun transaction(transactionType: TransactionType, amount:BigInteger, payType: PayType, paySeq:Long?): AccountHistory {
        this.amount = transactionType.calculation(this.amount, amount)
        if (this.amount < BigInteger.ZERO) throw RuntimeException()

        return AccountHistory(
            transactionType = transactionType,
            createDt = LocalDateTime.now(),
            amount = amount,
            balance = this.amount,
            payType = payType,
            paySeq = paySeq
        )
    }
}