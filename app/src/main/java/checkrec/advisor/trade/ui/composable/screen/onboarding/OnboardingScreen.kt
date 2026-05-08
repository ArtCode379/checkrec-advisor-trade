package checkrec.advisor.trade.ui.composable.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import checkrec.advisor.trade.ui.theme.BorderGray
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.TealAccent
import checkrec.advisor.trade.ui.theme.SurfaceWhite

private data class OnboardingSlide(val icon: ImageVector, val title: String, val description: String, val imageUrl: String)

private val slides = listOf(
    OnboardingSlide(
        icon = Icons.Default.TrendingUp,
        title = "Strategic Advisory",
        description = "We help organisations build clear growth strategies, define objectives, and create executable roadmaps aligned with market realities.",
        imageUrl = "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?w=600"
    ),
    OnboardingSlide(
        icon = Icons.Default.Lightbulb,
        title = "Operational Excellence",
        description = "Our consultants identify operational inefficiencies and implement lean methodologies to reduce costs and boost productivity.",
        imageUrl = "https://images.unsplash.com/photo-1498050108023-c5249f4df085?w=600"
    ),
    OnboardingSlide(
        icon = Icons.Default.Analytics,
        title = "Insight-Led Transformation",
        description = "We leverage data analytics and industry benchmarks to deliver measurable business transformation outcomes for your organisation.",
        imageUrl = "https://images.unsplash.com/photo-1551288049-bebda4e38f71?w=600"
    )
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState(pageCount = { slides.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            val slide = slides[page]
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(80.dp).background(NavyBlue.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(slide.icon, contentDescription = null, tint = NavyBlue, modifier = Modifier.size(40.dp))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(slide.title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = NavyBlue, textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(12.dp))
                Text(slide.description, fontSize = 14.sp, color = MutedGray, textAlign = TextAlign.Center, lineHeight = 22.sp)
                Spacer(modifier = Modifier.height(24.dp))
                AsyncImage(
                    model = slide.imageUrl,
                    contentDescription = slide.title,
                    modifier = Modifier.size(140.dp).clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), horizontalArrangement = Arrangement.Center) {
            repeat(slides.size) { idx ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (pagerState.currentPage == idx) 10.dp else 8.dp)
                        .background(if (pagerState.currentPage == idx) NavyBlue else BorderGray, CircleShape)
                )
            }
        }

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 16.dp)) {
            if (pagerState.currentPage == slides.size - 1) {
                Button(onClick = onFinish, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)) {
                    Text("Get Started", fontSize = 16.sp)
                }
            } else {
                Button(
                    onClick = { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = TealAccent)
                ) {
                    Text("Next", fontSize = 16.sp)
                }
            }
        }
    }
}
