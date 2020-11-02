    package com.example.demogame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.BarringInfo;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

    public class MainActivity extends AppCompatActivity {
    private int pressCounter=0;
    private int maPressCouter=4;
    private String [] keys = {"R","I","B","D","X"};
    private String textAnswer="BIRD";
    TextView textScreen,textQuestion,textTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        keys = shuffleArray(keys);
        for(String key : keys){
            addView(((LinearLayout)findViewById(R.id.layoutParent)),key,((EditText)findViewById(R.id.editText)));
        }
        maPressCouter=4;
    }
    private String [] shuffleArray(String [] arr){
        Random rdm = new Random();
        for(int i=arr.length-1;i>0;i--){
            int index = rdm.nextInt(i+1);
            String a = arr[index];
            arr[index]=arr[i];
            arr[i]=a;
        }
        return arr;
    }
     private void addView(LinearLayout viewParent,final  String text,final EditText editText){
        LinearLayout.LayoutParams linerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linerLayoutParams.rightMargin=20;
        final TextView textView = new TextView(this);
        textView.setLayoutParams(linerLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

         Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/FredokaOneRegular.ttf");
         textQuestion = (TextView)findViewById(R.id.textQuestion);
         textTitle = (TextView)findViewById(R.id.textTitle);
         textScreen = (TextView)findViewById(R.id.textScreen);

         textQuestion.setTypeface(typeface);
         textTitle.setTypeface(typeface);
         textScreen.setTypeface(typeface);
         textView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(pressCounter<maPressCouter){
                     if(pressCounter==0){
                         editText.setText("");
                         editText.setText(editText.getText().toString()+text);
//                         textView.startAnimation((smallbigforth));
                         textView.animate().alpha(0).setDuration(300);
                         pressCounter++;

                         if(pressCounter==maPressCouter){
                             doValidate();
                         }
                     }
                 }
             }
         });
         viewParent.addView(textView);

     }

        private void doValidate() {
            pressCounter=0;
            EditText editText = (EditText)findViewById(R.id.editText);
            LinearLayout linearLayout = findViewById(R.id.layoutParent);
            if(editText.getText().toString().equals(textAnswer)){
                Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                editText.setText("");

            }else {

                Toast.makeText(MainActivity.this,"Wrong",Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
            keys = shuffleArray(keys);
            linearLayout.removeAllViews();
            for(String key : keys){
                addView(linearLayout,key,editText);
            }
        }
    }