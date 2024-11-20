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
        val optionalCoupon = couponRepository.findByCouponType(request.couponType)

        if (optionalCoupon.isPresent) {
            val coupon = optionalCoupon.get()
            coupon.couponAdd(request.count)

            val couponHistory = CouponHistory(
                couponType = request.couponType,
                count = request.count
            )

            couponRepository.save(coupon)
            couponHistoryRepository.save(couponHistory)
            return CouponCreateResponse(
                couponType = coupon.couponType,
                count = coupon.count
            )
        } else {
            val coupon = Coupon(
                couponType = request.couponType,
                count = request.count
            )
            couponRepository.save(coupon)
            val couponHistory = CouponHistory(
                couponType = request.couponType,
                count = request.count
            )
            couponHistoryRepository.save(couponHistory)
            return CouponCreateResponse(
                couponType = coupon.couponType,
                count = coupon.count
            )
        }
    }
    
    @Transactional
    override fun issue(request: CouponIssueRequest): CouponIssueResponse {
        val coupon = couponRepository.findByCouponType(request.couponType)
            .orElseThrow()

        // TODO: 고객번호 넣기 & 고객쿠폰 테이블 추가
        coupon.couponIssue()

        couponRepository.save(coupon)

        val couponHistory = CouponHistory(
            couponType = coupon.couponType,
            count = 1L
        )
        couponHistoryRepository.save(couponHistory)

        return CouponIssueResponse(
            couponType = coupon.couponType,
            success = true
        )
    }
}