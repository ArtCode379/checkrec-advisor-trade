package checkrec.advisor.trade.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import checkrec.advisor.trade.ui.theme.MutedGray
import checkrec.advisor.trade.ui.theme.NavyBlue
import checkrec.advisor.trade.ui.theme.OffWhite
import checkrec.advisor.trade.ui.theme.SurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize().background(OffWhite)) {
        TopAppBar(
            title = { Text("Settings", fontWeight = FontWeight.Bold, color = SurfaceWhite) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyBlue)
        )

        Column(modifier = Modifier.padding(16.dp)) {
            Text("About", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MutedGray, modifier = Modifier.padding(bottom = 8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Company", fontSize = 13.sp, color = MutedGray)
                    Text("CHECKREC LTD", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    Text("App Name", fontSize = 13.sp, color = MutedGray)
                    Text("CheckRec Advisor", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
                    HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp))
                    Text("Version", fontSize = 13.sp, color = MutedGray)
                    Text("1.0.0", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = NavyBlue)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Legal", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MutedGray, modifier = Modifier.padding(bottom = 8.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Privacy Policy", fontSize = 15.sp, color = NavyBlue, modifier = Modifier.padding(vertical = 4.dp))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    Text("Terms of Service", fontSize = 15.sp, color = NavyBlue, modifier = Modifier.padding(vertical = 4.dp))
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("Support", fontWeight = FontWeight.SemiBold, fontSize = 13.sp, color = MutedGray, modifier = Modifier.padding(bottom = 8.dp))
            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://checkrecc.info"))
                    context.startActivity(intent)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Customer Support", fontSize = 15.sp)
            }
        }
    }
}
