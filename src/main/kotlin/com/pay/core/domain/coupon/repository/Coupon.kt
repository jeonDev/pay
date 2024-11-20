package com.pay.core.domain.coupon.repository

import com.pay.core.domain.type.CouponType
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate

@DynamicUpdate
@Entity
data class Coupon(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_SEQ")
    var id:Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "COUPON_TYPE", nullable = false)
    val couponType: CouponType,

    @Column(name = "COUNT", nullable = false, columnDefinition = "INT DEFAULT 0 CHECK (count >= 0)")
    var count:Long
) {

    fun couponAdd(count:Long) {
        this.count += count
    }

    fun couponIssue() {
        if (this.count - count < 0)
            throw RuntimeException()

        this.count -= count
    }
}
