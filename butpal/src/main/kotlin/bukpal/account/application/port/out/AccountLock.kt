package bukpal.account.application.port.out

import bukpal.account.domain.Account

interface AccountLock {
    fun lockAccount(accountId: Account.AccountId) : Unit
    fun releaseAccount(accountId: Account.AccountId) : Unit
}