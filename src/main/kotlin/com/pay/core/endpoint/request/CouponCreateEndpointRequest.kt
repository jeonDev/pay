package com.pay.core.endpoint.request

import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.type.CouponType

data class CouponCreateEndpointRequest(
    val couponType:CouponType,
    val count:Long
) {
    fun toRequest():CouponCreateRequest {
        return CouponCreateRequest(
            couponType = couponType,
            count = count
        )
    }
}
