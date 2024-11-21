package com.pay.core.domain.coupon.service.impl

import com.pay.core.domain.coupon.repository.CouponHistoryRepository
import com.pay.core.domain.coupon.repository.CouponRepository
import com.pay.core.domain.coupon.repository.CouponStorageRepository
import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.coupon.response.CouponCreateResponse
import com.pay.core.domain.coupon.response.CouponIssueResponse
import com.pay.core.domain.coupon.response.CouponUseResponse
import com.pay.core.domain.coupon.service.CouponService
import com.pay.core.domain.member.service.MemberService
import com.pay.core.domain.pay.response.CouponStorageResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponServiceImpl(
    val couponRepository: CouponRepository,
    val couponHistoryRepository: CouponHistoryRepository,
    val couponStorageRepository: CouponStorageRepository,
    val memberService: MemberService
):CouponService {

    @Transactional
    override fun create(request: CouponCreateRequest): CouponCreateResponse {
        val coupon = couponRepository.couponAdd(request.couponType, request.count)
        couponHistoryRepository.couponHistoryAdd(coupon.couponType, coupon.count)

        return CouponCreateResponse(
            couponType = coupon.couponType,
            count = coupon.count
        )
    }
    
    @Transactional
    override fun issue(request: CouponIssueRequest): CouponIssueResponse {
        val coupon = couponRepository.couponIssue(request.couponType, 1L)
        couponHistoryRepository.couponHistoryAdd(coupon.couponType, coupon.count)

        val member = memberService.findByMember(request.memberSeq)
            .orElseThrow()

        couponStorageRepository.couponSave(coupon.couponType, member)

        return CouponIssueResponse(
            couponType = coupon.couponType,
            success = true
        )
    }

    override fun findByCouponStorageSeq(couponStorageSeq: Long): CouponStorageResponse =
        couponStorageRepository.findByCouponStorageSeq(couponStorageSeq)
            .let { CouponStorageResponse(
                couponStorageSeq = it.id,
                couponType = it.couponType
            ) }

    override fun couponUse(couponStorageSeq: Long): CouponUseResponse =
        couponStorageRepository.couponUse(couponStorageSeq)
}