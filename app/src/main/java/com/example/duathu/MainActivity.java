package com.example.duathu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtpoint;
    CheckBox chkOne,chkTwo,chkThree;
    SeekBar skOne,skTwo,skThree;
    ImageButton btnPlay;
    int diem = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        DisableSeekBar();
        addEvents();
    }

    private void DisableSeekBar() {
        skOne.setEnabled(false);
        skTwo.setEnabled(false);
        skThree.setEnabled(false);
    }

    private void addControls() {
        txtpoint = this.<TextView>findViewById(R.id.txtPoint);
        chkOne = this.<CheckBox>findViewById(R.id.chkOne);
        chkTwo = this.<CheckBox>findViewById(R.id.chkTwo);
        chkThree = this.<CheckBox>findViewById(R.id.chkThree);
        skOne = this.<SeekBar>findViewById(R.id.skOne);
        skTwo = this.<SeekBar>findViewById(R.id.skTwo);
        skThree = this.<SeekBar>findViewById(R.id.skThree);
        btnPlay = this.<ImageButton>findViewById(R.id.btnPlay);

        txtpoint.setText(diem+"");
    }


    private void addEvents() {
        CountDownTimer countDownTimer = new CountDownTimer(60000,300) {
            @Override
            public void onTick(long l) {
                Random random = new Random();
                int number1 = random.nextInt(4)+1;
                int number2 = random.nextInt(4)+1;
                int number3 = random.nextInt(4)+1;
                //Kiểm tra ONE WIN
                if(skOne.getProgress() >= skOne.getMax()){
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"ONE WIN",Toast.LENGTH_SHORT).show();
                    if(chkOne.isChecked()){
                        diem+=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else{
                        diem-=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    txtpoint.setText(diem+"");
                    EnableCheckBox();
                }
                //Kiểm tra TWO WIN
                if(skTwo.getProgress() >= skTwo.getMax()){
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"TWO WIN",Toast.LENGTH_SHORT).show();
                    if(chkTwo.isChecked()){
                        diem+=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else{
                        diem-=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    txtpoint.setText(diem+"");
                    EnableCheckBox();
                }
                //Kiểm tra THREE WIN
                if(skThree.getProgress() >= skThree.getMax()){
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"THREE WIN",Toast.LENGTH_SHORT).show();
                    if(chkThree.isChecked()){
                        diem+=10;
                        Toast.makeText(MainActivity.this, "Bạn đoán chính xác", Toast.LENGTH_SHORT).show();
                    }else{
                        diem-=5;
                        Toast.makeText(MainActivity.this, "Bạn đoán sai", Toast.LENGTH_SHORT).show();
                    }
                    txtpoint.setText(diem+"");
                    EnableCheckBox();
                }
                skOne.setProgress(skOne.getProgress()+number1);
                skTwo.setProgress(skTwo.getProgress()+number2);
                skThree.setProgress(skThree.getProgress()+number3);
            }

            @Override
            public void onFinish() {

            }
        };

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkOne.isChecked() || chkTwo.isChecked() || chkThree.isChecked()){
                    skOne.setProgress(0);
                    skTwo.setProgress(0);
                    skThree.setProgress(0);
                    btnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckBox();
                }else{
                    Toast.makeText(MainActivity.this, "Vui lòng đặt cược", Toast.LENGTH_SHORT).show();
                }
            }
        });

        chkOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkTwo.setChecked(false);
                    chkThree.setChecked(false);
                }
            }
        });

        chkTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkOne.setChecked(false);
                    chkThree.setChecked(false);
                }
            }
        });

        chkThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chkTwo.setChecked(false);
                    chkOne.setChecked(false);
                }
            }
        });
    }
    public void EnableCheckBox(){
        chkOne.setEnabled(true);
        chkTwo.setEnabled(true);
        chkThree.setEnabled(true);
    }

    public void DisableCheckBox(){
        chkOne.setEnabled(false);
        chkTwo.setEnabled(false);
        chkThree.setEnabled(false);
    }
}