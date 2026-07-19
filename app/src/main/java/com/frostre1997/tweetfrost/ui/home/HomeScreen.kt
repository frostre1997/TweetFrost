package com.frostre1997.tweetfrost.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    apiService: ApiService,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(apiService))
) {
    val uiState by viewModel.uiState.collectAsState()

    // Load timeline on first composition
    LaunchedEffect(Unit) {
        viewModel.loadTimeline()
    }

    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (uiState.errorMessage != null) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Error: ${uiState.errorMessage}")
            Button(onClick = { viewModel.loadTimeline() }) {
                Text("Retry")
            }
        }
    } else {
        LazyColumn {
            items(uiState.tweets) { tweet ->
                TweetItem(tweet = tweet)
                Divider()
            }
        }
    }
}

@Composable
fun TweetItem(tweet: Tweet) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = tweet.user?.name ?: "Unknown",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "@${tweet.user?.screenName ?: "unknown"}",
            style = MaterialTheme.typography.bodySmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = tweet.text)
        Row {
            Text("❤️ ${tweet.likeCount}  🔄 ${tweet.retweetCount}")
        }
    }
}
