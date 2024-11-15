package com.pay.core.domain.account.service

import com.pay.core.domain.account.repository.AccountHistory

interface AccountHistoryService {
    fun create(accountHistory:AccountHistory)
}