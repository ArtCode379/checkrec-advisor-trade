package checkrec.advisor.trade.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = NavyBlue,
    onPrimary = SurfaceWhite,
    primaryContainer = ChipBg,
    onPrimaryContainer = NavyBlue,
    secondary = TealAccent,
    onSecondary = SurfaceWhite,
    secondaryContainer = ChipBg,
    onSecondaryContainer = NavyBlue,
    tertiary = LightTeal,
    background = OffWhite,
    onBackground = DarkSlate,
    surface = SurfaceWhite,
    onSurface = DarkSlate,
    surfaceVariant = ChipBg,
    onSurfaceVariant = MutedGray,
    outline = BorderGray,
    error = Color(0xFFb71c1c),
    onError = SurfaceWhite,
)

@Composable
fun ServiceSkeletonTheme(
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = NavyBlue.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
