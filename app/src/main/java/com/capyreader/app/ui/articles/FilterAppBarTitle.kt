package com.capyreader.app.ui.articles

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capyreader.app.R
import com.capyreader.app.ui.navigationTitle
import com.jocmp.capy.ArticleFilter
import com.jocmp.capy.Feed
import com.jocmp.capy.Folder
import com.jocmp.capy.SavedSearch

@Composable
fun FilterAppBarTitle(
    filter: ArticleFilter,
    allFeeds: List<Feed>,
    allFolders: List<Folder>,
    allSavedSearches: List<SavedSearch>,
    count: Long? = null,
    onRequestJumpToTop: () -> Unit,
) {
    val text = when (filter) {
        is ArticleFilter.Articles -> stringResource(filter.articleStatus.navigationTitle)
        is ArticleFilter.Feeds -> {
            allFeeds.find { it.id == filter.feedID }?.displayTitle()
        }

        is ArticleFilter.Folders -> {
            allFolders.find { it.title == filter.folderTitle }?.title
        }

        is ArticleFilter.SavedSearches ->
            allSavedSearches.find { it.id == filter.savedSearchID }?.name

        is ArticleFilter.Today -> stringResource(R.string.filter_today)
    }.orEmpty()

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onRequestJumpToTop()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f, fill = false)
            )
            if (count != null) {
                Spacer(Modifier.width(8.dp))
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun FilterAppBarTitlePreview() {
    MaterialTheme {
        TopAppBar(title = {
            FilterAppBarTitle(
                filter = ArticleFilter.default(),
                allFeeds = emptyList(),
                allFolders = emptyList(),
                allSavedSearches = emptyList(),
                count = 42L,
                onRequestJumpToTop = {}
            )
        })
    }
}
