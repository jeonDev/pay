package com.pay.core.domain.coupon.service.impl

import com.pay.core.domain.coupon.repository.Coupon
import com.pay.core.domain.coupon.repository.CouponHistory
import com.pay.core.domain.coupon.repository.CouponHistoryRepository
import com.pay.core.domain.coupon.repository.CouponRepository
import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.coupon.response.CouponCreateResponse
import com.pay.core.domain.coupon.response.CouponIssueResponse
import com.pay.core.domain.coupon.service.CouponService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponServiceImpl(
    val couponRepository: CouponRepository,
    val couponHistoryRepository: CouponHistoryRepository
):CouponService {

    @Transactional
    override fun create(request: CouponCreateRequest): CouponCreateResponse {
        val coupon = couponRepository.findByCouponType(request.couponType)
            .orElseGet { Coupon(couponType = request.couponType, count = 0) }
            .apply { couponAdd(request.count) }

        couponRepository.save(coupon)

        couponHistoryRepository.save(
            CouponHistory(
                couponType = request.couponType,
                count = request.count
            )
        )

        return CouponCreateResponse(
            couponType = coupon.couponType,
            count = coupon.count
        )
    }
    
    @Transactional
    override fun issue(request: CouponIssueRequest): CouponIssueResponse {
        val coupon = couponRepository.findByCouponType(request.couponType)
            .orElseThrow()
            .apply { couponIssue() }
        // TODO: 고객번호 넣기 & 고객쿠폰 테이블 추가

        couponRepository.save(coupon)
        couponHistoryRepository.save(CouponHistory(
            couponType = coupon.couponType,
            count = 1L
        ))

        return CouponIssueResponse(
            couponType = coupon.couponType,
            success = true
        )
    }
}