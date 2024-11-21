package com.pay.core.domain.pay.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface PaySendJpaRepository:JpaRepository<PaySend, Long> {
}