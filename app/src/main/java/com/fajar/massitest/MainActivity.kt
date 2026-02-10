import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val dummyPosts = listOf(
                        Post(1, "fajar_farizi", "https://goo.gl/v5j9Vf", "https://blogassets.airtel.in/wp-content/uploads/2024/10/alexander-shatov-71Qk8ODIBko-unsplash.jpg", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam nec vulputate nisl.", 120, 15, 90),
                        Post(2, "android_dev", "https://goo.gl/v5j9Vf", "https://blogassets.airtel.in/wp-content/uploads/2024/10/alexander-shatov-71Qk8ODIBko-unsplash.jpg", "UI Instagram Clone", 99, 19, 20)
                    )
                    InstagramHomeScreen(dummyPosts)
                }
            }
        }
    }
}


data class Post(
    val id: Int,
    val username: String,
    val userImage: String,
    val postImage: String,
    val caption: String,
    val likes: Int,
    val comments: Int,
    val repost: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstagramHomeScreen(posts: List<Post>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Instagram", fontSize = 24.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { }) { Icon(Icons.Outlined.FavoriteBorder, "Likes") }
                    IconButton(onClick = { }) { Icon(Icons.Outlined.Send, "Direct Message") }
                }
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item { StoriesSection() }
            items(posts) { post -> InstagramPostItem(post) }
        }
    }
}

@Composable
fun StoriesSection() {
    LazyRow(modifier = Modifier.padding(8.dp)) {
        items(10) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter("https://picsum.photos/100/100"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(70.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Magenta, CircleShape)
                        .padding(3.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Text("User $it", fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun InstagramPostItem(post: Post) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberAsyncImagePainter(post.userImage),
                contentDescription = null,
                modifier = Modifier.size(40.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(
                text = post.username,
                modifier = Modifier.padding(start = 8.dp).weight(1f),
                fontWeight = FontWeight.Bold
            )
            Icon(Icons.Default.MoreVert, contentDescription = null)
        }

        Image(
            painter = rememberAsyncImagePainter(post.postImage),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(400.dp),
            contentScale = ContentScale.Crop
        )

        Row(modifier = Modifier.padding(8.dp)) {
            Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.likes}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Outlined.MailOutline, contentDescription = null, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.comments}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Outlined.Refresh, contentDescription = null, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.repost}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Outlined.Send, contentDescription = null, modifier = Modifier.size(28.dp))
        }

        Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
            Row {
                Text(post.username, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(4.dp))
                Text(post.caption)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InstagramPreview() {
    MaterialTheme {
        val dummyPosts = listOf(
            Post(1, "fajar_farizi", "", "https://blogassets.airtel.in/wp-content/uploads/2024/10/alexander-shatov-71Qk8ODIBko-unsplash.jpg", "Lorem ipsum dolor sit amet.", 120, 15, 90),
            Post(2, "user2", "", "https://blogassets.airtel.in/wp-content/uploads/2024/10/alexander-shatov-71Qk8ODIBko-unsplash.jpg", "testtttttttt", 99, 19, 20)
        )
        InstagramHomeScreen(posts = dummyPosts)
    }
}