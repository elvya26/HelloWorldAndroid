package com.example.helloworld.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

/**
 * MINGGU 1 — Tugas Form Sederhana
 */
@Composable
fun GreetingScreen() {

    // ==========================================
    // STATE MANAGEMENT
    // ==========================================
    var name by remember { mutableStateOf("") }
    var nim by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    // State for submitted data to display
    var submittedName by remember { mutableStateOf("") }
    var submittedNim by remember { mutableStateOf("") }

    // Validation logic
    val isNameValid = name.isNotBlank()
    val isNimValid = nim.isNotBlank() && nim.all { it.isDigit() }
    val isFormValid = isNameValid && isNimValid

    // ==========================================
    // UI LAYOUT
    // ==========================================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()), // Tambahkan scroll agar tidak terpotong saat keyboard muncul
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Spacer(modifier = Modifier.height(32.dp))

        // ── Icon Header ──
        Icon(
            imageVector = Icons.Default.AssignmentInd,
            contentDescription = "Form Icon",
            modifier = Modifier.size(80.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Judul ──
        Text(
            text = "Form Pendaftaran",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Tugas Minggu 1 — Pemrograman Mobile",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
        )

        // ── Card Input ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Input Nama Lengkap
                Text(
                    text = "Nama Lengkap",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { 
                        name = it 
                        isSubmitted = false 
                    },
                    placeholder = { Text("Contoh: Elvya") },
                    singleLine = true,
                    isError = !isNameValid && name.isNotEmpty(),
                    supportingText = {
                        if (!isNameValid && name.isNotEmpty()) {
                            Text("Nama tidak boleh kosong")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Input NIM/NIP
                Text(
                    text = "NIM/NIP",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = nim,
                    onValueChange = { newValue ->
                        // Hanya update jika isSubmitted diganti atau input validasi dasar
                        nim = newValue
                        isSubmitted = false
                    },
                    placeholder = { Text("Contoh: 21000123") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    isError = !isNimValid && nim.isNotEmpty(),
                    supportingText = {
                        if (!isNimValid && nim.isNotEmpty()) {
                            Text("NIM harus berupa angka")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Submit Button
                Button(
                    onClick = { 
                        submittedName = name
                        submittedNim = nim
                        isSubmitted = true 
                    },
                    enabled = isFormValid,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Result Card ──
        AnimatedVisibility(
            visible = isSubmitted,
            enter = fadeIn() + slideInVertically()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Data Berhasil Disimpan!",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 12.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Nama:", fontWeight = FontWeight.SemiBold)
                        Text(text = submittedName)
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "NIM/NIP:", fontWeight = FontWeight.SemiBold)
                        Text(text = submittedNim)
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "Selamat mengerjakan tugas berikutnya! 🚀",
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Form Preview")
@Composable
fun GreetingScreenPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}
