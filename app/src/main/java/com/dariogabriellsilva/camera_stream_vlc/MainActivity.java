package com.dariogabriellsilva.camera_stream_vlc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pedro.vlc.VlcListener;
import com.pedro.vlc.VlcVideoLibrary;

import org.videolan.libvlc.MediaPlayer;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements VlcListener, View.OnClickListener {

    private VlcVideoLibrary vlcVideoLibrary;
    private Button bStartStop;
    private EditText etEndpoint;

    private String[] options = new String[]{":fullscreen"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_main);
        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        bStartStop = findViewById(R.id.b_start_stop);
        bStartStop.setOnClickListener(this);
        etEndpoint = findViewById(R.id.et_endpoint);
        vlcVideoLibrary = new VlcVideoLibrary(this, this, surfaceView);
        vlcVideoLibrary.setOptions(Arrays.asList(options));
    }

    @Override
    public void onComplete() {
        Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error, make sure your endpoint is correct", Toast.LENGTH_SHORT).show();
        vlcVideoLibrary.stop();
        bStartStop.setText(getString(R.string.start_player));
    }

    @Override
    public void onBuffering(MediaPlayer.Event event) {

    }

    @Override
    public void onClick(View view) {
        if (!vlcVideoLibrary.isPlaying()) {
            vlcVideoLibrary.play(etEndpoint.getText().toString());
            bStartStop.setText(getString(R.string.stop_player));
        } else {
            vlcVideoLibrary.stop();
            bStartStop.setText(getString(R.string.start_player));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}