package com.pay.core.domain.coupon.repository

import com.pay.core.domain.type.CouponType
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import java.util.Optional

interface CouponRepository:JpaRepository<Coupon, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    fun findByCouponType(couponType: CouponType):Optional<Coupon>
}