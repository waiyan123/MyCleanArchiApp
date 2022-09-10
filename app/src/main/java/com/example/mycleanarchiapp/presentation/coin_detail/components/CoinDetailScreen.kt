package com.example.mycleanarchiapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycleanarchiapp.presentation.viewmodels.CoinDetailViewModel

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel
) {
    val coinDetailState = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {

                Text(
                    text = "${coinDetailState.coins?.rank}. ${coinDetailState.coins?.name} (${coinDetailState.coins?.symbol})",
                    style = MaterialTheme.typography.h1
                )
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun FirstRow(
    rank: String = "1",
    name: String = "Bitcoin",
    symbol: String = "BTC",
    active: String = "active"
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "$rank. $name ($symbol)",
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = active,
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.align(CenterVertically)


        )
    }
}