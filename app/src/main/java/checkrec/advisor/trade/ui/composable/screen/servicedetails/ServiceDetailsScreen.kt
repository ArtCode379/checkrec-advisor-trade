package checkrec.advisor.trade.ui.composable.screen.servicedetails

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import checkrec.advisor.trade.ui.theme.ChipBg
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.SurfaceWhite
import checkrec.advisor.trade.ui.theme.TealAccent
import checkrec.advisor.trade.viewmodel.ServiceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    onBack: () -> Unit,
    onBookNow: (Int) -> Unit,
    viewModel: ServiceViewModel = koinViewModel()
) {
    val services by viewModel.servicesState.collectAsState()
    val service = services.data?.find { it.id == serviceId } ?: return
    var selectedSlot by remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        TopAppBar(
            title = { Text(service.name, color = SurfaceWhite, maxLines = 1, fontSize = 16.sp) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = SurfaceWhite)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        Column(modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())) {
            AsyncImage(
                model = service.imageUrl,
                contentDescription = service.name,
                modifier = Modifier.fillMaxWidth().height(280.dp).clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)),
                contentScale = ContentScale.Crop
            )

            Column(modifier = Modifier.padding(20.dp)) {
                Text(service.name, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = NavyBlue)
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Box(modifier = Modifier.background(ChipBg, RoundedCornerShape(8.dp)).padding(horizontal = 10.dp, vertical = 4.dp)) {
                        Text(service.category, fontSize = 12.sp, color = NavyBlue, fontWeight = FontWeight.Medium)
                    }
                    Icon(Icons.Default.Schedule, contentDescription = null, tint = MutedGray, modifier = Modifier.size(16.dp))
                    Text("${service.durationMinutes} min", color = MutedGray, fontSize = 13.sp)
                    Text("·", color = MutedGray, fontSize = 13.sp)
                    Text("£${service.price.toInt()}", fontWeight = FontWeight.Bold, color = TealAccent, fontSize = 16.sp)
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(service.description, fontSize = 14.sp, color = MutedGray, lineHeight = 22.sp)

                if (service.features.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("What's Included", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = NavyBlue)
                    Spacer(modifier = Modifier.height(8.dp))
                    service.features.forEach { feature ->
                        Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null, tint = TealAccent, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(feature, fontSize = 14.sp, color = NavyBlue)
                        }
                    }
                }

                if (service.availableSlots.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text("Available Slots", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = NavyBlue)
                    Spacer(modifier = Modifier.height(8.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(0.dp)) {
                        items(service.availableSlots) { slot ->
                            FilterChip(
                                selected = selectedSlot == slot,
                                onClick = { selectedSlot = slot },
                                label = { Text(slot) },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = NavyBlue,
                                    selectedLabelColor = SurfaceWhite,
                                    containerColor = ChipBg,
                                    labelColor = NavyBlue
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        Surface(shadowElevation = 8.dp) {
            Button(
                onClick = { onBookNow(service.id) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Book Consultation", fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}
