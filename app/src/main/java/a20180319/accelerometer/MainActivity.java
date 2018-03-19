package a20180319.accelerometer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private static final String TAG = "MainActivity111";
    SensorManager sensorManager;
    EditText etTxt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        // 获取程序界面上的文本框组件
        etTxt1 = (EditText) findViewById(R.id.txt1);
        // 获取系统的传感器管理服务
        sensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 为系统的加速度传感器注册监听器
        Log.d(TAG, "onResume() called");
        sensorManager.registerListener( this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop() called");
        sensorManager.unregisterListener(this);
        super.onStop();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged() called with: event = [" + event + "]");
        float[] valus = event.values;
        StringBuilder sb = new StringBuilder();
        sb.append("X方向上的加速度：");
        sb.append(valus[0]);
        sb.append("\nY方向上的加速度：");
        sb.append(valus[1]);
        sb.append("\nZ方向上的加速度：");
        sb.append(valus[2]);
        etTxt1.setText(sb.toString());
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged() called with: sensor = [" + sensor + "], accuracy = [" + accuracy + "]");
    }
}
