package com.serah.sokohub.ui.screens.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.navigation.ROUT_HOME
import com.navigation.ROUT_INTENT
import com.navigation.ROUT_LOGIN
import com.serah.sokohub.ui.theme.Darkblue


// This is our contact. Each contact has these details.
data class Contact(
    val id: Int,
    val name: String,
    val job: String,
    val phone: String,
    val email: String,
    val color: Color
)


// These are the contacts that will show on screen
val contactList = listOf(
    Contact(1, "Grace Wanjiru",   "Software Engineer", "+254 712 345 678", "grace@gmail.com",   Color(0xFF1565C0)),
    Contact(2, "Brian Odhiambo", "Product Designer",  "+254 733 987 654", "brian@gmail.com",   Color(0xFF6A1B9A)),
    Contact(3, "Amina Hassan",   "Business Analyst",  "+254 722 111 222", "amina@gmail.com",   Color(0xFF2E7D32)),
)


// This is ONE card. It shows one contact's details.
@Composable
fun ContactCard(
    contact: Contact,
    onEditClick: (Contact) -> Unit,
    onDeleteClick: (Contact) -> Unit
) {
    // context lets us open the phone app or email app
    val context = LocalContext.current

    // The card box
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = contact.color),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // --- Top row: Name + Edit/Delete buttons ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Name and job title
                Column {
                    Text(
                        text = contact.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = contact.job,
                        fontSize = 13.sp,
                        color = Color.White
                    )
                }

                // Edit and Delete buttons on the right
                Row {
                    IconButton(onClick = { onEditClick(contact) }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit", tint = Color.White)
                    }
                    IconButton(onClick = { onDeleteClick(contact) }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // --- Phone row: tap to call ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    // This opens the phone dialer app
                    val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.phone}"))
                    context.startActivity(callIntent)
                }) {
                    Icon(Icons.Default.Phone, contentDescription = "Call", tint = Color.White)
                }
                Text(text = contact.phone, color = Color.White, fontSize = 14.sp)
            }

            // --- Email row: tap to email ---
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    // This opens the email app
                    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${contact.email}"))
                    context.startActivity(emailIntent)
                }) {
                    Icon(Icons.Default.Email, contentDescription = "Email", tint = Color.White)
                }
                Text(text = contact.email, color = Color.White, fontSize = 14.sp)
            }

        }
    }
}


// This is the Edit popup that appears when you tap Edit
@Composable
fun EditDialog(
    contact: Contact,
    onDismiss: () -> Unit,
    onSave: (Contact) -> Unit
) {
    // These hold the typed values
    var name  by remember { mutableStateOf(contact.name) }
    var job   by remember { mutableStateOf(contact.job) }
    var phone by remember { mutableStateOf(contact.phone) }
    var email by remember { mutableStateOf(contact.email) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Edit Contact") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(value = name,  onValueChange = { name  = it }, label = { Text("Name")  })
                OutlinedTextField(value = job,   onValueChange = { job   = it }, label = { Text("Job")   })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
            }
        },
        confirmButton = {
            Button(onClick = {
                // Save the updated contact
                onSave(contact.copy(name = name, job = job, phone = phone, email = email))
            }) { Text("Save") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


// This is the Delete popup that appears when you tap Delete
@Composable
fun DeleteDialog(
    contact: Contact,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Contact?") },
        text = { Text("Are you sure you want to remove ${contact.name}?") },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) { Text("Delete") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}


// This is the full screen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {

    // cards holds the list of contacts. When it changes, the screen updates.
    var cards by remember { mutableStateOf(contactList) }

    // These track which contact is being edited or deleted
    var contactToEdit   by remember { mutableStateOf<Contact?>(null) }
    var contactToDelete by remember { mutableStateOf<Contact?>(null) }

    // This tracks which bottom nav item is selected
    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(

        // Top bar
        topBar = {
            TopAppBar(
                title = { Text("Business Cards") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Menu, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Darkblue,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        },

        // Bottom nav bar
        bottomBar = {
            NavigationBar(containerColor = Darkblue) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = Color.Black) },
                    label = { Text("Home", color = Color.Black) },
                    selected = selectedIndex == 0,
                    onClick = { selectedIndex = 0; navController.navigate(ROUT_HOME) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Favorite, contentDescription = "Intent", tint = Color.Black) },
                    label = { Text("Intent", color = Color.Black) },
                    selected = selectedIndex == 1,
                    onClick = { selectedIndex = 1; navController.navigate(ROUT_INTENT) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Login", tint = Color.Black) },
                    label = { Text("Login", color = Color.Black) },
                    selected = selectedIndex == 2,
                    onClick = { selectedIndex = 2; navController.navigate(ROUT_LOGIN) }
                )
            }
        },

        // The + button at the bottom right
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Add a new blank contact
                    val newId = (cards.maxOfOrNull { it.id } ?: 0) + 1
                    cards = cards + Contact(
                        id = newId,
                        name = "New Contact",
                        job = "Job Title",
                        phone = "+254 700 000 000",
                        email = "email@gmail.com",
                        color = Color(0xFF1565C0)
                    )
                },
                containerColor = Darkblue
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        },

        // Main content: the list of cards
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFF4F6FA)),
                contentPadding = PaddingValues(top = 12.dp, bottom = 80.dp)
            ) {
                items(cards, key = { it.id }) { contact ->
                    ContactCard(
                        contact = contact,
                        onEditClick   = { contactToEdit   = it },
                        onDeleteClick = { contactToDelete = it }
                    )
                }
            }
        }
    )

    // Show Edit popup if a contact was tapped for editing
    if (contactToEdit != null) {
        EditDialog(
            contact   = contactToEdit!!,
            onDismiss = { contactToEdit = null },
            onSave    = { updated ->
                cards = cards.map { if (it.id == updated.id) updated else it }
                contactToEdit = null
            }
        )
    }

    // Show Delete popup if a contact was tapped for deleting
    if (contactToDelete != null) {
        DeleteDialog(
            contact   = contactToDelete!!,
            onDismiss = { contactToDelete = null },
            onConfirm = {
                cards = cards.filter { it.id != contactToDelete!!.id }
                contactToDelete = null
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen(rememberNavController())
}