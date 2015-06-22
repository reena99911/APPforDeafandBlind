package com.example.first.dotkeyboard;

import android.content.Intent;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Hashtable;


public class MainActivity extends ActionBarActivity implements TextToSpeech.OnInitListener {

    Button btndot,btndash,btnspace,btnspeak,btnback;
    Vibrator myvibrate;
    EditText txtInput;
    TTSmanager ttSmanager = null;
    Hashtable morseMap = new Hashtable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);


        btndot=(Button)findViewById(R.id.buttondot);
        btndash=(Button)findViewById(R.id.buttondash);
        btnspace=(Button)findViewById(R.id.buttonspace);
        btnback=(Button)findViewById(R.id.buttonback);
        btnspeak=(Button)findViewById(R.id.buttonspeak);
        ttSmanager = new TTSmanager();
        ttSmanager.Init(this);

        txtInput=(EditText)findViewById(R.id.editText);
        //txtInput.setText(txtInput.getText(),TextView.BufferType.EDITABLE);
      /*  txtInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }
        });
        */

        myvibrate=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        btnspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    morseMap = new Hashtable<String, String>();
                    morseMap.put(".-", "a");
                    morseMap.put("-...", "b");
                    morseMap.put("-.-.", "c");
                    morseMap.put("-..", "d");
                    morseMap.put(".", "e");
                    morseMap.put("..-.", "f");
                    morseMap.put("--.", "g");
                    morseMap.put("....", "h");
                    morseMap.put("..", "i");
                    morseMap.put(".---", "j");
                    morseMap.put("-.-", "k");
                    morseMap.put(".-..", "l");
                    morseMap.put("--", "m");
                    morseMap.put("-.", "n");
                    morseMap.put("---", "o");
                    morseMap.put(".--.", "p");
                    morseMap.put("--.-", "q");
                    morseMap.put(".-.", "r");
                    morseMap.put("...", "s");
                    morseMap.put("-", "t");
                    morseMap.put("..-", "u");
                    morseMap.put("...-", "v");
                    morseMap.put(".--", "w");
                    morseMap.put("-..-", "x");
                    morseMap.put("-.--", "y");
                    morseMap.put("--..", "z");
                    morseMap.put(".----", "1");
                    morseMap.put("..---", "2");
                    morseMap.put("...--", "3");
                    morseMap.put("....-", "4");
                    morseMap.put(".....", "5");
                    morseMap.put("-....", "6");
                    morseMap.put("--...", "7");
                    morseMap.put("---..", "8");
                    morseMap.put("----.", "9");
                    morseMap.put("-----", "0");
                    morseMap.put(".----.", "\'");
                    morseMap.put(".--.-.", "@");
                    morseMap.put(".-...", "&");
                    morseMap.put("---...", ":");
                    morseMap.put("--..--", ",");
                    morseMap.put("...-..-", "$");
                    morseMap.put("-...-", "=");
                    morseMap.put("---.", "!");
                    morseMap.put("-.-.--", "!");
                    morseMap.put("-....-", "-");
                    morseMap.put("-.--.", "(");
                    morseMap.put("-.--.-", ")");
                    morseMap.put(".-.-.-", ".");
                    morseMap.put(".-.-.", "+");
                    morseMap.put("..--..", "?");
                    morseMap.put(".-..-.", "\"");
                    morseMap.put("-.-.-.", ";");
                    morseMap.put("-..-.", "/");
                    morseMap.put("..--.-", "_");
                    morseMap.put("....--", "#");
                    morseMap.put("", " ");
                    morseMap.put(".......", "   ");

                if(morseMap.containsKey(btnspace.getText()))
                {
                    String str=btnspace.getText().toString();
                    txtInput.setText(txtInput.getText() + morseMap.get(str).toString());
                    txtInput.setSelection(txtInput.getText().length());
                    btnspace.setText("");
                    myvibrate.vibrate(130);

                }else
                {
                    btnspace.setText("");



                    Toast.makeText(getApplicationContext(),"This is invalid input. Please enter valid input.",Toast.LENGTH_SHORT).show();
                    myvibrate.vibrate(400);
                }


                 }
        }
       );
        myvibrate=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        btnback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(txtInput.getText().toString().length() > 0){
                 String input = txtInput.getText().toString();
                 input = input.substring(0, input.length()-1);
                 txtInput.setText(input);
                 txtInput.setSelection(txtInput.getText().length());


             }
         myvibrate.vibrate(100);
          }
      }
        );

        btnspeak.setOnClickListener(new View.OnClickListener() {
             @Override
        public void onClick(View v) {
       if(txtInput.getText().toString().length() > 0){

           String text = txtInput.getText().toString();
           ttSmanager.InitQueue(text);

                }
         myvibrate.vibrate(100);
            }
         }
        );


        myvibrate=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        btndot.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          btnspace.setText(btnspace.getText().toString()+".");

             myvibrate.vibrate(40);
           }
         }
        );

        myvibrate=(Vibrator)getSystemService(MainActivity.VIBRATOR_SERVICE);
        btndash.setOnClickListener(new View.OnClickListener() {
          @Override
             public void onClick(View v) {
              btnspace.setText(btnspace.getText().toString()+"-");
             // editt1.setSelection(editt1.getText().length());
             myvibrate.vibrate(80);
             }
          }
        );

     }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ttSmanager.shutDown();
    }

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
        if (id == R.id.action_Morsechart) {
            Intent intent = new Intent(this,Morsechart.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {

    }
}
