package com.example.mycleanarchiapp.presentation.coin_detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycleanarchiapp.data.remote.dto.TeamMember

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.body2
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SamplePreview() {
    TeamListItem(
        teamMember = TeamMember("1", "Robetto", "General Manager"),
        modifier = Modifier.fillMaxWidth()
    )
}