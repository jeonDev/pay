package com.pay.core.domain.coupon.repository

import org.springframework.data.jpa.repository.JpaRepository

interface CouponStorageRepository:JpaRepository<CouponStorage, Long> {
}