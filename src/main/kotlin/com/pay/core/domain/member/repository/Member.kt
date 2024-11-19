package com.pay.core.domain.member.repository

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    var id:Long? = null,

    @Column(name = "NAME")
    var name:String,

    @Column(name = "CREATE_DT", nullable = false)
    var createDt: LocalDateTime
)