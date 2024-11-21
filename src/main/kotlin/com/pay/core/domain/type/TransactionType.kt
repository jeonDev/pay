package com.pay.core.domain.type

import java.math.BigInteger

enum class TransactionType {
    DEPOSIT {
        override fun calculation(balance: BigInteger, amount: BigInteger): BigInteger = balance.add(amount)
    }, WITHDRAW {
        override fun calculation(balance: BigInteger, amount: BigInteger): BigInteger = balance.subtract(amount)
    };


    abstract fun calculation(balance:BigInteger, amount:BigInteger): BigInteger;
    companion object {
        fun of(transactionType:String):TransactionType {
            if ("DEPOSIT".equals(transactionType)) return DEPOSIT
            if ("WITHDRAW".equals(transactionType)) return WITHDRAW
            throw RuntimeException()
        }
    }
}