package com.pay.core.domain.coupon.repository.impl

import com.pay.core.domain.coupon.repository.CouponStorageRepository
import com.pay.core.domain.coupon.repository.jpa.CouponStorage
import com.pay.core.domain.coupon.repository.jpa.CouponStorageJpaRepository
import com.pay.core.domain.coupon.response.CouponUseResponse
import com.pay.core.domain.member.repository.Member
import com.pay.core.domain.type.CouponType
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class CouponStorageRepositoryImpl(
    val couponStorageJpaRepository: CouponStorageJpaRepository
):CouponStorageRepository {

    override fun couponSave(couponType: CouponType, member: Member): CouponStorage {
        val couponStorage = CouponStorage(
            couponType = couponType,
            createDt = LocalDateTime.now(),
            useYn = false,
        )
        couponStorage.member = member
        return couponStorageJpaRepository.save(couponStorage)
    }

    override fun findByCouponStorageSeq(couponStorageSeq: Long): CouponStorage =
        couponStorageJpaRepository.findByIdAndUseYn(couponStorageSeq, false)
            .orElseThrow()

    override fun couponUse(couponStorageSeq: Long): CouponUseResponse =
        couponStorageJpaRepository.findById(couponStorageSeq)
            .orElseThrow()
            .let {
                it.couponUse()
                CouponUseResponse(
                    couponType = it.couponType,
                    couponStorageSeq = it.id
                )
            }

}