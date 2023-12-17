package com.bangkit.fishery_app.ui.screen.detail_post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.CommentModel
import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.ui.components.PostInfo
import com.bangkit.fishery_app.ui.components.UserComment

@Composable
fun DetailPostScreen(
    id: Int,
    viewModel: DetailPostViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = id) {
        viewModel.getDetailPostById(id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    state.detailPost?.let { detailPost ->
        DetailPostContent(
            detailPost = detailPost,
            listComment = state.listComment
        )
    }

}


@Composable
fun DetailPostContent(
    modifier: Modifier = Modifier,
    detailPost: PostModel,
    listComment: List<CommentModel>,
) {


    var showCommentPage by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = detailPost.userPhoto,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = detailPost.username,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                )
                Text(
                    text = detailPost.date,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    modifier = modifier
                )
            }
        }

        AsyncImage(
            model = detailPost.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .fillMaxWidth()
                .height(280.dp)
        )

        Text(
            text = detailPost.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp, top = 8.dp)
        )

        PostInfo(
            icon = Icons.Default.LocationOn,
            text = detailPost.location
        )

        PostInfo(
            icon = Icons.Default.Call,
            text = detailPost.phone
        )

        PostInfo(
            icon = Icons.Default.AttachMoney,
            text = detailPost.price
        )

        Text(
            text = detailPost.description,
            textAlign = TextAlign.Justify,
            modifier = modifier
                .padding(8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(start = 8.dp, end = 8.dp, top = 32.dp)
                .fillMaxWidth()
        ) {


            OutlinedButton(
                onClick = { showCommentPage = true },
                modifier = modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(R.string.comment)
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(
                    stringResource(R.string.comment)
                )
            }

            Button(
                onClick = {},
                modifier = modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.CreditCard,
                    contentDescription = stringResource(R.string.buy)
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(stringResource(R.string.buy))
            }
        }
        if (showCommentPage) {
            CommentPage(
                listComment = listComment
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentPage(
    modifier: Modifier = Modifier,
    listComment: List<CommentModel>
) {

    var comment = remember {
        mutableStateOf("")
    }

    BottomSheetScaffold(
        sheetContent = {
            LazyColumn(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                items(listComment) { user ->
                    UserComment(
                        image = user.userImage,
                        username = user.username,
                        comment = user.comment
                    )
                }
            }
        }
    ) {

    }
}

