package com.example.cursosant.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.cursosant.ui.theme.ProgressBackground

/**
 * Project: JC Retrofit
 * From: com.cursosant.jcretrofit.ui.components
 * Created by: Alain Nicolás Tello
 * On: 27/05/25 at 11:49
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
fun AntProgressFullScreen() {
    Box(Modifier
            .fillMaxSize()
            .background(ProgressBackground)
            .clickable(interactionSource = null, indication = null){},
        contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}