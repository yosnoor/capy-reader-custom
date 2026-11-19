package com.capyreader.app.ui.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.glance.GlanceId
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.jocmp.capy.ArticleFilter
import com.jocmp.capy.ArticleStatus

class UnreadCountWidget : GlanceAppWidget() {
    private val repository = WidgetRepository()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                Content()
            }
        }
    }

    override suspend fun providePreview(context: Context, widgetCategory: Int) {
        provideContent {
            GlanceTheme {
                UnreadCountLayout(count = 42L)
            }
        }
    }

    @Composable
    private fun Content() {
        val account = repository.account
        if (repository.isLoggedIn && account != null) {
            val count by account.countUnread(filter = ArticleFilter.Articles(ArticleStatus.UNREAD), query = null).collectAsState(0L)

            UnreadCountLayout(count = count)
        } else {
            UnreadCountLayout(count = null)
        }
    }
}
