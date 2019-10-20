package com.example.myapplication;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //initial variables
    //accelerometer
    private static final float GRAVITY_THRESHOLD = 1.5F;
    private SensorManager sm;
    private Sensor sensorObj;

    //media player for audio effects
    private MediaPlayer mp;

    //UI controls
    private Button btnRoll;
    private EditText txtDiceNo;
    private TextView txtAlertMsg;

    //dice images
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView imgKid;

    //other variables
    Random rand = new Random();
    int randomNum = 1;
    int totalScore = 0;
    int noOfDice = 0;

    int[] images = {R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize variables
        mp = MediaPlayer.create(this, R.raw.soundeffect);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorObj = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        btnRoll = findViewById(R.id.btnRoll);
        txtDiceNo = findViewById(R.id.txtDiceNo);
        txtAlertMsg = findViewById(R.id.txtAlertMsg);

        img1 = findViewById(R.id.imgDice1);
        img2 = findViewById(R.id.imgDice2);
        img3 = findViewById(R.id.imgDice3);
        img4 = findViewById(R.id.imgDice4);
        img5 = findViewById(R.id.imgDice5);
        img6 = findViewById(R.id.imgDice6);
        imgKid = findViewById(R.id.imgKid);

        // event that triggers on click of "Roll Dice" button
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUserAction();
            }
        });
    }

    //Triggers on every click of "Roll Dice"
    private void onUserAction() {

        //to make the image disappear on rolling dice
        imgKid.setVisibility(View.INVISIBLE);

        //No action when the no of dice text box is empty
        if (txtDiceNo.getText().toString().trim() == "") {
            imgKid.setVisibility(View.VISIBLE);
            return;
        }

        //No action when the no of dice text box is provided with non-integer
        try {
            noOfDice = Integer.parseInt(txtDiceNo.getText().toString().trim());
        } catch (Exception e) {
            imgKid.setVisibility(View.VISIBLE);
            return;
        }

        //No action when the no of dice text box is not provided with value between 1 and 6
        if (noOfDice < 1 || noOfDice > 6) {
            txtAlertMsg.setText("Maximum of 6 dice are allowed!");
            txtAlertMsg.setTextSize(17);
            txtAlertMsg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            txtAlertMsg.setVisibility(View.VISIBLE);
            imgKid.setVisibility(View.VISIBLE);
            return;
        }

        // to give sound effects every time user rolls the dice
        mp.start();

        // to hide the error messages when not required
        txtAlertMsg.setVisibility(View.INVISIBLE);

        totalScore = 0;
        int value = 0;

        img1.setVisibility(View.VISIBLE);
        img2.setVisibility(View.VISIBLE);
        img3.setVisibility(View.VISIBLE);
        img4.setVisibility(View.VISIBLE);
        img5.setVisibility(View.VISIBLE);
        img6.setVisibility(View.VISIBLE);

        // based on the number of dice chosen by the player, the display position of images changes.
        // Also, a random number is generated for the dice to roll randomly.
        // Total score is calculated based on the dice rolled.
        switch (noOfDice) {
            case 1:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;

                img2.setVisibility(View.INVISIBLE);
                img3.setVisibility(View.INVISIBLE);
                img4.setVisibility(View.INVISIBLE);
                img5.setVisibility(View.INVISIBLE);
                img6.setVisibility(View.INVISIBLE);
                break;
            case 2:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img4.setImageResource(images[value]);
                totalScore += value + 1;

                img2.setVisibility(View.INVISIBLE);
                img3.setVisibility(View.INVISIBLE);
                img5.setVisibility(View.INVISIBLE);
                img6.setVisibility(View.INVISIBLE);
                break;
            case 3:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img2.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img5.setImageResource(images[value]);
                totalScore += value + 1;

                img3.setVisibility(View.INVISIBLE);
                img4.setVisibility(View.INVISIBLE);
                img6.setVisibility(View.INVISIBLE);
                break;
            case 4:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img2.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img3.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img5.setImageResource(images[value]);
                totalScore += value + 1;

                img4.setVisibility(View.INVISIBLE);
                img6.setVisibility(View.INVISIBLE);
                break;
            case 5:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img2.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img3.setImageResource(images[value]);
                totalScore += value + 1;
                img4.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img5.setImageResource(images[value]);
                totalScore += value + 1;

                img6.setVisibility(View.INVISIBLE);
                break;
            case 6:
                value = rand.nextInt(images.length);
                img1.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img2.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img3.setImageResource(images[value]);
                totalScore += value + 1;
                img4.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img5.setImageResource(images[value]);
                totalScore += value + 1;
                value = rand.nextInt(images.length);
                img6.setImageResource(images[value]);
                totalScore += value + 1;
                break;
        }

        // to display total score
        txtAlertMsg.setText("Total score:    " + totalScore);
        txtAlertMsg.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        txtAlertMsg.setTextSize(24);
        txtAlertMsg.setVisibility(View.VISIBLE);
    }

    // to monitor the new raw sensor data including accuracy, time stamp etc.
    @Override
    public void onSensorChanged(SensorEvent event) {
        double x, y, z;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            double g_x = x / SensorManager.GRAVITY_EARTH;
            double g_Y = y / SensorManager.GRAVITY_EARTH;
            double g_z = z / SensorManager.GRAVITY_EARTH;

            // gForce will be close to 1 when there is no movement.
            float shake_speed = (float) Math.sqrt(g_x * g_x + g_Y * g_Y + g_z * g_z);
            System.out.println(shake_speed);

            if (shake_speed > GRAVITY_THRESHOLD) {
                onUserAction();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // to register the SensorEventListener
    @Override
    protected void onResume() {
        super.onResume();
        if (sensorObj != null) {
            sm.registerListener(this, sensorObj, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // to unregister the SensorEventListener
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
}
