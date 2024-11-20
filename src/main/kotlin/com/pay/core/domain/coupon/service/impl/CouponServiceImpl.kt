package com.pay.core.domain.coupon.service.impl

import com.pay.core.domain.coupon.repository.*
import com.pay.core.domain.coupon.request.CouponCreateRequest
import com.pay.core.domain.coupon.request.CouponIssueRequest
import com.pay.core.domain.coupon.response.CouponCreateResponse
import com.pay.core.domain.coupon.response.CouponIssueResponse
import com.pay.core.domain.coupon.response.CouponUseResponse
import com.pay.core.domain.coupon.service.CouponService
import com.pay.core.domain.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class CouponServiceImpl(
    val couponRepository: CouponRepository,
    val couponHistoryRepository: CouponHistoryRepository,
    val couponStorageRepository: CouponStorageRepository,
    val memberService: MemberService
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

        val member = memberService.findByMember(request.memberSeq)
            .orElseThrow()


        couponRepository.save(coupon)
        couponHistoryRepository.save(CouponHistory(
            couponType = coupon.couponType,
            count = 1L
        ))

        val couponStorage = CouponStorage(
            couponType = coupon.couponType,
            createDt = LocalDateTime.now(),
            useYn = false,
        )
        couponStorage.member = member
        couponStorageRepository.save(couponStorage)

        return CouponIssueResponse(
            couponType = coupon.couponType,
            success = true
        )
    }

    override fun couponUse(couponStorageSeq: Long): CouponUseResponse {
        return couponStorageRepository.findById(couponStorageSeq)
            .orElseThrow()
            .let {
                it.couponUse()
                CouponUseResponse(
                    couponType = it.couponType,
                    couponStorageSeq = it.id
                )
            }
    }
}