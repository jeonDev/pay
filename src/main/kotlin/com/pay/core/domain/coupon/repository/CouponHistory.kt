package com.pay.core.domain.coupon.repository

import com.pay.core.domain.type.CouponType
import jakarta.persistence.*

@Entity
data class CouponHistory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_HISTORY_SEQ")
    var id:Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "COUPON_TYPE", nullable = false)
    val couponType: CouponType,

    @Column(name = "COUNT")
    var count:Long
)
