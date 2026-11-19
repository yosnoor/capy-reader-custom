package com.capyreader.app.ui.widget

import com.capyreader.app.preferences.AppPreferences
import com.jocmp.capy.Account
import com.jocmp.capy.logging.CapyLog
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class WidgetRepository : KoinComponent {
    private val appPreferences by inject<AppPreferences>()

    val isLoggedIn: Boolean
        get() = try {
            appPreferences.isLoggedIn
        } catch (e: Exception) {
            CapyLog.error("widget_is_logged_in", e)
            false
        }

    val account: Account?
        get() {
            if (!isLoggedIn) return null
            return try {
                getKoin().getOrNull<Account>()
            } catch (e: Exception) {
                CapyLog.error("widget_account", e)
                null
            }
        }
}
