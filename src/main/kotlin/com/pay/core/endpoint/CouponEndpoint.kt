package com.pay.core.endpoint

import com.pay.core.domain.coupon.service.CouponService
import com.pay.core.endpoint.request.CouponCreateEndpointRequest
import com.pay.core.endpoint.request.CouponIssueEndpointRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponEndpoint(
    val couponService: CouponService
) {

    @PostMapping("/v1/coupon/create")
    fun create(@RequestBody request:CouponCreateEndpointRequest) =
        request.toRequest()
            .let { couponService.create(it) }

    @PutMapping("/v1/coupon/issue")
    fun issue(@RequestBody request:CouponIssueEndpointRequest) =
        request.toRequest()
            .let { couponService.issue(it) }
}