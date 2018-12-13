package com.putrinursofiyanti1605910.quiz_1605910_putri;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    TextView tvHasil;

    private SensorManager sm;
    private Sensor senAccel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHasil = (TextView) findViewById(R.id.tvHasil);

        sm = (SensorManager) getSystemService(getApplicationContext().SENSOR_SERVICE);
        senAccel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(senAccel != null){
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setMessage("Sukses, device punya sensor accelerometer!");
            ad.show();
        }else{
            AlertDialog ad = new AlertDialog.Builder(this).create();
            ad.setMessage("Tidak ada sensor accelerometer!");
            ad.show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double ax=0, ay=0, az=0;
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            ax=event.values[0];
            ay=event.values[1];
            az=event.values[2];
        }
        tvHasil.setText("x: "+ax+" y: "+ay+" az: "+az);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume(){
        super.onResume();
        sm.registerListener(this, senAccel, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause(){
        super.onPause();
        sm.unregisterListener(this);
    }
}
