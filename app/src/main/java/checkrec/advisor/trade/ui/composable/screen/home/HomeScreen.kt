package checkrec.advisor.trade.ui.composable.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import checkrec.advisor.trade.data.model.ServiceModel
import checkrec.advisor.trade.ui.theme.BorderGray
import checkrec.advisor.trade.ui.theme.ChipBg
import checkrec.advisor.trade.ui.theme.ChipContent
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.SurfaceWhite
import checkrec.advisor.trade.ui.theme.TealAccent
import checkrec.advisor.trade.viewmodel.ServiceViewModel

private data class Category(val label: String, val icon: ImageVector)

private val categories = listOf(
    Category("Strategy", Icons.Default.TrendingUp),
    Category("Operations", Icons.Default.Build),
    Category("HR & Org", Icons.Default.Groups),
    Category("Analytics", Icons.Default.Analytics),
    Category("Leadership", Icons.Default.Groups)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onServiceClick: (Int) -> Unit,
    viewModel: ServiceViewModel = koinViewModel()
) {
    val services by viewModel.servicesState.collectAsState()
    val serviceList = services.data ?: emptyList()
    val featured = serviceList.take(4)
    val pagerState = rememberPagerState(pageCount = { featured.size.coerceAtLeast(1) })

    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            val next = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(next)
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        TopAppBar(
            title = { Text("CheckRec Advisor", fontWeight = FontWeight.Bold, color = SurfaceWhite) },
            actions = {
                IconButton(onClick = { android.util.Log.d("CheckRec", "notifications tapped") }) {
                    Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = SurfaceWhite)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 16.dp)) {
            // Next available banner
            item {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = TealAccent)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Brush.horizontalGradient(listOf(NavyBlue, TealAccent)))
                            .padding(20.dp)
                    ) {
                        Column {
                            Text("Next Available Slot", color = SurfaceWhite.copy(alpha = 0.8f), fontSize = 12.sp)
                            Text("Today, 11:00 — Strategy Consultation", color = SurfaceWhite, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Book your complimentary discovery call →", color = SurfaceWhite.copy(alpha = 0.8f), fontSize = 13.sp)
                        }
                    }
                }
            }

            // Hero carousel
            item {
                if (featured.isNotEmpty()) {
                    HorizontalPager(state = pagerState) { page ->
                        HeroCard(service = featured[page], onClick = { onServiceClick(featured[page].id) })
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        repeat(featured.size) { idx ->
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .size(if (pagerState.currentPage == idx) 10.dp else 7.dp)
                                    .background(if (pagerState.currentPage == idx) NavyBlue else BorderGray, CircleShape)
                            )
                        }
                    }
                }
            }

            // Categories
            item {
                Text("Service Areas", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = NavyBlue, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp))
                LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(categories) { cat ->
                        Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = ChipBg), elevation = CardDefaults.cardElevation(0.dp)) {
                            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(cat.icon, contentDescription = cat.label, tint = NavyBlue, modifier = Modifier.size(28.dp))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(cat.label, color = ChipContent, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
            item { Text("All Services", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = NavyBlue, modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) }

            items(serviceList) { service ->
                ServiceCard(service = service, onClick = { onServiceClick(service.id) })
            }
        }
    }
}

@Composable
private fun HeroCard(service: ServiceModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(200.dp).padding(horizontal = 16.dp).clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(model = service.imageUrl, contentDescription = service.name, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Box(modifier = Modifier.fillMaxSize().background(Brush.verticalGradient(listOf(androidx.compose.ui.graphics.Color.Transparent, NavyBlue.copy(alpha = 0.85f)))))
            Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                Text(service.name, color = SurfaceWhite, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("From £${service.price.toInt()} · ${service.durationMinutes} min", color = SurfaceWhite.copy(alpha = 0.8f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun ServiceCard(service: ServiceModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp).clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier.size(80.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(service.name, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = NavyBlue)
                Text(service.description, fontSize = 12.sp, color = MutedGray, maxLines = 2, lineHeight = 18.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.background(NavyBlue, RoundedCornerShape(6.dp)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                        Text("Book Now", fontSize = 11.sp, color = SurfaceWhite, fontWeight = FontWeight.Medium)
                    }
                    Text("£${service.price.toInt()}", fontWeight = FontWeight.Bold, color = TealAccent, fontSize = 14.sp)
                }
            }
        }
    }
}
