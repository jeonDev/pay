package com.pay.core.domain.member.repository

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Member(

    @Column(name = "NAME")
    var name:String,

    @Column(name = "CREATE_DT", nullable = false)
    var createDt: LocalDateTime
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    var id:Long? = null

}
