package ru.sikuda.mobile.h_gr.ui.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


data class TaskItem(
    val id: Long,
    val text: String,
)

@Composable
fun TasksList() {

    val items = listOf(TaskItem(1, "Text1"), TaskItem(2, "Text2"))
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(items.size) { it ->
            Item(
                items[it],
                onClicked = { OnClickTaskItem(items[it].id) },
            )
            Divider()
        }
    }
}

fun OnClickTaskItem(id: Long){

}

@Composable
private fun Item(
    item: TaskItem,
    onClicked: () -> Unit,
) {

    Row(modifier = Modifier.clickable(onClick = onClicked)) {

        Spacer(modifier = Modifier.width(8.dp))

//        Checkbox(
//            checked = item.isDone,
//            modifier = Modifier.align(Alignment.CenterVertically),
//            onCheckedChange = onDoneChanged,
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
        Text(
            text = AnnotatedString(item.text),
            modifier = Modifier
                .weight(1F)
                .align(Alignment.CenterVertically),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        IconButton(onClick = onDeleteClicked) {
//            Icon(
//                imageVector = Icons.Default.Delete,
//                contentDescription = null
//            )
//        }
//
//        Spacer(modifier = Modifier.width(MARGIN_SCROLLBAR))
    }
}

