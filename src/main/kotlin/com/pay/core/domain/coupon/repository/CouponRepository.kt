package com.pay.core.domain.coupon.repository

import com.pay.core.domain.type.CouponType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CouponRepository:JpaRepository<Coupon, Long> {
    fun findByCouponType(couponType: CouponType):Optional<Coupon>
}