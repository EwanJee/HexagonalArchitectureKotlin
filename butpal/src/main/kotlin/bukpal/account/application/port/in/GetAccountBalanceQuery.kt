package bukpal.account.application.port.`in`

import bukpal.account.domain.Account
import bukpal.account.domain.Money

interface GetAccountBalanceQuery{
    fun getAccountBalance(accountId: Account.AccountId): Money
}