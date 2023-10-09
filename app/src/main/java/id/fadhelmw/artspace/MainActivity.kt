package id.fadhelmw.artspace
// Import yang diperlukan untuk activity dan komponen Compose
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import androidx.compose.material3.contentColorFor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MyApp() {
    val images = listOf(
        // Daftar gambar pancake yang akan ditampilkan dalam aplikasi
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3
    )

    // Variabel ini melacak indeks gambar saat ini yang sedang ditampilkan
    var currentIndex by remember { mutableStateOf(0) }
    val maxIndex = images.size - 1

    // Pengelolaan fokus dan keyboard
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    // Tampilan menu
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tambahkan logo di sini
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Image(
            painter = painterResource(id = R.drawable.logo), // Ganti dengan ID gambar logo Anda
            contentDescription = "Logo",
            modifier = Modifier
                .size(125.dp) // Sesuaikan ukuran logo
        )
        Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Pancakes Menu", // Judul atau nama aplikasi Anda
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Gambar pancake yang akan diganti
        Image(
            painter = painterResource(id = images[currentIndex]),
            contentDescription = "Pancake ${currentIndex +1}",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .border(
                    width = 1.dp, //lebar border
                    color = Color.Gray, //warna border
                    shape = RoundedCornerShape(8.dp) // bentuk
                )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Deskripsi gambar yang bisa diedit
        BasicTextField(
            value = "Pancakes ${currentIndex + 1}", // Mengganti deskripsi sesuai gambar
            onValueChange = { /* TODO: Handle text change */ },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 18.sp,
                color = Color.Black // Warna teks yang disesuaikan
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk mengganti gambar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    currentIndex = when {
                        currentIndex > 0 -> currentIndex - 1
                        else -> maxIndex
                    }
                },
                enabled = currentIndex > 0,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    currentIndex = when {
                        currentIndex < maxIndex -> currentIndex + 1
                        else -> 0
                    }
                },
                enabled = currentIndex < maxIndex,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(text = "Next")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApp()
}