package com.pay.core.domain.coupon.repository

import com.pay.core.domain.coupon.repository.jpa.Coupon
import com.pay.core.domain.type.CouponType

interface CouponRepository {
    fun couponAdd(couponType: CouponType, count:Long): Coupon
    fun couponIssue(couponType: CouponType, count:Long): Coupon
}