package com.pay.core.domain.member.repository

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository:JpaRepository<Member, Long> {
}