package com.example.grolapp2

import android.Manifest
import android.annotation.TargetApi
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.grolapp2.databinding.ActivityMapsBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


private const val TAG = "MapsActivity"
private lateinit var geoClient: GeofencingClient
private val REQUEST_TURN_DEVICE_LOCATION_ON = 20
private val REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE = 3
private val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 4
private val REQUEST_LOCATION_PERMISSION = 10


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    private val geofenceList =ArrayList<Geofence>()//Konzum
    private val geofenceList2 =ArrayList<Geofence>()//Kaufland
    private val geofenceList3 =ArrayList<Geofence>()//Lidl
    private val geofenceList4 =ArrayList<Geofence>()//Spar

    private val geofenceListFakultet = ArrayList<Geofence>()//test Fakultet

    private val gadgetQ = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q

    private val geofenceIntent: PendingIntent by lazy {
        val intent = Intent(this, GeofenceBroadcastReceiver::class.java)
        PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createChannel(this)

        geoClient = LocationServices.getGeofencingClient(this)


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(com.example.grolapp2.R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        //Zemljopisna dužina i širina za Konzum
        val latitude = 45.55603
        val longitude = 18.71994

        //Zemljopisna dužina i širina za Kaufland
        val latitude2 = 45.54447
        val longitude2 = 18.70939

        //Zemljopisna dužina i širina za Lidl
        val latitude3 = 45.55297
        val longitude3 = 18.73164

        //Zemljopisna dužina i širina za Spar
        val latitude4 = 45.54998
        val longitude4 = 18.69264

	    //Zemljopisna dužina i širina za fakultet
        val latitude6 = 45.55666
        val longitude6 = 18.71186

        val radius = 100f

        geofenceList.add(Geofence.Builder()
            .setRequestId("entry.key")
            .setCircularRegion(latitude,longitude,radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build())

        geofenceList2.add(Geofence.Builder()
            .setRequestId("entry.key")
            .setCircularRegion(latitude2,longitude2,radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()
        )

        geofenceList3.add(Geofence.Builder()
            .setRequestId("entry.key")
            .setCircularRegion(latitude3,longitude3,radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()
        )

        geofenceList4.add(Geofence.Builder()
            .setRequestId("entry.key")
            .setCircularRegion(latitude4,longitude4,radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()
        )

	    geofenceListFakultet.add(Geofence.Builder()
            .setRequestId("entry.key")
            .setCircularRegion(latitude6,longitude6,radius)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .build()
        )

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        //vrijednosti za Konzum
        val latlng = LatLng(45.55603,
            18.71994,)

        //vrijednosti za Kaufland
        val latlng2 = LatLng(45.54447, 18.70939,)

        //vrijednosti za Lidl
        val latlng3 = LatLng(45.55297, 18.73164)

        //vrijednosti za Spar
        val latlng4 = LatLng(45.54998, 18.69264)

        //vrijednosti za test Fakultet
        val latlng6 = LatLng(45.55666, 18.71186)

        //circleOptions za Konzum
        val circleOptions = CircleOptions()
            .center(latlng)
            .radius(20.0)
            .fillColor(0x40ff0000)
            .strokeColor(Color.BLUE)
            .strokeWidth(2f)

        //circleOptions za Kaufland
        val circleOptions2 = CircleOptions()
            .center(latlng2)
            .radius(20.0)
            .fillColor(0x40ff0000)
            .strokeColor(Color.BLUE)
            .strokeWidth(2f)

        //circleOptions za Lidl
        val circleOptions3 = CircleOptions()
            .center(latlng3)
            .radius(20.0)
            .fillColor(0x40ff0000)
            .strokeColor(Color.BLUE)
            .strokeWidth(2f)

        //circleOptions za Spar
        val circleOptions4 = CircleOptions()
            .center(latlng4)
            .radius(20.0)
            .fillColor(0x40ff0000)
            .strokeColor(Color.BLUE)
            .strokeWidth(2f)

        //circleOptions za test Fakultet
        val circleOptions6 = CircleOptions()
            .center(latlng6)
            .radius(20.0)
            .fillColor(0x40ff0000)
            .strokeColor(Color.BLUE)
            .strokeWidth(2f)

        val zoomLevel = 12f

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, zoomLevel))
        map.addMarker(MarkerOptions().position(latlng).title("Konzum"))//Konzum
        map.addCircle(circleOptions)

        map.addMarker(MarkerOptions().position(latlng2).title("Kaufland"))//Kaufland
        map.addCircle(circleOptions2)


        map.addMarker(MarkerOptions().position(latlng3).title("Lidl"))//Lidl
        map.addCircle(circleOptions3)

        map.addMarker(MarkerOptions().position(latlng4).title("Spar"))//Spar
        map.addCircle(circleOptions4)

        map.addMarker(MarkerOptions().position(latlng6).title("Test Fakultet"))//test Fakultet
        map.addCircle(circleOptions6)

        startLocation()
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) === PackageManager.PERMISSION_GRANTED
    }

    private fun startLocation() {
        if (isPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            map.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }


    //specify the geofence to monitor and the initial trigger
    private fun seekGeofencing(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geofenceList)//Konzum
            addGeofences(geofenceList2)//Kaufland
            addGeofences(geofenceList3)//Lidl
            addGeofences(geofenceList4)//Spar
            addGeofences(geofenceListFakultet)//test Fakultet
        }.build()
    }

    //adding a geofence
    private fun addGeofence(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        geoClient?.addGeofences(seekGeofencing(), geofenceIntent)?.run {
            addOnSuccessListener {
                Toast.makeText(this@MapsActivity, "Geofences added", Toast.LENGTH_SHORT).cancel()
            }
            addOnFailureListener {
                Toast.makeText(this@MapsActivity, "Failed to add geofences", Toast.LENGTH_SHORT).show()

            }
        }
    }

    //removing a geofence
    private fun removeGeofence(){
        geoClient?.removeGeofences(geofenceIntent)?.run {
            addOnSuccessListener {
                Toast.makeText(this@MapsActivity, "Geofences removed", Toast.LENGTH_SHORT).cancel()

            }
            addOnFailureListener {
                Toast.makeText(this@MapsActivity, "Failed to remove geofences", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun examinePermisionAndinitiatGeofence() {
        if (authorizedLocation()) {
            validateGadgetAreaInitiateGeofence()
        } else {
            askLocationPermission()
        }
    }

    // check if background and foreground permissions are approved
    @TargetApi(29)
    private fun authorizedLocation(): Boolean {
        val formalizeForeground = (
                PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION
                ))
        val formalizeBackground =
            if (gadgetQ) {
                PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_BACKGROUND_LOCATION
                )
            } else {
                true
            }
        return formalizeForeground && formalizeBackground
    }

    //requesting background and foreground permissions
    @TargetApi(29)
    private fun askLocationPermission() {
        if (authorizedLocation())
            return
        var grantingPermission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        val customResult = when {
            gadgetQ -> {
                grantingPermission += Manifest.permission.ACCESS_BACKGROUND_LOCATION
                REQUEST_FOREGROUND_AND_BACKGROUND_PERMISSION_RESULT_CODE
            }
            else -> REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        }
        Log.d(TAG, "askLocationPermission: ")
        ActivityCompat.requestPermissions(
            this,
            grantingPermission,
            customResult
        )

    }

    private fun validateGadgetAreaInitiateGeofence(resolve: Boolean = true) {
        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_LOW_POWER
        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)

        val client = LocationServices.getSettingsClient(this)
        val locationResponses =
            client.checkLocationSettings(builder.build())

        locationResponses.addOnFailureListener { exception ->
            if (exception is ResolvableApiException && resolve) {
                try {
                    exception.startResolutionForResult(
                        this,
                        REQUEST_TURN_DEVICE_LOCATION_ON
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    Log.d(TAG, "Error geting location settings resolution: " + sendEx.message)
                }
            } else {
                Toast.makeText(this, "Molimo, uključite lokacijeske usluge.", Toast.LENGTH_SHORT).show()
            }
        }
        locationResponses.addOnCompleteListener {
            if (it.isSuccessful) {
                addGeofence()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.size > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED))
                startLocation()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        validateGadgetAreaInitiateGeofence(false)
    }

    override fun onStart() {
        super.onStart()
        examinePermisionAndinitiatGeofence()
    }

    override fun onDestroy() {
        super.onDestroy()
        removeGeofence()
    }
}