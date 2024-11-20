package com.pay.core.domain.coupon.request

import com.pay.core.domain.type.CouponType

data class CouponIssueRequest(
    val couponType: CouponType
)
