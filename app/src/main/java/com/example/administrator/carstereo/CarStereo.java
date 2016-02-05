package com.example.administrator.carstereo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class CarStereo extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_stereo);
    }
    int i = 1;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_stereo, menu);
        final SeekBar tuner = (SeekBar)findViewById(R.id.tuner);
        Button power = (Button) findViewById(R.id.powerButton);
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button power = (Button) findViewById(R.id.powerButton);
                TextView station = (TextView) findViewById(R.id.Station);
                Button preset1 = (Button) findViewById(R.id.tuner1);
                Button preset2 = (Button) findViewById(R.id.tuner2);
                Button preset3 = (Button) findViewById(R.id.tuner3);
                Button preset4 = (Button) findViewById(R.id.tuner4);
                Button preset5 = (Button) findViewById(R.id.tuner5);
                Button preset6 = (Button) findViewById(R.id.tuner6);
                TextView volume = (TextView) findViewById(R.id.volLevel);
                Button eject = (Button) findViewById(R.id.ejectButton);
                Switch amFm = (Switch) findViewById(R.id.amfmSwitch);
                if (i % 2 != 0) {
                    station.setBackgroundColor(Color.parseColor("#FF000000"));
                    volume.setBackgroundColor(Color.parseColor("#FF000000"));
                    power.setTextColor(Color.parseColor("#FF000000"));
                    preset1.setTextColor(Color.parseColor("#FF000000"));
                    preset2.setTextColor(Color.parseColor("#FF000000"));
                    preset3.setTextColor(Color.parseColor("#FF000000"));
                    preset4.setTextColor(Color.parseColor("#FF000000"));
                    preset5.setTextColor(Color.parseColor("#FF000000"));
                    preset6.setTextColor(Color.parseColor("#FF000000"));
                    eject.setTextColor(Color.parseColor("#FF000000"));
                    amFm.setEnabled(false);
                    tuner.setEnabled(false);

                } else {
                    power.setTextColor(Color.parseColor("#ff00df00"));
                    eject.setTextColor(Color.parseColor("#ff00df00"));
                    preset1.setTextColor(Color.parseColor("#ff00df00"));
                    preset2.setTextColor(Color.parseColor("#ff00df00"));
                    preset3.setTextColor(Color.parseColor("#ff00df00"));
                    preset4.setTextColor(Color.parseColor("#ff00df00"));
                    preset5.setTextColor(Color.parseColor("#ff00df00"));
                    preset6.setTextColor(Color.parseColor("#ff00df00"));
                    station.setBackgroundColor(Color.parseColor("#ff00df00"));
                    volume.setBackgroundColor(Color.parseColor("#ff00df00"));
                    amFm.setEnabled(true);
                    tuner.setEnabled(true);
                }
                i++;

            }
        });



        String suffix;
        String current;

        tuner.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar arg0) {

            }
            public void onStartTrackingTouch(SeekBar arg0){

            }
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2)
            {
                Switch amFm = (Switch)findViewById(R.id.amfmSwitch);
                if(amFm.isChecked())
                {
                    int min = 881;
                    int max = 1079;
                    int step = 2;
                    String suffix = "MHz";
                    tuner.setMax(99);
                    String current = "" + (double)((step*tuner.getProgress())+(int)min)/10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }
                else{
                    int min = 53;
                    int max = 170;
                    int step = 10;
                    String suffix = "kHz";
                    tuner.setMax(170-53);
                    String current = "" + (tuner.getProgress()+(int)min)*10+suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }

            }
        });

        final Switch amFm = (Switch)findViewById(R.id.amfmSwitch);
        amFm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SeekBar tuner = (SeekBar) findViewById(R.id.tuner);
                if (isChecked) {
                    int min = 881;
                    int max = 1079;
                    int step = 2;
                    String suffix = "MHz";
                    tuner.setMax(99);
                    String current = "" + (double) ((step * tuner.getProgress()) + min) / 10 + suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);

                } else {
                    int min = 53;
                    int max = 170;
                    int step = 10;
                    String suffix = "kHz";
                    tuner.setMax(170 - 53);
                    String current = "" + (tuner.getProgress() + min) * 10 + suffix;
                    TextView station = (TextView) findViewById(R.id.Station);
                    station.setText(current);
                }
            }
        });
        Button preset1 = (Button)findViewById(R.id.tuner1);
        Button preset2 = (Button)findViewById(R.id.tuner2);
        Button preset3 = (Button)findViewById(R.id.tuner3);
        Button preset4 = (Button)findViewById(R.id.tuner4);
        Button preset5 = (Button)findViewById(R.id.tuner5);
        Button preset6 = (Button)findViewById(R.id.tuner6);

        final int amArray[] = {2, 7, 12, 17, 22, 27};
        final int fmArray[] = {14, 24, 34, 44, 54, 64};

        Context context = getApplicationContext();
        CharSequence text = "Preset saved!";
        int duration = Toast.LENGTH_SHORT;

        final Toast toast = Toast.makeText(context, text, duration);


        preset1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[0] = tuner.getProgress();
                    toast.show();
                }

                else if (amFm.isClickable()) {
                    fmArray[0] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });
        preset2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[1] = tuner.getProgress();
                    toast.show();
                }else if (amFm.isClickable()) {
                    fmArray[1] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });
        preset3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[2] = tuner.getProgress();
                    toast.show();
                }else if (amFm.isClickable()) {
                    fmArray[2] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });
        preset4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[3] = tuner.getProgress();
                    toast.show();
                }else if (amFm.isClickable()) {
                    fmArray[3] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });
        preset5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[4] = tuner.getProgress();
                    toast.show();
                }else if (amFm.isClickable()) {
                    fmArray[4] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });
        preset6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (amFm. isClickable() && amFm.isChecked() == false) {
                    amArray[5] = tuner.getProgress();
                    toast.show();
                }else if (amFm.isClickable()) {
                    fmArray[5] = tuner.getProgress();
                    toast.show();
                }
                return true;
            }
        });

        preset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[0]);

                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[0]);
                }
            }
        });


        preset2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[1]);
                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[1]);
                }
            }
        });

        preset3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[2]);
                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[2]);
                }
            }
        });
        preset4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[3]);
                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[3]);
                }
            }
        });

        preset5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[4]);
                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[4]);
                }
            }
        });

        preset6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (amFm.isClickable() && amFm.isChecked() == false) {
                    tuner.setProgress(amArray[5]);
                }
                else if (amFm.isClickable()) {
                    tuner.setProgress(fmArray[5]);
                }
            }
        });

        final SeekBar volume = (SeekBar)findViewById(R.id.volume);
        volume.setMax(100);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String current = "" + (volume.getProgress() + "%");
                TextView volLevel = (TextView) findViewById(R.id.volLevel);
                volLevel.setText(current);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
