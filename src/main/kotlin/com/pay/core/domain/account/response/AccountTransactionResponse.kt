package com.pay.core.domain.account.response

import com.pay.core.domain.type.TransactionType

data class AccountTransactionResponse(
    val transactionType: TransactionType
)
