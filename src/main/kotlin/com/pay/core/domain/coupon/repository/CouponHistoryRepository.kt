package com.pay.core.domain.coupon.repository

import com.pay.core.domain.coupon.repository.jpa.CouponHistory
import com.pay.core.domain.type.CouponType

interface CouponHistoryRepository {

    fun couponHistoryAdd(couponType: CouponType, count:Long):CouponHistory
}