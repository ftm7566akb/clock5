package com.example.iranpc.myapplication2;


import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.content.SharedPreferences;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener{

    private TextView hours,minutes;
    private Button say;
    private int[] sounds={R.raw.s4o , R.raw.s50 , R.raw.s2 , R.raw.daghigheh , 0};
    private int loc=0;
    int[] sounds1={
            0,
            R.raw.s1,
            R.raw.s2,
            R.raw.s3,
            R.raw.s4,
            R.raw.s5,
            R.raw.s6,
            R.raw.s7,
            R.raw.s8,
            R.raw.s9,
            R.raw.s10,
            R.raw.s11,
            R.raw.s12,
            R.raw.s13,
            R.raw.s14,
            R.raw.s15,
            R.raw.s16,
            R.raw.s17,
            R.raw.s18,
            R.raw.s19,
            R.raw.s20,
    };
    int[] sounds1o={
            0,
            R.raw.s1o,
            R.raw.s2o,
            R.raw.s3o,
            R.raw.s4o,
            R.raw.s5o,
            R.raw.s6o,
            R.raw.s7o,
            R.raw.s8o,
            R.raw.s9o,
            R.raw.s10o,
            R.raw.s11o,
            R.raw.s12o,
            R.raw.s13o,
            R.raw.s14o,
            R.raw.s15o,
            R.raw.s16o,
            R.raw.s17o,
            R.raw.s18o,
            R.raw.s19o,
            R.raw.s20o,
    };
    int[] sounds10o={
            0,
            R.raw.s10o,
            R.raw.s20o,
            R.raw.s30o,
            R.raw.s40o,
            R.raw.s50o,
    };
    int[] sounds10={
            0,
            R.raw.s10,
            R.raw.s20,
            R.raw.s30,
            R.raw.s40,
            R.raw.s50,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hours=(TextView) findViewById(R.id.hours);
        minutes=(TextView) findViewById(R.id.minutes);
        say=(Button)findViewById(R.id.say);

        Typeface t=Typeface.createFromAsset(getAssets(),"digital7.ttf");
        hours.setTypeface(t);
        minutes.setTypeface(t);

        say.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final RadioGroup rdg = (RadioGroup) findViewById(R.id.radioGroup);
                rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Date d=new Date();
                        int h=d.getHours();
                        int m=d.getMinutes();
                        String hs=String.format("%02d" , h);
                        String ms=String.format("%02d" , m);
                        SharedPreferences  sh1;


                        switch (rdg.getCheckedRadioButtonId()) {
                            case R.id.twoelv:

                              sh1 = getSharedPreferences("hs" , MODE_PRIVATE);
                              sh1.edit().putInt("hs" , h);
                              if(h > 12)
                              {
                                h = h - 12;
                              }

                              //va dar inja kod pakhsh kardan saat("h" bala k b sourat 12 saate tabdil shode) va daqiqe
                                break;

                            case R.id.twoeny:

                                hours.setText(hs);
                                minutes.setText(ms);

                                if (h==0)
                                    h=12;
                                else if (h>12)
                                    h-=12;
                                int i=0;
                                sounds[i++]=m==0?sounds1[h]:sounds1o[h];
                                sounds[i++]=0;
                                if (m<20)
                                    sounds[i+1]=sounds1[m];
                                else
                                {
                                    int m10=m/10;
                                    int m1=m%10;
                                    sounds[i++]=m1==0?sounds10[m10]:sounds10o[m10];
                                    if (m1!=0)
                                        sounds[i++]=sounds[m1];
                                }

                                MediaPlayer mp=MediaPlayer.create(MainActivity.this , R.raw.saat);

                                mp.setOnCompletionListener(MainActivity.this);
                                mp.start();            }
                    });
                }

                @Override
                public void onCompletion(MediaPlayer mp) {
                    if (sounds[loc]!=0){
                        MediaPlayer m=MediaPlayer.create(this , sounds[loc]);
                        loc++;
                        m.setOnCompletionListener(this);
                        m.start();
                    }

                }
                                break;


                            default:
                                break;

                        }



                });


    @Override
    public void onCompletion(MediaPlayer mp){
            if (sounds[loc] != 0) {
                MediaPlayer m = MediaPlayer.create(this, sounds[loc]);
                loc++;
                m.setOnCompletionListener(this);
                m.start();
            }

        }
}
}
