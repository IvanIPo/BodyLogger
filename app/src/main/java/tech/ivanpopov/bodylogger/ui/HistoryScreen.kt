@file:OptIn(ExperimentalFoundationApi::class)

package tech.ivanpopov.bodylogger.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun HistoryScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        items(5) { index ->
            WeightItemView(LocalContext.current, weight = "Item: $index", onClick = {})
        }
    }
}

@Composable
private fun WeightItemView(context: Context, weight: String, onClick: (msg: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .combinedClickable(
                onClick = { },
                onLongClick = {
                    Toast
                        .makeText(context, "Do you wanna change this entry?", Toast.LENGTH_SHORT)
                        .show()
                },
            )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text = weight)
        }
    }

}