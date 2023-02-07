package com.example.rodrigo.bankaccount.repositories

import com.example.rodrigo.bankaccount.entity.Account
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface AccountRepository: MongoRepository<Account, Long> {
    fun findByDocument(document: String): Optional<Account>
}