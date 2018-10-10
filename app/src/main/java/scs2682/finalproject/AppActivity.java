package scs2682.finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import scs2682.finalproject.ui.home.NotePager;
import scs2682.finalproject.ui.home.events.OnNoteEvent;
import scs2682.finalproject.ui.home.events.OnNoteUpdatedEvent;
import scs2682.finalproject.ui.home.mainboard.NoteFragment1;
import scs2682.finalproject.ui.home.mainboard.NoteFragment2;
import scs2682.finalproject.ui.home.mainboard.NoteFragment3;
import scs2682.finalproject.ui.home.mainboard.NoteFragment4;

public class  AppActivity extends AppCompatActivity {


    public static void hideKeyboard(Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = null;

        if (inputMethodManager == null) {
            return;
        }

        if (context instanceof Activity) {
            view = ((Activity) context).getCurrentFocus();
        }

        IBinder windowToken = view != null ? view.getWindowToken() : null;

        if (windowToken != null) {
            inputMethodManager.hideSoftInputFromInputMethod(windowToken, 0);
            inputMethodManager.hideSoftInputFromWindow(windowToken, 0);
        }
    }

    /**
     * Show soft keyboard
     *
     * @param context context If null will skip
     * @param view focused view.
     */
    public static void showKeyboard(Context context, @NonNull View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(view, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appactivity);

        if (savedInstanceState == null) {
            // add fragments only when activity just started
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.appLayout, new NoteFragment1())
                    .add(R.id.appLayout, new NoteFragment2())
                    .add(R.id.appLayout, new NoteFragment3())
                    .add(R.id.appLayout, new NoteFragment4())
                    .commit();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

