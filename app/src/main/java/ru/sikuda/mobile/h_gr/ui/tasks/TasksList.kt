package ru.sikuda.mobile.h_gr.ui.tasks

import android.app.Application
import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class TaskItem(
    val id: Long,
    val text: String,
)

@Composable
fun TasksList(name: String?, onNavigation: () -> Unit) {

    val context = LocalContext.current

    val items = listOf(
        TaskItem( 1, "Text1"),
        TaskItem( 2, "Text2"),
        TaskItem( 3, "Text3"),
        TaskItem( 4, "Text4"),
        TaskItem( 5, "Text5"),
    )
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(items.size) { it ->
            Item(
                items[it],
                onClicked = { OnClickTaskItem(context, items[it].id) },
            )
            Divider()
        }
    }
}

fun OnClickTaskItem(context: Context, id: Long) {
    Toast.makeText(context, "Click $id", Toast.LENGTH_SHORT).show()
}

@Composable
private fun Item(
    item: TaskItem,
    onClicked: () -> Unit,
) {

    Row(modifier = Modifier.clickable(onClick = onClicked)) {

//        Spacer(modifier = Modifier.width(20.dp))

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
            fontSize = 40.sp,
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

