package com.aiden.aiden.architecturepatternstudy.data.source.local

import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitDataSource

class UpbitLocalDataSource(private val db: UpbitDatabase?) : UpbitDataSource {

    override fun getMarketList(
        onSuccess: (List<String>) -> Unit,
        onFail: (Throwable?) -> Unit
    ) {
        // do not use
    }

    override fun getTickerList(
        marketList: List<String>,
        isUsingLocalDb: Boolean,
        onSuccess: (List<TickerResponse>) -> Unit,
        onFail: (Throwable?) -> Unit
    ) {

        Thread(Runnable {
            try {
                onSuccess(db!!.tickerDao().getByMarket(marketList[0]))
            } catch (e: Exception) {
                onFail(e)
            }
        }).start()

    }

    override fun saveTickerList(
        tickerList: List<TickerResponse>,
        onSuccess: (List<TickerResponse>) -> Unit,
        onFail: (Throwable?) -> Unit
    ) {
        Thread(Runnable {
            try {
                db!!.tickerDao().insert(tickerList)
                onSuccess(tickerList)
            } catch (e: Exception) {
                onFail(e)
            }
        }).start()
    }

}