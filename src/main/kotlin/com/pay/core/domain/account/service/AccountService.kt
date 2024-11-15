package com.pay.core.domain.account.service;

import com.pay.core.domain.account.request.AccountCreateRequest
import com.pay.core.domain.account.request.AccountTransactionRequest
import com.pay.core.domain.account.response.AccountCreateResponse
import com.pay.core.domain.account.response.AccountTransactionResponse

interface AccountService {

    fun create(request:AccountCreateRequest):AccountCreateResponse
    fun transaction(request: AccountTransactionRequest):AccountTransactionResponse
}