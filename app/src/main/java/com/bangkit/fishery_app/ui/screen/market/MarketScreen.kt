package com.bangkit.fishery_app.ui.screen.market

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.ui.components.CardPosting
import kotlinx.coroutines.launch

@Composable
fun MarketScreen(
    moveToDetailPost: (idPost: Int) -> Unit,
    moveAddPost: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MarketViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MarketContent(
        moveToDetailPost = moveToDetailPost,
        moveAddPost = moveAddPost,
        listPost = state.listPosts,
        query = state.title,
        onQueryChange = { query ->
            viewModel.viewModelScope.launch {
                viewModel.onEvent(MarketEvent.OnSearchQueryChanged(query))
            }
        },
        onSearch = {
            viewModel.viewModelScope.launch {
                viewModel.onEvent(MarketEvent.GetPosts(state.title))
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketContent(
    moveToDetailPost: (idPost: Int) -> Unit,
    moveAddPost: () -> Unit,
    listPost: List<PostModel>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
        ) {
            SearchBar(
                query = query,
                onQueryChange = onQueryChange,
                onSearch = { onSearch() },
                active = false,
                onActiveChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                placeholder = {
                    Text(
                        text = stringResource(R.string.search)
                    )
                },
                shape = MaterialTheme.shapes.large,
                modifier = modifier
                    .weight(2f)
                    .heightIn(min = 48.dp)
            ) {

            }

            FilledIconButton(
                onClick = { moveAddPost() },
                modifier = modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = modifier.padding(top = 16.dp)
        ) {
            items(listPost, key = { it.idPost }) { post ->
                CardPosting(
                    username = post.username,
                    imgUser = post.userPhoto,
                    date = post.date,
                    imagePost = post.image,
                    title = post.title,
                    description = post.description,
                    modifier = modifier.clickable {
                        moveToDetailPost(post.idPost)
                    }
                )
            }
        }
    }
}
