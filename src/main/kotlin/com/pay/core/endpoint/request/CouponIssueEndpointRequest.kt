package com.pay.core.endpoint.request

import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.type.CouponType

data class CouponIssueEndpointRequest(
    val couponType:CouponType,
    val memberSeq:Long
) {
    fun toRequest():CouponIssueRequest {
        return CouponIssueRequest(
            couponType = couponType,
            memberSeq = memberSeq
        )
    }
}
