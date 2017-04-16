package com.example.mmanoghna.simplifiedlearning;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.google.android.gms.analytics.internal.zzy.c;
import static com.google.android.gms.analytics.internal.zzy.o;


public class NewJLGApplication extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private TextView outtxt;
    public static final String TAG = "LOCATION SERVICES";
    private GoogleApiClient mGoogleApiClient;
    protected Location mlastlocation;
    private LocationRequest mLocationRequest;
    protected TextView mlatitudetxt;
    protected TextView mlongitudetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_jlgapplication);
        mlatitudetxt = (TextView) findViewById(R.id.latitude_text);
        mlongitudetext = (TextView) findViewById(R.id.latitude_text);
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    /**
     * Runs when a GoogleApiClient object successfully connects.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        // Provides a simple way of getting a device's location and is well suited for
        // applications that do not require a fine-grained location and that do not need location
        // updates. Gets the best and most recent location currently available, which may be null
        // in rare cases when a location is not available.
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlastlocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mlastlocation != null) {
            mlatitudetxt.setText(String.valueOf(mlastlocation.getLatitude()));
            mlongitudetext.setText(String.valueOf(mlastlocation.getLongitude()));
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    /*
    * Called by Google Play services if the connection to GoogleApiClient drops because of an
    * error.
    */
    public void onDisconnected() {
        Log.i(TAG, "Disconnected");
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }


    public void add(View view) {
        Intent j = new Intent(this, add.class);
        startActivity(j);
    }

    public void view1(View view) {
        Intent k = new Intent(this, view1.class);
        startActivity(k);
    }

    public void modify(View view) {
        Intent l = new Intent(this, modify.class);
        startActivity(l);
    }


}
