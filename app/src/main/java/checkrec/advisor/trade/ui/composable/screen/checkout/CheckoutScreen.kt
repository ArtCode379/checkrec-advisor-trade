package checkrec.advisor.trade.ui.composable.screen.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.SurfaceWhite
import checkrec.advisor.trade.viewmodel.BookingViewModel
import checkrec.advisor.trade.viewmodel.ServiceViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    serviceId: Int,
    onBack: () -> Unit,
    onSuccess: () -> Unit,
    bookingViewModel: BookingViewModel = koinViewModel(),
    serviceViewModel: ServiceViewModel = koinViewModel()
) {
    val services by serviceViewModel.servicesState.collectAsState()
    val service = services.data?.find { it.id == serviceId }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val isComplete = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()

    if (showDialog) {
        CheckoutDialog(onDismiss = { showDialog = false; onSuccess() })
    }

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        TopAppBar(
            title = { Text("Book Consultation", color = SurfaceWhite) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = SurfaceWhite)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
            service?.let {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Service Summary", fontWeight = FontWeight.SemiBold, fontSize = 14.sp, color = MutedGray)
                        Text(it.name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = NavyBlue)
                        Text("£${it.price.toInt()} · ${it.durationMinutes} min", color = MutedGray, fontSize = 13.sp)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }

            Text("Your Details", fontWeight = FontWeight.Bold, fontSize = 16.sp, color = NavyBlue)
            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name *") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name *") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email *") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp))
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Notes / Preferred Date") }, modifier = Modifier.fillMaxWidth(), minLines = 3, shape = RoundedCornerShape(8.dp))

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    bookingViewModel.addBooking(
                        serviceId = serviceId,
                        serviceName = service?.name ?: "",
                        customerFirstName = firstName,
                        customerLastName = lastName,
                        customerEmail = email
                    )
                    showDialog = true
                },
                enabled = isComplete,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Confirm Booking", fontSize = 16.sp, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }
}
