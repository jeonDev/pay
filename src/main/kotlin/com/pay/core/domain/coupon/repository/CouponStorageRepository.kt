package com.pay.core.domain.coupon.repository

import com.pay.core.domain.coupon.repository.jpa.CouponStorage
import com.pay.core.domain.coupon.response.CouponUseResponse
import com.pay.core.domain.member.repository.Member
import com.pay.core.domain.type.CouponType

interface CouponStorageRepository {

    fun couponSave(couponType: CouponType, member:Member):CouponStorage
    fun findByCouponStorageSeq(couponStorageSeq:Long): CouponStorage
    fun couponUse(couponStorageSeq: Long): CouponUseResponse
}