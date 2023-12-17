package com.bangkit.fishery_app.ui.screen.detail_post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.bangkit.fishery_app.R
import com.bangkit.fishery_app.data.model.CommentModel
import com.bangkit.fishery_app.data.model.PostModel
import com.bangkit.fishery_app.ui.components.CardMessageItem
import com.bangkit.fishery_app.ui.components.PostInfo
import com.bangkit.fishery_app.util.DateHelper
import kotlinx.coroutines.launch

@Composable
fun DetailPostScreen(
    id: Int, viewModel: DetailPostViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = id) {
        viewModel.getDetailPostById(id)
        viewModel.getCommentById(id)
    }

    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        state.detailPost?.let { detailPost ->
            DetailPostContent(detailPost = detailPost,
                listComment = state.listComment,
                inputComment = state.inputComment,
                onInputCommentChange = {
                    viewModel.viewModelScope.launch {
                        viewModel.onEvent(DetailPostEvent.OnCommentChanged(it))
                    }
                },
                onSendComment = {
                    viewModel.viewModelScope.launch {
                        viewModel.onEvent(
                            DetailPostEvent.AddComment(
                                id, state.inputComment, state.profilePhoto, state.username
                            )
                        )
                    }
                }
            )
        }
    }

    LaunchedEffect(state.isCommentSuccessful) {
        if (state.isCommentSuccessful) {
            viewModel.viewModelScope.launch {
                viewModel.getCommentById(id)
            }
        }
    }

    if (state.isLoading) {
        CircularProgressIndicator()
    }

}

@Composable
fun DetailPostContent(
    modifier: Modifier = Modifier,
    detailPost: PostModel,
    listComment: List<CommentModel>,
    inputComment: String,
    onInputCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
) {

    var showButtonSheet by rememberSaveable {
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
                    text = DateHelper.formatIsoDate(detailPost.date),
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
            icon = Icons.Default.LocationOn, text = detailPost.location
        )

        PostInfo(
            icon = Icons.Default.Call, text = detailPost.phone
        )

        PostInfo(
            icon = Icons.Default.AttachMoney, text = detailPost.price
        )

        Text(
            text = detailPost.description,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier
                .padding(start = 8.dp, end = 8.dp, top = 32.dp)
                .fillMaxWidth()
        ) {

            OutlinedButton(
                onClick = {
                    showButtonSheet = true
                }, modifier = modifier.weight(1f)
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
                onClick = {}, modifier = modifier.weight(1f)
            ) {
                Icon(
                    imageVector = Icons.Default.CreditCard,
                    contentDescription = stringResource(R.string.buy)
                )
                Spacer(modifier = modifier.size(8.dp))
                Text(stringResource(R.string.buy))
            }
        }

        if (showButtonSheet) {
            CommentPage(
                listComment = listComment,
                onCloseSheet = {
                    showButtonSheet = false
                },
                inputComment = inputComment,
                onInputCommentChange = onInputCommentChange,
                onSendComment = onSendComment
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentPage(
    modifier: Modifier = Modifier,
    listComment: List<CommentModel>,
    onCloseSheet: () -> Unit,
    inputComment: String,
    onInputCommentChange: (String) -> Unit,
    onSendComment: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = sheetState, onDismissRequest = onCloseSheet
    ) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = stringResource(R.string.comment),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, top = 8.dp)
                )
                LazyColumn(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp)
                ) {
                    items(listComment, key = { it.idPost }) { comment ->
                        CardMessageItem(
                            photoProfile = comment.photoProfile,
                            username = comment.username,
                            comment = comment.comment
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier
                        .padding(start = 8.dp, end = 8.dp, top = 32.dp)
                        .fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = inputComment,
                        onValueChange = onInputCommentChange,
                        shape = RoundedCornerShape(24.dp),
                        modifier = modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(
                                onClick = onSendComment,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = stringResource(R.string.send),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.comment), modifier = modifier
                            )
                        },
                    )
                }
            }
        }
    }
}


