package com.pay.core.domain.coupon.service

import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.coupon.response.CouponCreateResponse
import com.pay.core.domain.coupon.response.CouponIssueResponse

interface CouponService {

    fun create(request:CouponCreateRequest): CouponCreateResponse
    fun issue(request:CouponIssueRequest):CouponIssueResponse
}