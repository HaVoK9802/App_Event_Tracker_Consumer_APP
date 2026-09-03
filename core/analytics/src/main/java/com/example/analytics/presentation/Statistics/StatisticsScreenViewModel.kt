package com.example.analytics.presentation.Statistics

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.analytics.domain.Statistics.models.CollectiveEventStatistics
import com.example.analytics.domain.Statistics.models.EventStat
import com.example.analytics.domain.Statistics.repo.EventStatisticsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.text.format

@HiltViewModel
class StatisticsScreenViewModel @Inject constructor(
   private val eventStatisticsRepo: EventStatisticsRepo
): ViewModel() {


    var collectiveEventStatistics = mutableStateOf(CollectiveEventStatistics())
        private set

    init {
        fetchData()
    }

    fun fetchData(){
        viewModelScope.launch(Dispatchers.IO) {
            val totalProcessedEvents = eventStatisticsRepo.getTotalProcessedEvents()
            val totalInstalls = eventStatisticsRepo.getTotalInstalls()
            val totalVisits = eventStatisticsRepo.getTotalVisits()
            val totalUniqueScreenVisits = eventStatisticsRepo.getTotalUniqueScreenVisits()
            val totalCartItemsAdded = eventStatisticsRepo.getTotalCartItemsAdded()
            val totalItemPurchases = eventStatisticsRepo.getTotalItemPurchases()

            collectiveEventStatistics.value = CollectiveEventStatistics(
                totalProcessedEvents = EventStat(
                    count = totalProcessedEvents,
                    percentage = 100f
                ),
                installs = EventStat(
                    count = totalInstalls,
                    percentage = (totalInstalls.toFloat()/totalProcessedEvents)*100
                ),
                visits = EventStat(
                    count = totalVisits,
                    percentage = (totalVisits.toFloat()/totalProcessedEvents)*100
                ),
                screenVisits = EventStat(
                    count = totalUniqueScreenVisits,
                    percentage = (totalUniqueScreenVisits.toFloat()/totalProcessedEvents)*100
                ),
                itemsAddedToCart = EventStat(
                    count = totalCartItemsAdded,
                    percentage = (totalCartItemsAdded.toFloat()/totalProcessedEvents)*100
                ),
                purchases = EventStat(
                    count = totalItemPurchases,
                    percentage = (totalItemPurchases.toFloat()/totalProcessedEvents)*100
                )
            )
        }
    }

}