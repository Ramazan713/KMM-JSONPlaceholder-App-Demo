package com.example.kmmjsonplaceholderapp.android.presentation.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.kmmjsonplaceholderapp.android.presentation.detail.components.PostRow
import com.example.myjsonplaceholderapp.presentation.dia.GetInputDia
import com.example.kmmjsonplaceholderapp.android.presentation.utils.ListenMessage

@Composable
fun DetailPage(
    state: DetailState,
    onEvent: (DetailEvent) -> Unit,
) {

    ListenMessage(
        message = state.message,
        onClearMessage = { onEvent(DetailEvent.ClearMessage) }
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(DetailEvent.ShowDialog(DetailDialogEvent.AddPost))
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) {paddings->
        Box(
            modifier = Modifier
                .padding(paddings)
                .fillMaxSize()
        ){
            if(state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .zIndex(2f)
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                item {
                    Button(onClick = {
                        onEvent(DetailEvent.Refresh)
                    }) {
                        Text(text = "Refresh")
                    }
                }
                items(state.items){item->
                    PostRow(
                        postComments = item,
                        onDelete = {
                            onEvent(DetailEvent.DeletePost(item.post.id))
                        },
                        onUpdate = {
                            onEvent(DetailEvent.ShowDialog(DetailDialogEvent.UpdatePost(item.post)))
                        },
                        onDeleteComment = {
                            onEvent(DetailEvent.DeleteComment(it))
                        }
                    )
                }
            }
        }
    }

    state.dialogEvent?.let { dialogEvent->
        when(dialogEvent){
            DetailDialogEvent.AddPost -> {
                GetInputDia(
                    onDismiss = { onEvent(DetailEvent.ShowDialog(null)) },
                    title = "Add Post",
                    onApproved = {
                        onEvent(DetailEvent.AddPost(it))
                    }
                )
            }
            is DetailDialogEvent.UpdatePost -> {
                GetInputDia(
                    onDismiss = { onEvent(DetailEvent.ShowDialog(null)) },
                    title = "Update Post",
                    content = dialogEvent.post.body,
                    onApproved = {
                        onEvent(DetailEvent.UpdatePost(it,dialogEvent.post))
                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DetailPagePreview() {
    DetailPage(
        state = DetailState(),
        onEvent = {}
    )
}