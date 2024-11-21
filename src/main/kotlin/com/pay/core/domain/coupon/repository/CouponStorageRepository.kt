package com.pay.core.domain.coupon.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface CouponStorageRepository:JpaRepository<CouponStorage, Long> {
    fun findByIdAndUseYn(couponStorageSeq:Long, useYn:Boolean):Optional<CouponStorage>
}