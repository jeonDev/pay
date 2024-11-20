package com.pay.core.domain.coupon.response

import com.pay.core.domain.type.CouponType

data class CouponUseResponse(
    val couponStorageSeq: Long?,
    val couponType: CouponType
)
