package com.pay.core.domain.account.repository

import com.pay.core.domain.member.repository.Member
import jakarta.persistence.*
import java.math.BigInteger

@Entity
data class Account (

    @Column(name = "AMOUNT")
    var amount:BigInteger

) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_SEQ")
    var id:Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    lateinit var member:Member

}