package checkrec.advisor.trade.ui.composable.screen.bookings

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.SuccessGreen
import checkrec.advisor.trade.ui.theme.SurfaceWhite
import checkrec.advisor.trade.ui.theme.TealAccent
import checkrec.advisor.trade.viewmodel.BookingViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingsScreen(
    onBrowseServices: () -> Unit,
    viewModel: BookingViewModel = koinViewModel()
) {
    val bookingsState by viewModel.bookingsState.collectAsState()
    val bookings = bookingsState.data ?: emptyList()

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        TopAppBar(
            title = { Text("My Bookings", fontWeight = FontWeight.Bold, color = SurfaceWhite) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        if (bookings.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.CalendarToday, contentDescription = null, tint = MutedGray, modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("No bookings yet", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = NavyBlue)
                    Text("Book a consultation to get started", color = MutedGray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onBrowseServices, colors = ButtonDefaults.buttonColors(containerColor = NavyBlue)) {
                        Text("Browse Services")
                    }
                }
            }
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(bookings) { booking ->
                    val dateStr = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(booking.timestamp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(booking.serviceName, fontWeight = FontWeight.SemiBold, fontSize = 15.sp, color = NavyBlue, modifier = Modifier.weight(1f))
                                Box(
                                    modifier = Modifier
                                        .background(SuccessGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text("Confirmed", fontSize = 11.sp, color = SuccessGreen, fontWeight = FontWeight.Medium)
                                }
                            }
                            Text("${booking.customerFirstName} ${booking.customerLastName}", color = MutedGray, fontSize = 13.sp)
                            Text(dateStr, color = MutedGray, fontSize = 12.sp)
                            Text("#${booking.bookingNumber}", color = TealAccent, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}
