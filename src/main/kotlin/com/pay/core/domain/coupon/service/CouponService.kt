package com.pay.core.domain.coupon.service

import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.coupon.response.CouponCreateResponse
import com.pay.core.domain.coupon.response.CouponIssueResponse
import com.pay.core.domain.coupon.response.CouponUseResponse
import com.pay.core.domain.pay.response.CouponStorageResponse

interface CouponService {

    fun create(request:CouponCreateRequest): CouponCreateResponse
    fun issue(request:CouponIssueRequest):CouponIssueResponse
    fun findByCouponStorageSeq(couponStorageSeq: Long):CouponStorageResponse
    fun couponUse(couponStorageSeq:Long):CouponUseResponse
}