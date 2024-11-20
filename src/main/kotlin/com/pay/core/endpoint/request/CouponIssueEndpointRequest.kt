package com.pay.core.endpoint.request

import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.type.CouponType

data class CouponIssueEndpointRequest(
    val couponType:CouponType
) {
    fun toRequest():CouponIssueRequest {
        return CouponIssueRequest(
            couponType = couponType
        )
    }
}
