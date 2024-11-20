package com.pay.core.domain.coupon.response

import com.pay.core.domain.type.CouponType

data class CouponIssueResponse(
    val couponType: CouponType,
    val success:Boolean
)
