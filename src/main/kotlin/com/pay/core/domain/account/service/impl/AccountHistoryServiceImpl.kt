package com.pay.core.domain.account.service.impl

import com.pay.core.domain.account.repository.AccountHistory
import com.pay.core.domain.account.repository.AccountHistoryRepository
import com.pay.core.domain.account.service.AccountHistoryService
import org.springframework.stereotype.Service

@Service
class AccountHistoryServiceImpl(
    val accountHistoryRepository: AccountHistoryRepository
):AccountHistoryService {

    override fun create(accountHistory: AccountHistory) {
        accountHistoryRepository.save(accountHistory)
    }
}