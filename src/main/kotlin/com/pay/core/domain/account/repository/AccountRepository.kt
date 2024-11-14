package com.pay.core.domain.account.repository

import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository:JpaRepository<Account, Long> {
}