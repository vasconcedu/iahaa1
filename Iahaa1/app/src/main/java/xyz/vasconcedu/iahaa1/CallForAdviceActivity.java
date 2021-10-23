package xyz.vasconcedu.iahaa1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CallForAdviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_for_advice);

        Intent baseCallForAdviceIntent = new Intent();
        PendingIntent pendingCallForAdviceIntent = PendingIntent.getActivity(
                this,
                42,
                baseCallForAdviceIntent,
                PendingIntent.FLAG_ONE_SHOT
        );

        Intent wrappingCallForAdviceIntent = new Intent();
        wrappingCallForAdviceIntent.putExtra("callForAdvicePI", pendingCallForAdviceIntent);

        Log.i("[Iahaa1]", "Okey dokey...");

        setResult(Activity.RESULT_OK, wrappingCallForAdviceIntent);
        finish();
    }
}
