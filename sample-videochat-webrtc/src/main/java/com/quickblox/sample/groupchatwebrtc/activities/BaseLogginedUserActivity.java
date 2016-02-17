package com.quickblox.sample.groupchatwebrtc.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.quickblox.sample.groupchatwebrtc.definitions.Consts;
import com.quickblox.sample.groupchatwebrtc.R;
import com.quickblox.sample.groupchatwebrtc.holder.DataHolder;
import com.quickblox.sample.groupchatwebrtc.managers.ResourcesManager;
import com.quickblox.users.model.QBUser;


/**
 * QuickBlox team
 */
public class BaseLogginedUserActivity extends Activity {
    private static final String TAG = BaseLogginedUserActivity.class.getSimpleName();

    private static final String APP_VERSION = "App version";
    static android.app.ActionBar mActionBar;
    private Chronometer timerABWithTimer;
    private boolean isStarted = false;

    public void initActionBar() {

        mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_view, null);

        TextView numberOfListAB = (TextView) mCustomView.findViewById(R.id.numberOfListAB);
        QBUser loggedUser = DataHolder.getLoggedUser();
        if (loggedUser != null ) {
            int number = DataHolder.getUserIndexByID(loggedUser.getId());
            numberOfListAB.setBackgroundResource(ResourcesManager.resourceSelector(number));
            numberOfListAB.setText(String.valueOf(number+1));

            TextView loginAsAB = (TextView) mCustomView.findViewById(R.id.loginAsAB);
            loginAsAB.setText(R.string.logged_in_as);
            //
            TextView userNameAB = (TextView) mCustomView.findViewById(R.id.userNameAB);
            userNameAB.setText(String.valueOf(number+1));
        }

        numberOfListAB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BaseLogginedUserActivity.this);
                dialog.setTitle(APP_VERSION);
                dialog.setMessage(Consts.VERSION_NUMBER);
                dialog.show();
                return true;
            }});


        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

    public void initActionBarWithTimer() {
        mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_with_timer, null);

        timerABWithTimer = (Chronometer) mCustomView.findViewById(R.id.timerABWithTimer);

        Log.d(TAG, "initActionBarWithTimer. timerABWithTimer == null: " + (timerABWithTimer == null));

        TextView loginAsABWithTimer = (TextView) mCustomView.findViewById(R.id.loginAsABWithTimer);
        loginAsABWithTimer.setText(R.string.logged_in_as);

        TextView userNameAB = (TextView) mCustomView.findViewById(R.id.userNameABWithTimer);
        QBUser user = DataHolder.getLoggedUser();
        if (user != null) {
            userNameAB.setText(user.getFullName());
        }

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void startTimer() {
        Log.d(TAG, "startTimer. timerABWithTimer == null: " + (timerABWithTimer == null));

        if (!isStarted) {
            if(timerABWithTimer == null) {
                initActionBarWithTimer();
            }
            timerABWithTimer.setBase(SystemClock.elapsedRealtime());
            timerABWithTimer.start();
            isStarted = true;
        }
    }

    public void stopTimer(){
        if (timerABWithTimer != null){
            timerABWithTimer.stop();
            isStarted = false;
        }
    }
}




