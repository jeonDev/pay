package com.pay.core.domain.coupon.repository.impl

import com.pay.core.domain.coupon.repository.CouponHistoryRepository
import com.pay.core.domain.coupon.repository.jpa.CouponHistory
import com.pay.core.domain.coupon.repository.jpa.CouponHistoryJpaRepository
import com.pay.core.domain.type.CouponType
import org.springframework.stereotype.Repository

@Repository
class CouponHistoryRepositoryImpl(
    val couponHistoryJpaRepository: CouponHistoryJpaRepository
):CouponHistoryRepository {

    override fun couponHistoryAdd(couponType: CouponType, count: Long): CouponHistory =
        couponHistoryJpaRepository.save(
            CouponHistory(
                couponType = couponType,
                count = count
            )
        )
}