package tech.ivanpopov.bodylogger.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tech.ivanpopov.bodylogger.R

@Composable
@Preview
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Box(modifier = Modifier.background(color = MaterialTheme.colors.background)) {
            Image(
                painter = painterResource(R.drawable.ic_baseline_monitor_weight_24),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
            )
            Text(text = "--",
                modifier = Modifier.align(Alignment.TopCenter).padding(vertical = 230.dp),
                fontSize = 64.sp
            )
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier.align(Alignment.BottomCenter).padding(vertical = 220.dp).height(60.dp),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Enter weight",color = Color.Black)
            }
        }

    }
}