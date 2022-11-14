package lotto

import lotto.domain.lotto.Lotto
import lotto.domain.prize.LottoPrize
import lotto.domain.statistics.Profit
import lotto.domain.statistics.WinningLotto
import lotto.domain.store.LottoMachine
import lotto.view.InputView
import lotto.view.OutputView

class LottoGame {
    fun start() {
        val money = InputView().inputPurchaseAmount().toInt()
        val lottos = LottoMachine(money).createLottos()
        buyLotto(lottos)
        val winningLotto = inputLottoNumbers()
        val bonusNumber = inputBonusNumber()
        printStatistics(lottos, winningLotto, bonusNumber, money)
    }

    private fun buyLotto(lottos: List<List<Int>>) {
        OutputView().printInputPurchaseAmount()
        println()
        val count = lottos.size
        OutputView().printLottoPurchaseCount(count)
        lottos.forEach { lotto ->
            println(lotto.toString())
        }
        println()
    }

    private fun inputLottoNumbers(): List<Int> {
        OutputView().printInputWinningNumbers()
        val winningLotto = InputView().inputWinningNumber()
        println()
        return winningLotto
    }

    private fun inputBonusNumber(): Int {
        OutputView().printBonusNumber()
        val bonusNumber = InputView().inputBonusNumber()
        println()
        return bonusNumber
    }

    private fun printStatistics(
        lottos: List<List<Int>>,
        winningLotto: List<Int>,
        bonusNumber: Int,
        money: Int
    ) {
        val lotties = lottos.map { Lotto(it) }
        val prizeCount = WinningLotto(lotties, bonusNumber).countWinningLotto(winningLotto)
        OutputView().printWinningStatistics(prizeCount)
        printProfit(prizeCount, money)
    }

    private fun printProfit(prizeCount: Map<LottoPrize, Int>, money: Int) {
        val profitRate = Profit(prizeCount, money).calculateRate()
        OutputView().printPrifitRate(profitRate)
    }
}