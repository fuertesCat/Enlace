package com.example.erikpc.enlace;


    import android.app.Activity;
    import android.content.Context;
    import android.media.MediaPlayer;
    import android.media.MediaRecorder;
    import android.support.v7.app.ActionBarActivity;
    import android.os.Bundle;
    import android.os.Environment;
    import android.view.Menu;
    import android.view.MenuItem;
    import java.io.IOException;
    import android.util.Log;
    import android.view.View.OnClickListener;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.LinearLayout;
    import android.widget.Button;


    public class Grabacion extends Activity {

        private static final String LOG_TAG = "Audio";
        private static String mFileName = null;

        private RecordButton mRecordButton = null;
        private MediaRecorder mRecorder = null;

        private PlayButton   mPlayButton = null;
        private MediaPlayer mPlayer = null;

        private void onRecord(boolean start) {
            if (start) {
                startRecording();
            } else {
                stopRecording();
            }
        }

        private void onPlay(boolean start) {
            if (start) {
                startPlaying();
            } else {
                stopPlaying();
            }
        }

        private void startPlaying() {
            mPlayer = new MediaPlayer();
            try {
                mPlayer.setDataSource(mFileName);
                mPlayer.prepare();
                mPlayer.start();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }
        }

        private void stopPlaying() {
            mPlayer.release();
            mPlayer = null;
        }

        private void startRecording() {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setOutputFile(mFileName);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try {
                mRecorder.prepare();
            } catch (IOException e) {
                Log.e(LOG_TAG, "prepare() failed");
            }

            mRecorder.start();
        }

        private void stopRecording() {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }

        class RecordButton extends Button {
            boolean mStartRecording = true;

            OnClickListener clicker = new OnClickListener() {
                public void onClick(View v) {
                    onRecord(mStartRecording);
                    if (mStartRecording) {
                        setText("Detener");
                    } else {
                        setText("grabar");
                    }
                    mStartRecording = !mStartRecording;
                }
            };

            public RecordButton(Context ctx) {
                super(ctx);
                setText("Grabar");
                setOnClickListener(clicker);
            }
        }

        class PlayButton extends Button {
            boolean mStartPlaying = true;

            OnClickListener clicker = new OnClickListener() {
                public void onClick(View v) {
                    onPlay(mStartPlaying);
                    if (mStartPlaying) {
                        setText("Detener ");
                    } else {
                        setText("Reproducir");
                    }
                    mStartPlaying = !mStartPlaying;
                }
            };

            public PlayButton(Context ctx) {
                super(ctx);
                setText("Reproducir");
                setOnClickListener(clicker);
            }
        }

        public Grabacion() {
            mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
            mFileName += "/flotante.mp3";
        }

        @Override
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);

            LinearLayout ll = new LinearLayout(this);
            mRecordButton = new RecordButton(this);
            ll.addView(mRecordButton,
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            0));
            mPlayButton = new PlayButton(this);
            ll.addView(mPlayButton,
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            0));
            setContentView(ll);


            Bundle bundle=this.getIntent().getExtras();
        }

        @Override
        public void onPause() {
            super.onPause();
            if (mRecorder != null) {
                mRecorder.release();
                mRecorder = null;
            }

            if (mPlayer != null) {
                mPlayer.release();
                mPlayer = null;
            }
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










}
