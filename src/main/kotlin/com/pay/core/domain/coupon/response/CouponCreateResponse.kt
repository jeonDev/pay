package com.pay.core.domain.coupon.response

import com.pay.core.domain.type.CouponType

data class CouponCreateResponse(
    val couponType: CouponType,
    val count: Long
)