package com.pay.core.domain.coupon.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CouponStorageJpaRepository:JpaRepository<CouponStorage, Long> {
    fun findByIdAndUseYn(couponStorageSeq:Long, useYn:Boolean):Optional<CouponStorage>
}