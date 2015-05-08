package com.example.erikpc.enlace;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.location.LocationListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class latitud extends Activity {


           	private Button btnActualizar;
        private Button btnDesactivar;
        	private TextView lblLatitud;
        	private TextView lblLongitud;
        	private TextView lblPrecision;
        	private TextView lblEstado;

            private LocationManager locManager;
        	private LocationListener locListener;

                	@Override
        	protected void onCreate(Bundle savedInstanceState) {
            	super.onCreate(savedInstanceState);
            	setContentView(R.layout.activity_latitud);

                    	btnActualizar = (Button)findViewById(R.id.button);
             btnDesactivar = (Button)findViewById(R.id.button2);
             lblLatitud = (TextView)findViewById(R.id.textView);
             lblLongitud = (TextView)findViewById(R.id.textView2);
             lblPrecision = (TextView)findViewById(R.id.textView3);
             lblEstado = (TextView)findViewById(R.id.textView4);

                     btnActualizar.setOnClickListener(new OnClickListener() {
                        	public void onClick(View v) {
                            	comenzarLocalizacion();
                            	}
                        	});

                     btnDesactivar.setOnClickListener(new OnClickListener() {
                        	public void onClick(View v) {
                            	locManager.removeUpdates(locListener);
                            	}
                        	});
            	}

                private void comenzarLocalizacion()
         {

                     locManager =
                     (LocationManager)getSystemService(Context.LOCATION_SERVICE);


                             Location loc =
                     locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


                             mostrarPosicion(loc);


                             locListener = new LocationListener() {
                	public void onLocationChanged(Location location) {
                    	mostrarPosicion(location);
                    	}
                	public void onProviderDisabled(String provider){
                    	lblEstado.setText("Provider OFF");
                    	}
                	public void onProviderEnabled(String provider){
                    	lblEstado.setText("Provider ON ");
                    	}
                	public void onStatusChanged(String provider, int status, Bundle extras){
                    	Log.i("", "Provider Status: " + status);
                    	lblEstado.setText("Provider Status: " + status);
                    	}
                 };

                     locManager.requestLocationUpdates(
                             LocationManager.GPS_PROVIDER, 30000, 0, locListener);
             }

                 private void mostrarPosicion(Location loc) {
             if(loc != null)
                {
                         lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
             lblLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
             lblPrecision.setText("Precision: " + String.valueOf(loc.getAccuracy()));
             Log.i("", String.valueOf(loc.getLatitude() + " - " + String.valueOf(loc.getLongitude())));
             }
             else
             {
                     lblLatitud.setText("Latitud: (sin_datos)");
             lblLongitud.setText("Longitud: (sin_datos)");
             lblPrecision.setText("Precision: (sin_datos)");
                 Bundle bundle=this.getIntent().getExtras();
             }
             }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_latitud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
