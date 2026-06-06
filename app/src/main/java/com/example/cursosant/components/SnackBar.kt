package com.example.cursosant.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cursosant.R
import com.example.cursosant.ui.theme.Common_padding_default
import com.example.cursosant.ui.theme.Common_padding_min
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
private fun AntSnackbarPreview(){
    MaterialTheme {
        AntSnackbar(
            modifier = Modifier.padding(top = 24.dp),
            msgRes = R.string.app_name,
            isPreview = true,
            colorBackground = Color.DarkGray,
            shape = CircleShape,
            onDismiss = {}
        )
    }
}

@Composable
fun AntSnackbar(
    modifier: Modifier,
    msgRes: Int,
    colorBackground: Color = Color.Transparent,
    shape: Shape = RectangleShape,
    isPreview: Boolean = false,
    onDismiss: () -> Unit
    ){

    var showSnackbar by remember { mutableStateOf(isPreview) }
    var msg = msgRes
    var scope = rememberCoroutineScope()
    LaunchedEffect(msgRes) {
        scope.launch {
            showSnackbar = true
            delay(5_000)
            onDismiss()
            showSnackbar = false
        }
    }

    Box() {
        AnimatedVisibility(
            visible = stringResource(msg).isNotEmpty() && showSnackbar
        ) {
            Text(
                text = stringResource(msg),
                modifier = modifier
                    .background(color = colorBackground, shape = shape)
                    .padding(vertical = Common_padding_min, horizontal = Common_padding_default),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}