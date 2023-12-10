package com.example.kmmjsonplaceholderapp.android.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kmmjsonplaceholderapp.core.domain.models.User
import com.example.kmmjsonplaceholderapp.android.presentation.utils.SampleData

@Composable
fun UserRow(
    modifier: Modifier = Modifier,
    user: User,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    margins: PaddingValues = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
) {
    val shape = MaterialTheme.shapes.medium

    ListItem(
        modifier = modifier
            .padding(margins)
            .clip(shape)
            .clickable {
                onClick()
            }
            .padding(1.dp),
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        leadingContent = {
            Text(text = user.id.toString())
        },
        headlineContent = {
            Text(text = user.name)
        },
        supportingContent = {
            Text(text = user.email)
        },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.DeleteForever, contentDescription = "Delete")
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun UserRowPreview() {
    UserRow(
        user = SampleData.user,
        onClick = {},
        onDelete = {}
    )
}