package com.pay.core.domain.pay.response

import com.pay.core.domain.type.CouponType

data class CouponStorageResponse(
    val couponStorageSeq: Long?,
    val couponType: CouponType
)
