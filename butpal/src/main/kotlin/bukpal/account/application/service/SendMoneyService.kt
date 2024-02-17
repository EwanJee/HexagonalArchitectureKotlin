package bukpal.account.application.service

import bukpal.account.application.port.`in`.SendMoneyCommand
import bukpal.account.application.port.`in`.SendMoneyUseCase
import bukpal.account.application.port.out.AccountLock
import bukpal.account.application.port.out.LoadAccountPort
import bukpal.account.application.port.out.UpdateAccountStatePort
import bukpal.account.domain.Account
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@RequiredArgsConstructor
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val accountLock: AccountLock,
    private val updateAccountStatePort: UpdateAccountStatePort,
    private val moneyTransferProperties : MoneyTransferProperties
) : SendMoneyUseCase{
    override fun sendMoney(command: SendMoneyCommand): Boolean {
        checkThreshold(command)
        val baselineDate : LocalDateTime = LocalDateTime.now().minusDays(10)
        val sourceAccount : Account = loadAccountPort.loadAccount(
            command.targetAccountId,
            baselineDate
        )
        val targetAccount : Account = loadAccountPort.loadAccount(
            command.targetAccountId,
            baselineDate
        )
        val sourceAccountId : Account.AccountId = sourceAccount.getId()
        val targetAccountId : Account.AccountId = targetAccount.getId()
        if(!sourceAccount.withdraw(command.money, targetAccountId)){
            accountLock.releaseAccount(sourceAccountId)
            return false
        }
        if(!targetAccount.withdraw(command.money, sourceAccountId)){
            accountLock.releaseAccount(sourceAccountId)
            accountLock.releaseAccount(targetAccountId)
            return false
        }
        updateAccountStatePort.updateActivities(sourceAccount)
        updateAccountStatePort.updateActivities(targetAccount)

        accountLock.releaseAccount(sourceAccountId)
        accountLock.releaseAccount(targetAccountId)

        return true;
    }

    private fun checkThreshold(command: SendMoneyCommand) {
        if(command.money.isGreaterThan(moneyTransferProperties.maximumTransferThreshold)){
            throw RuntimeException()
        }
    }

}
