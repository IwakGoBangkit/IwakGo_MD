package com.bangkit.fishery_app.ui.screen.market

sealed class MarketEvent {
    data class OnSearchQueryChanged(val query: String): MarketEvent()
    data class GetPosts(val title: String): MarketEvent()

}