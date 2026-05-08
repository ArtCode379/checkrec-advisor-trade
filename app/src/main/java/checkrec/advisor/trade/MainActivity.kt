package checkrec.advisor.trade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import checkrec.advisor.trade.ui.composable.approot.AppRoot
import checkrec.advisor.trade.ui.theme.ServiceSkeletonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ServiceSkeletonTheme {
                AppRoot()
            }
        }
    }
}