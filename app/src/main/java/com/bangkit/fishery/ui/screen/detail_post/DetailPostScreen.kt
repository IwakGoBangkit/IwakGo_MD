package com.bangkit.fishery.ui.screen.detail_post

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bangkit.fishery.R
import com.bangkit.fishery.data.model.PostModel
import com.bangkit.fishery.ui.components.PostInfo
import com.bangkit.fishery.util.DummyPostUser.postUser

@Composable
fun DetailPostScreen(
    id: String
) {
    DetailPostContent(
        idPost = id
    )
}


@Composable
fun DetailPostContent(
    idPost: String,
    modifier: Modifier = Modifier
) {

    var post by remember {
        mutableStateOf<PostModel?>(null)
    }

    var showCommentPage by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = idPost) {
        post = getDetailById(idPost)
    }

    if (post != null) {
        Column(
            modifier = modifier.padding(top = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(post!!.userPhoto),
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
                        text = post!!.username,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = modifier
                    )
                    Text(
                        text = post!!.date,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = modifier
                    )
                }
            }

            Image(
                painter = painterResource(post!!.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(280.dp)
            )

            Text(
                text = post!!.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, top = 8.dp)
            )

            PostInfo(
                icon = Icons.Default.LocationOn,
                text = post!!.location
            )

            PostInfo(
                icon = Icons.Default.Call,
                text = post!!.phone
            )

            PostInfo(
                icon = Icons.Default.Info,
                text = post!!.price
            )

            Text(
                text = post!!.description,
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
                Button(
                    onClick = {},
                    modifier = modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = stringResource(R.string.call)
                    )
                    Spacer(modifier = modifier.size(8.dp))
                    Text(stringResource(R.string.call))
                }

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
            }
            if (showCommentPage) {
                CommentPage()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentPage(
    modifier: Modifier = Modifier
) {
    BottomSheetScaffold(
        sheetContent = {

        }
    ) {

    }
}

fun getDetailById(id: String): PostModel? {
    return postUser.find { it.idPost == id }
}