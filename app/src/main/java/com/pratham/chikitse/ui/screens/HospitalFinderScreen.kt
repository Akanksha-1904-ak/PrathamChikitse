package com.pratham.chikitse.ui.screens

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

private val PrathamTeal = Color(0xFF00695C)
private val PrathamTealDark = Color(0xFF003D33)

data class NearbyHospital(
    val name: String,
    val address: String,
    val distance: String,
    val isOpen: Boolean?,
    val rating: Float?,
    val lat: Double,
    val lng: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalFinderScreen(isKannada: Boolean, onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var hospitals by remember { mutableStateOf<List<NearbyHospital>>(emptyList()) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var locationGranted by remember { mutableStateOf(false) }
    var debugInfo by remember { mutableStateOf("") }

    locationGranted = ContextCompat.checkSelfPermission(
        context, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    fun loadHospitals() {
        isLoading = true
        errorMessage = null
        hospitals = emptyList()
        scope.launch {
            fetchNearbyHospitals(context) { result, err, debug ->
                hospitals = result
                errorMessage = err
                debugInfo = debug
                isLoading = false
            }
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        locationGranted = granted
        if (granted) loadHospitals()
    }

    LaunchedEffect(locationGranted) {
        if (locationGranted) loadHospitals()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        if (isKannada) "ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆ" else "Nearby Hospitals",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back", tint = Color.White)
                    }
                },
                actions = {
                    if (locationGranted) {
                        IconButton(onClick = { loadHospitals() }) {
                            Icon(Icons.Default.Refresh, "Refresh", tint = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = PrathamTealDark)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF5F5F5))
        ) {
            when {
                !locationGranted -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("📍", style = MaterialTheme.typography.displayLarge)
                        Spacer(Modifier.height(16.dp))
                        Text(
                            if (isKannada) "ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆ ಹುಡುಕಲು ಸ್ಥಳ ಅನುಮತಿ ಅಗತ್ಯ"
                            else "Location permission needed to find nearby hospitals",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                        Spacer(Modifier.height(24.dp))
                        Button(
                            onClick = {
                                permissionLauncher.launch(
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                )
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrathamTeal),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.LocationOn, null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                if (isKannada) "ಅನುಮತಿ ನೀಡಿ" else "Grant Permission",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                isLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(color = PrathamTeal, strokeWidth = 3.dp)
                            Spacer(Modifier.height(16.dp))
                            Text(
                                if (isKannada) "ಹತ್ತಿರದ ಆಸ್ಪತ್ರೆ ಹುಡುಕುತ್ತಿದ್ದೇವೆ..."
                                else "Finding nearby hospitals...",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                if (isKannada) "GPS ಸಿಗ್ನಲ್ ಕಾಯುತ್ತಿದ್ದೇವೆ"
                                else "Waiting for GPS signal...",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }

                errorMessage != null -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("⚠️", style = MaterialTheme.typography.displayLarge)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            errorMessage ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = Color.Red
                        )
                        if (debugInfo.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                debugInfo,
                                style = MaterialTheme.typography.bodySmall,
                                textAlign = TextAlign.Center,
                                color = Color.Gray
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { loadHospitals() },
                            colors = ButtonDefaults.buttonColors(containerColor = PrathamTeal),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Refresh, null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                if (isKannada) "ಮತ್ತೆ ಪ್ರಯತ್ನಿಸಿ" else "Try Again",
                                color = Color.White
                            )
                        }
                    }
                }

                hospitals.isEmpty() -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text("🏥", style = MaterialTheme.typography.displayLarge)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            if (isKannada) "ಹತ್ತಿರದಲ್ಲಿ ಆಸ್ಪತ್ರೆ ಕಾಣಲಿಲ್ಲ"
                            else "No hospitals found nearby",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        if (debugInfo.isNotEmpty()) {
                            Spacer(Modifier.height(8.dp))
                            Text(
                                debugInfo,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray,
                                textAlign = TextAlign.Center
                            )
                        }
                        Spacer(Modifier.height(16.dp))
                        Button(
                            onClick = { loadHospitals() },
                            colors = ButtonDefaults.buttonColors(containerColor = PrathamTeal),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(Icons.Default.Refresh, null, tint = Color.White)
                            Spacer(Modifier.width(8.dp))
                            Text(
                                if (isKannada) "ಮತ್ತೆ ಪ್ರಯತ್ನಿಸಿ" else "Try Again",
                                color = Color.White
                            )
                        }
                    }
                }

                else -> {
                    val isOffline = debugInfo.contains("Offline") ||
                            debugInfo.contains("📵") ||
                            debugInfo.contains("cached")
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isOffline) Color(0xFFE3F2FD) else Color(0xFFE8F5E9)
                            )
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            if (isOffline) Icons.Default.WifiOff
                            else Icons.Default.LocationOn,
                            null,
                            tint = if (isOffline) Color(0xFF1565C0) else PrathamTeal
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            if (isOffline)
                                if (isKannada)
                                    "${hospitals.size} ಆಸ್ಪತ್ರೆಗಳು • ಆಫ್‌ಲೈನ್ ಡೇಟಾ • ತೆರೆಯಲು ಟ್ಯಾಪ್ ಮಾಡಿ"
                                else
                                    "${hospitals.size} hospitals • Offline data • Tap to navigate"
                            else
                                if (isKannada)
                                    "${hospitals.size} ಆಸ್ಪತ್ರೆಗಳು ಕಂಡುಬಂದಿವೆ • ತೆರೆಯಲು ಟ್ಯಾಪ್ ಮಾಡಿ"
                                else
                                    "${hospitals.size} hospitals found near you • Tap to navigate",
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isOffline) Color(0xFF1565C0) else PrathamTeal,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(hospitals) { hospital ->
                            LiveHospitalCard(hospital = hospital, isKannada = isKannada)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LiveHospitalCard(hospital: NearbyHospital, isKannada: Boolean) {
    val context = LocalContext.current

    fun openNavigation() {
        val navUri = Uri.parse(
            "google.navigation:q=${hospital.lat},${hospital.lng}&mode=d"
        )
        val navIntent = Intent(Intent.ACTION_VIEW, navUri).apply {
            setPackage("com.google.android.apps.maps")
        }
        if (navIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(navIntent)
        } else {
            val browserUri = Uri.parse(
                "https://www.google.com/maps/dir/?api=1" +
                        "&destination=${hospital.lat},${hospital.lng}" +
                        "&destination_place_name=${Uri.encode(hospital.name)}" +
                        "&travelmode=driving"
            )
            context.startActivity(Intent(Intent.ACTION_VIEW, browserUri))
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { openNavigation() },
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(Color(0xFFE3F2FD), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.LocalHospital, null,
                        tint = PrathamTeal,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        hospital.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2
                    )
                    Spacer(Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.LocationOn, null,
                            tint = Color.Gray,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            hospital.address,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray,
                            maxLines = 2
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = PrathamTeal.copy(alpha = 0.1f)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.DirectionsWalk, null,
                                    tint = PrathamTeal,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    hospital.distance,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = PrathamTeal,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        hospital.isOpen?.let { open ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (open) Color(0xFFE8F5E9) else Color(0xFFFFEBEE)
                            ) {
                                Text(
                                    if (open) "● Open" else "● Closed",
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp, vertical = 4.dp
                                    ),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = if (open) Color(0xFF2E7D32) else Color(0xFFC62828),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        hospital.rating?.let { rating ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFFFF8E1)
                            ) {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp, vertical = 4.dp
                                    ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        Icons.Default.Star, null,
                                        tint = Color(0xFFFFA000),
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(Modifier.width(3.dp))
                                    Text(
                                        "$rating",
                                        style = MaterialTheme.typography.labelMedium,
                                        color = Color(0xFFFFA000),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
                Icon(
                    Icons.Default.NavigateNext, null,
                    tint = PrathamTeal,
                    modifier = Modifier
                        .size(28.dp)
                        .align(Alignment.CenterVertically)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrathamTeal)
                    .clickable { openNavigation() }
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Navigation, null,
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        if (isKannada) "ನ್ಯಾವಿಗೇಟ್ ಮಾಡಿ" else "Navigate",
                        color = Color.White,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// ─── Internet check ────────────────────────────────────────────────────────

private fun isInternetAvailable(context: android.content.Context): Boolean {
    return try {
        val cm = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        val hasInternet = capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
        val hasValidated = capabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_VALIDATED
        )
        android.util.Log.d(
            "HospitalFinder",
            "Internet=$hasInternet Validated=$hasValidated"
        )
        hasInternet && hasValidated
    } catch (e: Exception) {
        android.util.Log.e("HospitalFinder", "Internet check error: ${e.message}")
        false
    }
}

// ─── Save to cache ─────────────────────────────────────────────────────────

private fun saveHospitalsToCache(
    context: android.content.Context,
    hospitals: List<NearbyHospital>
) {
    try {
        val prefs = context.getSharedPreferences(
            "hospital_cache", android.content.Context.MODE_PRIVATE
        )
        val jsonArray = JSONArray()
        hospitals.forEach { h ->
            val obj = JSONObject()
            obj.put("name", h.name)
            obj.put("address", h.address)
            obj.put("distance", h.distance)
            if (h.isOpen != null) obj.put("isOpen", h.isOpen)
            else obj.put("isOpen", JSONObject.NULL)
            if (h.rating != null) obj.put("rating", h.rating)
            else obj.put("rating", JSONObject.NULL)
            obj.put("lat", h.lat)
            obj.put("lng", h.lng)
            jsonArray.put(obj)
        }
        prefs.edit()
            .putString("hospitals", jsonArray.toString())
            .putLong("saved_time", System.currentTimeMillis())
            .apply()
        android.util.Log.d("HospitalFinder", "Cached ${hospitals.size} hospitals")
    } catch (e: Exception) {
        android.util.Log.e("HospitalFinder", "Cache save error: ${e.message}")
    }
}

// ─── Load from cache ───────────────────────────────────────────────────────

private fun loadHospitalsFromCache(
    context: android.content.Context
): List<NearbyHospital> {
    return try {
        val prefs = context.getSharedPreferences(
            "hospital_cache", android.content.Context.MODE_PRIVATE
        )
        val json = prefs.getString("hospitals", null) ?: return emptyList()
        val jsonArray = JSONArray(json)
        val list = mutableListOf<NearbyHospital>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            list.add(
                NearbyHospital(
                    name = obj.getString("name"),
                    address = obj.getString("address"),
                    distance = obj.getString("distance"),
                    isOpen = if (obj.isNull("isOpen")) null
                    else obj.getBoolean("isOpen"),
                    rating = if (obj.isNull("rating")) null
                    else obj.getDouble("rating").toFloat(),
                    lat = obj.getDouble("lat"),
                    lng = obj.getDouble("lng")
                )
            )
        }
        android.util.Log.d("HospitalFinder", "Loaded ${list.size} from cache")
        list
    } catch (e: Exception) {
        android.util.Log.e("HospitalFinder", "Cache load error: ${e.message}")
        emptyList()
    }
}

// ─── Hardcoded fallback hospitals ──────────────────────────────────────────

private fun getHardcodedHospitals(): List<NearbyHospital> {
    return listOf(
        NearbyHospital(
            "Bowring & Lady Curzon Hospital",
            "Shivajinagar, Bengaluru", "Nearby",
            null, null, 12.9791, 77.6013
        ),
        NearbyHospital(
            "Victoria Hospital",
            "Kempe Gowda Rd, Bengaluru", "Nearby",
            null, null, 12.9634, 77.5744
        ),
        NearbyHospital(
            "Kidwai Memorial Institute",
            "Dr. M H Marigowda Rd, Bengaluru", "Nearby",
            null, null, 12.9250, 77.5938
        ),
        NearbyHospital(
            "St. John's Medical College Hospital",
            "Sarjapur Rd, Bengaluru", "Nearby",
            null, null, 12.9256, 77.6196
        ),
        NearbyHospital(
            "Manipal Hospital Whitefield",
            "Whitefield, Bengaluru", "Nearby",
            null, null, 12.9698, 77.7500
        ),
        NearbyHospital(
            "Mysuru District Hospital",
            "Irwin Road, Mysuru", "Nearby",
            null, null, 12.3052, 76.6551
        ),
        NearbyHospital(
            "Mangaluru Wenlock Hospital",
            "Hampankatta, Mangaluru", "Nearby",
            null, null, 12.8679, 74.8429
        ),
        NearbyHospital(
            "Hubballi KIMS Hospital",
            "Vidyanagar, Hubballi", "Nearby",
            null, null, 15.3647, 75.1240
        ),
        NearbyHospital(
            "Belagavi District Hospital",
            "Nehru Nagar, Belagavi", "Nearby",
            null, null, 15.8497, 74.4977
        ),
        NearbyHospital(
            "Emergency — Call 108",
            "Ambulance Service Karnataka", "Call Now",
            true, null, 12.9716, 77.5946
        )
    )
}

// ─── Main fetch function ───────────────────────────────────────────────────

private suspend fun fetchNearbyHospitals(
    context: android.content.Context,
    onResult: (List<NearbyHospital>, String?, String) -> Unit
) {
    withContext(Dispatchers.IO) {
        try {
            val internetAvailable = isInternetAvailable(context)
            android.util.Log.d("HospitalFinder", "Internet available: $internetAvailable")

            // ✅ No internet — load from cache or hardcoded
            if (!internetAvailable) {
                val cached = loadHospitalsFromCache(context)
                if (cached.isNotEmpty()) {
                    withContext(Dispatchers.Main) {
                        onResult(cached, null, "📵 Offline — showing saved hospitals")
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onResult(
                            getHardcodedHospitals(),
                            null,
                            "📵 Offline — showing default hospitals"
                        )
                    }
                }
                return@withContext
            }

            // ✅ Check location permission
            if (ContextCompat.checkSelfPermission(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                withContext(Dispatchers.Main) {
                    onResult(emptyList(), "Location permission denied", "")
                }
                return@withContext
            }

            // ✅ Get user location
            val fusedClient = LocationServices.getFusedLocationProviderClient(context)
            var userLat = 0.0
            var userLng = 0.0

            val lastLoc = fusedClient.lastLocation.await()
            if (lastLoc != null) {
                userLat = lastLoc.latitude
                userLng = lastLoc.longitude
                android.util.Log.d("HospitalFinder", "lastLocation: $userLat, $userLng")
            } else {
                android.util.Log.d("HospitalFinder", "Requesting fresh location...")
                val deferred = CompletableDeferred<Location?>()
                val request = LocationRequest.create().apply {
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                    numUpdates = 1
                    interval = 1000
                    fastestInterval = 500
                }
                val callback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        fusedClient.removeLocationUpdates(this)
                        deferred.complete(result.lastLocation)
                    }
                }
                withContext(Dispatchers.Main) {
                    fusedClient.requestLocationUpdates(
                        request, callback, Looper.getMainLooper()
                    )
                }
                val freshLoc = withTimeoutOrNull(10_000) { deferred.await() }
                if (freshLoc != null) {
                    userLat = freshLoc.latitude
                    userLng = freshLoc.longitude
                    android.util.Log.d("HospitalFinder", "Fresh: $userLat, $userLng")
                } else {
                    // GPS timeout — use Bengaluru default
                    userLat = 12.9716
                    userLng = 77.5946
                    android.util.Log.d("HospitalFinder", "GPS timeout — using Bengaluru")
                }
            }

            // ✅ Call Google Places API
            val apiKey = "AIzaSyCpzx8U0p_w17EB8O-NHfXl76NdTmaLD94"
            val url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                    "?location=$userLat,$userLng" +
                    "&radius=5000" +
                    "&type=hospital" +
                    "&key=$apiKey"

            android.util.Log.d("HospitalFinder", "URL: $url")
            val response = URL(url).readText()
            val json = JSONObject(response)
            val status = json.getString("status")
            android.util.Log.d("HospitalFinder", "Status: $status")

            when (status) {
                "OK" -> {
                    val results = json.getJSONArray("results")
                    val hospitalList = mutableListOf<NearbyHospital>()

                    for (i in 0 until minOf(results.length(), 15)) {
                        val place = results.getJSONObject(i)
                        val name = place.getString("name")
                        val address = place.optString("vicinity", "Address not available")
                        val isOpen = place.optJSONObject("opening_hours")
                            ?.optBoolean("open_now")
                        val rating = if (place.has("rating"))
                            place.getDouble("rating").toFloat() else null
                        val geo = place.getJSONObject("geometry")
                            .getJSONObject("location")
                        val placeLat = geo.getDouble("lat")
                        val placeLng = geo.getDouble("lng")
                        val distKm = calculateDistance(
                            userLat, userLng, placeLat, placeLng
                        )
                        val distStr = if (distKm < 1.0)
                            "${(distKm * 1000).toInt()} m"
                        else
                            String.format("%.1f km", distKm)

                        hospitalList.add(
                            NearbyHospital(
                                name = name,
                                address = address,
                                distance = distStr,
                                isOpen = isOpen,
                                rating = rating,
                                lat = placeLat,
                                lng = placeLng
                            )
                        )
                    }

                    hospitalList.sortBy { distanceToMeters(it.distance) }

                    // ✅ Save for offline use
                    saveHospitalsToCache(context, hospitalList)

                    withContext(Dispatchers.Main) {
                        onResult(
                            hospitalList, null,
                            "📡 Live • $userLat, $userLng"
                        )
                    }
                }

                "ZERO_RESULTS" -> {
                    withContext(Dispatchers.Main) {
                        onResult(emptyList(), null, "No hospitals found in 5km")
                    }
                }

                "REQUEST_DENIED" -> {
                    val errMsg = json.optString(
                        "error_message", "API key error"
                    )
                    android.util.Log.e("HospitalFinder", "REQUEST_DENIED: $errMsg")
                    val cached = loadHospitalsFromCache(context)
                    if (cached.isNotEmpty()) {
                        withContext(Dispatchers.Main) {
                            onResult(
                                cached, null,
                                "📵 API error — showing cached hospitals"
                            )
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onResult(
                                getHardcodedHospitals(), null,
                                "API Error — showing default hospitals"
                            )
                        }
                    }
                }

                else -> {
                    withContext(Dispatchers.Main) {
                        onResult(emptyList(), "Google returned: $status", "")
                    }
                }
            }

        } catch (e: Exception) {
            android.util.Log.e("HospitalFinder", "Exception: ${e.message}", e)
            val cached = loadHospitalsFromCache(context)
            if (cached.isNotEmpty()) {
                withContext(Dispatchers.Main) {
                    onResult(cached, null, "📵 Error — showing cached hospitals")
                }
            } else {
                withContext(Dispatchers.Main) {
                    onResult(
                        getHardcodedHospitals(), null,
                        "📵 Offline — showing default hospitals"
                    )
                }
            }
        }
    }
}

// ─── Distance helpers ──────────────────────────────────────────────────────

private fun distanceToMeters(distance: String): Int {
    return try {
        when {
            distance.contains("km") ->
                (distance.replace(" km", "").toFloat() * 1000).toInt()
            distance.contains("m") ->
                distance.replace(" m", "").toInt()
            else -> Int.MAX_VALUE
        }
    } catch (e: Exception) {
        Int.MAX_VALUE
    }
}

private fun calculateDistance(
    lat1: Double, lng1: Double,
    lat2: Double, lng2: Double
): Double {
    val r = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLng = Math.toRadians(lng2 - lng1)
    val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(Math.toRadians(lat1)) *
            Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLng / 2) * Math.sin(dLng / 2)
    return r * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
}