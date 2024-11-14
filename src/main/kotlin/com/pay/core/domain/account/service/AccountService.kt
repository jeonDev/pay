package com.pay.core.domain.account.service;

import com.pay.core.domain.account.request.AccountCreateRequest
import com.pay.core.domain.account.response.AccountCreateResponse

interface AccountService {

    fun create(accountCreateRequest:AccountCreateRequest):AccountCreateResponse
}