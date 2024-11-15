package com.pay.core.domain.account.request

import com.pay.core.domain.type.TransactionType
import java.math.BigInteger

data class AccountTransactionRequest(
    val transactionType:TransactionType,
    val amount: BigInteger,
    val memberSeq: Long
)
