package com.pay.core.domain.pay.repository

import org.springframework.data.jpa.repository.JpaRepository

interface PaySendRepository:JpaRepository<PaySend, Long> {
}