package com.example.erikpc.enlace;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.speech.RecognizerIntent;
import android.app.Activity;

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener {
    private ImageButton Camara;
    private ImageButton Llamada;
    private ImageButton grabar;
    private ImageButton Localizacion;
    private static final String LOG_TAG="grabadora";
    private MediaPlayer mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String fichero = Environment.
            getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Localizacion = (ImageButton)findViewById(R.id.imageButton4);
        Localizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent local=new Intent(MainActivity.this,latitud.class);
              //  Bundle b=new Bundle();
           //     b.putString();

                startActivity(local);
            }
        });
        Camara =(ImageButton)findViewById(R.id.imageButton);
        Camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager())!=null)
                startActivityForResult(intent,0);
            }

        });
        Llamada=(ImageButton)findViewById(R.id.imageButton2);
        Llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent llamar = new Intent(Intent.ACTION_DIAL);

                    if (llamar.resolveActivity(getPackageManager()) != null)
                        startActivity(llamar);
                    }


            });
        grabar =(ImageButton)findViewById(R.id.imageButton3);
        grabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent grabacion=new Intent(MainActivity.this, Grabacion.class);
                startActivity(grabacion);

            }
        });


            }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }







    @Override
    public void onClick(View v) {



    }

}
