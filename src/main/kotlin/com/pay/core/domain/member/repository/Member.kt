package com.pay.core.domain.member.repository

import jakarta.persistence.*

@Entity
data class Member(

    @Column(name = "NAME")
    var name:String
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_SEQ")
    var id:Long? = null

}
