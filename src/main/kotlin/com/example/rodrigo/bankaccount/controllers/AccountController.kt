package com.example.rodrigo.bankaccount.controllers

import com.example.rodrigo.bankaccount.entity.Account
import com.example.rodrigo.bankaccount.repositories.AccountRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("accounts")
class AccountController (val accountRepository: AccountRepository) {

    @PostMapping
    fun create(@RequestBody account: Account) = ResponseEntity.ok(accountRepository.save(account))

    @GetMapping
    fun read() = ResponseEntity.ok(accountRepository.findAll())

    @PutMapping("{document}")
    fun update(@PathVariable document: String, @RequestBody account: Account): ResponseEntity<Account>{
        val accountRetrievedOptional = accountRepository.findByDocument(document)
        val accountToSave = accountRetrievedOptional
            .orElseThrow{RuntimeException("Account document: $document not found!")}
            .copy(name = account.name, balance = account.balance, active = account.active)
        return ResponseEntity.ok(accountToSave)
    }

    @DeleteMapping("{document}")
    fun delete(@PathVariable document: String) = accountRepository.findByDocument(document).ifPresent{accountRepository.delete(it)}
}