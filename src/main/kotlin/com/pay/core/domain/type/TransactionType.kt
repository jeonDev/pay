package com.pay.core.domain.type

enum class TransactionType {
    DEPOSIT, WITHDRAW;

    companion object {
        fun of(transactionType:String):TransactionType {
            if ("DEPOSIT".equals(transactionType)) return DEPOSIT
            if ("WITHDRAW".equals(transactionType)) return WITHDRAW
            throw RuntimeException()
        }
    }
}