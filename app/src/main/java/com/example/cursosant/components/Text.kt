package com.example.cursosant.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.cursosant.ui.theme.Common_padding_default
import com.example.cursosant.ui.theme.Typography

/**
 * Project: JC Room
 * From: com.cursosant.jcroom.ui.components
 * Created by: Alain Nicolás Tello.
 * On: 02/07/25 at 13:50
 * All rights reserved 2025.
 *
 * All my Udemy Courses:
 * https://www.udemy.com/user/alain-nicolas-tello/
 * And Frogames formación:
 * https://cursos.frogamesformacion.com/pages/instructor-alain-nicolas
 *
 * Coupons on my Website:
 * www.alainnicolastello.com
 */
 
@Composable
fun AntTextTitle(titleRes: Int) {
    Text(text = stringResource(titleRes),
        style = Typography.headlineMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth().padding(top = Common_padding_default))
}