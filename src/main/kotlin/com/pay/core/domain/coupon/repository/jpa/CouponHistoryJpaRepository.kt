package com.pay.core.domain.coupon.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CouponHistoryJpaRepository:JpaRepository<CouponHistory, Long> {
}