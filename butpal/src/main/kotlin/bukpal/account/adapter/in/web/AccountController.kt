package bukpal.account.adapter.`in`.web

import bukpal.account.application.port.`in`.SendMoneyCommand
import bukpal.account.application.port.`in`.SendMoneyUseCase
import bukpal.account.domain.Account
import bukpal.account.domain.Money
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class AccountController (val sendMoneyUseCase: SendMoneyUseCase){
    @PostMapping( "/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        @PathVariable sourceAccountId: Long,
        @PathVariable targetAccountId: Long,
        @PathVariable amount: Long
    )
    {
        val command : SendMoneyCommand = SendMoneyCommand(
            Account.AccountId(sourceAccountId),
            Account.AccountId(sourceAccountId),
            Money.of(amount)
        )
        sendMoneyUseCase.sendMoney(command)
    }
}