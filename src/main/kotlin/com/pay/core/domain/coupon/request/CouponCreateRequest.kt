package com.pay.core.domain.coupon.request

import com.pay.core.domain.type.CouponType

data class CouponCreateRequest(
    val couponType:CouponType,
    val count:Long
)
