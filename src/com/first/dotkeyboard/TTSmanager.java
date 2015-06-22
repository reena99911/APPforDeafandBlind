package com.example.first.dotkeyboard;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import java.util.Locale;

/**
 * Created by reena on 8/4/15.
 */
public class TTSmanager {
    private TextToSpeech mTsh = null;
    private boolean isloaded = true;

    public void Init(Context context) {
        try {
            mTsh = new TextToSpeech(context, oninitListener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TextToSpeech.OnInitListener oninitListener = new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status == TextToSpeech.SUCCESS) {
                int result = mTsh.setLanguage(Locale.US);
                isloaded = true;
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("error", "this language not supported");
                } else {
                    Log.e("error", "Initialised failed");
                }
            }
        }
    };
    public void shutDown() {
        mTsh.shutdown();
    }

    public void addQueue(String text) {
        if (isloaded) {
            mTsh.speak(text, TextToSpeech.QUEUE_ADD, null);
        } else {
            Log.e("error", "TTS not initialised");
        }
    }

    public void InitQueue(String text) {
        if (isloaded) {
            mTsh.speak(text, TextToSpeech.QUEUE_ADD, null);
        } else {
            Log.e("error", "TTS not initialised");
        }
    }

}
