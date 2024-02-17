package bukpal.account.application.port.out

import bukpal.account.domain.Account
import java.time.LocalDateTime

interface LoadAccountPort {
    fun loadAccount(accountId: Account.AccountId, baselineDate : LocalDateTime): Account

}