package bukpal.account.application.port.out

import bukpal.account.domain.Account

interface UpdateAccountStatePort {
    fun updateActivities(account: Account) : Unit
}