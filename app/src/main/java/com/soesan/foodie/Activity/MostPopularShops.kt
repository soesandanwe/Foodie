package com.soesan.foodie.Activity

import android.content.ContentValues
import android.graphics.Paint
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.CameraUpdate
import kotlin.math.roundToInt


class MostPopularShops : AppCompatActivity(), AdapterView.OnItemSelectedListener, OnMapReadyCallback {
    var spinner: Spinner? = null
    var shops: Int=20
    private lateinit var mMap: GoogleMap
    private lateinit var mapView:MapView
    private val MAP_VIEW_BUNDLE_KEY = "AIzaSyAByJNQVORLPeY_3g3Y5KssRM3-PHkbsjw"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_most_popular_shops)
      //  setSupportActionBar(findViewById(R.id.main_toolbar));

        val recyclerViewPopular = findViewById<RecyclerView>(R.id.recyclerView_mostpopular)
        val mostPopularAdapter = MostPopularAdapter(shops,this )
        val lm= LinearLayoutManager(this, LinearLayout.VERTICAL,false)
        recyclerViewPopular.layoutManager = lm
        recyclerViewPopular.adapter = mostPopularAdapter
        recyclerViewPopular.setHasFixedSize(true)


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

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {

            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }


        mapView = findViewById(R.id.mapView_shop)
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)
        val mapViewParams = mapView.getLayoutParams()

        val displayMetrics = DisplayMetrics()
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val screenDisplayHeight = displayMetrics.heightPixels
        val bottom_slide_dimension = this.resources.getDimension(R.dimen.bottom_slide).toInt();
        val margin_dimension = this.resources.getDimension(R.dimen.activity_horizontal_margin).toInt();

        mapViewParams.height = (screenDisplayHeight-bottom_slide_dimension)-((bottom_slide_dimension/2)+margin_dimension);
        mapView.setLayoutParams(mapViewParams);

    }
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMinZoomPreference(5f);

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
    }
}
