package ru.sikuda.mobile.h_gr.ui.tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TasksDetail(name: String, onNavigationUp: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxWidth() ) {
        Text(
            text = name,
            modifier = Modifier
                .align(Alignment.Center),
            fontSize = 40.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}