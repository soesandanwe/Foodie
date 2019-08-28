package com.soesan.foodie.Activity

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.*
import com.soesan.foodie.Adapter.MostPopularAdapter
import com.soesan.foodie.R
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.view.ViewGroup
import com.google.android.gms.maps.*
import android.util.DisplayMetrics
import android.view.ViewStub
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import kotlin.math.roundToInt
import com.google.android.gms.maps.SupportMapFragment

import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.util.AttributeSet
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback





class MostPopularShops : AppCompatActivity(), AdapterView.OnItemSelectedListener, OnMapReadyCallback,
    GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {
    var spinner: Spinner? = null
    var shops: Int=20
    var MY_LOCATION_REQUEST_CODE:Int=1
    private lateinit var mMap: GoogleMap


    //keytool -list -v -keystore "C:\Users\soe.sanda\.android\debug.keystore" -alias androiddebugkey -storepass android -keypass android

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_shops)


        val recyclerViewPopular = findViewById<RecyclerView>(R.id.recyclerView_mostpopular)
        val mostPopularAdapter = MostPopularAdapter(shops,this )
        val lm= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        recyclerViewPopular.layoutManager = lm
        recyclerViewPopular.adapter = mostPopularAdapter
        recyclerViewPopular.setHasFixedSize(true)

        //Sliding Up Pannel
        val mLayout = findViewById<SlidingUpPanelLayout>(R.id.sliding_layout)
        mLayout.setDragView(R.id.dragView)
        mLayout.addPanelSlideListener(object : SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {
                Log.i(ContentValues.TAG, "onPanelSlide, offset $slideOffset")
            }

            override fun onPanelStateChanged(panel: View, previousState: SlidingUpPanelLayout.PanelState, newState: SlidingUpPanelLayout.PanelState) {
                Log.i(ContentValues.TAG, "onPanelStateChanged $newState")
            }
        })
        mLayout.setFadeOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED)
            }
        })

        val place=findViewById<TextView>(R.id.tv_place)
        place.setPaintFlags(place.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
        place.setOnClickListener {
            // your code to perform when the user clicks on the TextView
            Toast.makeText(this@MostPopularShops, "You clicked on TextView 'Click Me'.", Toast.LENGTH_SHORT).show()
        }

        val shop_count=findViewById<TextView>(R.id.tv_shop_count)
        shop_count.text= shops.toString()+ " restaurants"
        val list_of_items = arrayOf("Item 1", "Item 2", "Item 3")

        spinner = findViewById<Spinner>(R.id.sp_distance)
        spinner!!.setOnItemSelectedListener(this)

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, R.layout.simple_spinner_style, list_of_items)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set Adapter to Spinner
        spinner!!.setAdapter(aa)

        val displayMetrics = DisplayMetrics()
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val screenDisplayHeight = displayMetrics.heightPixels
        val bottom_slide_dimension =mLayout.panelHeight
        val mapLayout=findViewById<View>(R.id.mapViewLayout)
        mapLayout.layoutParams.height= (screenDisplayHeight-(bottom_slide_dimension*2))


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapViewLayout) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }


    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(5f)

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15f))


        val mUiSettings = mMap.getUiSettings()
        mUiSettings.setZoomControlsEnabled(true)
        mUiSettings.setScrollGesturesEnabled(true)
        mUiSettings.setZoomGesturesEnabled(true)
        mUiSettings.setMapToolbarEnabled(true)
        mUiSettings.setCompassEnabled(true)
        mUiSettings.setMyLocationButtonEnabled(true)
        mUiSettings.setAllGesturesEnabled(true)


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
        }
        else
        {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                MY_LOCATION_REQUEST_CODE)

        }

    }

    override fun onMyLocationButtonClick(): Boolean {

        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();

        return false;
    }

    override fun onMyLocationClick(location: Location) {

        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_LOCATION_REQUEST_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    mMap.setMyLocationEnabled(true);
                } else {
                    mMap.setMyLocationEnabled(false);
                }
                return
            }

            else -> {
                mMap.setMyLocationEnabled(false);
            }
        }
    }

}
