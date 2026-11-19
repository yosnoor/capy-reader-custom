package com.capyreader.app.ui.widget

import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.ColorFilter
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.action.clickable
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.material3.ColorProviders
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.capyreader.app.R

@Composable
fun UnreadCountLayout(count: Long?) {
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = GlanceModifier
            .fillMaxSize()
            .background(GlanceTheme.colors.widgetBackground)
            .cornerRadius(16.dp)
            .clickable(context.openUnread())
    ) {
        if (count == null) {
            Text(
                text = context.getString(R.string.widget_headlines_account_error),
                style = TextStyle(
                    color = GlanceTheme.colors.onSurface,
                    fontSize = 10.sp,
                ),
                modifier = GlanceModifier.padding(4.dp)
            )
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically,
                modifier = GlanceModifier
                    .fillMaxSize()
                    .padding(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        provider = ImageProvider(R.drawable.capy_icon_small),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(GlanceTheme.colors.secondary),
                        modifier = GlanceModifier.size(14.dp)
                    )
                    Spacer(GlanceModifier.width(2.dp))
                    Text(
                        text = context.getString(R.string.filter_unread),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium,
                            color = GlanceTheme.colors.onSurfaceVariant,
                        )
                    )
                }
                Spacer(GlanceModifier.defaultWeight())
                val countText = count.toString()
                val fontSize = when {
                    countText.length <= 2 -> 28.sp
                    countText.length == 3 -> 22.sp
                    else -> 18.sp
                }
                Text(
                    text = countText,
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        color = GlanceTheme.colors.onSurface,
                    )
                )
                Spacer(GlanceModifier.defaultWeight())
            }
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 72, heightDp = 72)
@Composable
fun UnreadCountLayoutPreview() {
    GlanceTheme {
        UnreadCountLayout(count = 42L)
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 72, heightDp = 72)
@Composable
fun UnreadCountLayoutDarkPreview() {
    GlanceTheme(
        colors = ColorProviders(
            light = darkColorScheme(),
            dark = darkColorScheme(),
        ),
    ) {
        UnreadCountLayout(count = 42L)
    }
}
