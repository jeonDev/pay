package com.pay.core.domain.account.repository.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface AccountHistoryRepository:JpaRepository<AccountHistory, Long> {
}