package com.example.mycleanarchiapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycleanarchiapp.data.remote.dto.TeamMember
import com.example.mycleanarchiapp.domain.model.CoinDetail
import com.example.mycleanarchiapp.presentation.viewmodels.CoinDetailViewModel
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel
) {
    val coinDetailState = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {

        MainPreview(coinDetailState.coin)

        if (coinDetailState.error.isNotBlank()) {
            Text(
                text = coinDetailState.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (coinDetailState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainPreview(
    coin: CoinDetail = CoinDetail(
        "", "", "", "",
        0, false, emptyList(), emptyList()
    )
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            FirstRow(coin)
            Spacer(modifier = Modifier.height(30.dp))
        }

        item {
            SecondRow(coin)
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            ThirdRow()
            Spacer(modifier = Modifier.height(30.dp))
        }
        item {
            FourthRow(coin)
            Spacer(modifier = Modifier.height(30.dp))
            Divider()
        }
        item {
            FifthRow(coin)

        }

    }
}


@Composable
fun FirstRow(
    coin: CoinDetail
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${coin.rank}. ${coin.name} (${coin.symbol})",
            style = MaterialTheme.typography.h5,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(8f)
        )
        Text(
            text = coin.isActive.toString(),
            textAlign = TextAlign.End,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .align(CenterVertically)
                .weight(2f),
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun SecondRow(
    coin: CoinDetail
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = coin.description,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
fun ThirdRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Tags",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun FourthRow(
    coin: CoinDetail
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
        crossAxisSpacing = 10.dp,
        mainAxisSpacing = 10.dp
    ) {
        coin.tags.forEach {
            CoinTag(tag = it)
        }
    }
}

@Composable
fun FifthRow(
    coin: CoinDetail
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        coin.teamMemberList.forEach { teamMember ->
            TeamListItem(
                teamMember = teamMember,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

//@Composable
//fun SampleRow() {
//    var name by remember {
//        mutableStateOf("")
//    }
//    Row(modifier = Modifier.fillMaxWidth()) {
//        OutlinedTextField(
//            value = name,
//            onValueChange = {
//                name = it
//            },
//            label = { Text("Name") })
//    }
//}


