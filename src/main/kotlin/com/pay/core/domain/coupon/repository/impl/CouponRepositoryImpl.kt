package com.pay.core.domain.coupon.repository.impl

import com.pay.core.domain.coupon.repository.CouponRepository
import com.pay.core.domain.coupon.repository.jpa.Coupon
import com.pay.core.domain.coupon.repository.jpa.CouponJpaRepository
import com.pay.core.domain.type.CouponType
import org.springframework.stereotype.Repository

@Repository
class CouponRepositoryImpl(
    val couponJpaRepository: CouponJpaRepository
):CouponRepository {

    override fun couponAdd(couponType: CouponType, count:Long): Coupon =
        couponJpaRepository.save(couponJpaRepository.findByCouponType(couponType)
                    .orElseGet { Coupon(couponType = couponType, count = count) }
                    .apply { couponAdd(count) })

    override fun couponIssue(couponType: CouponType, count: Long): Coupon =
        couponJpaRepository.save(couponJpaRepository.findByCouponType(couponType)
            .orElseThrow()
            .apply { couponIssue(count) })
}