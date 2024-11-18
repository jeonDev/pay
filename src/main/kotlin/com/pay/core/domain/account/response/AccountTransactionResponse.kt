package com.pay.core.domain.account.response

import com.pay.core.domain.type.TransactionType
import java.math.BigInteger

data class AccountTransactionResponse(
    val transactionType: TransactionType,
    val balance: BigInteger
)
