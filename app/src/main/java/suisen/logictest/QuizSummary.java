package suisen.logictest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by suisen on 08/07/16.
 */
public class QuizSummary extends AppCompatActivity {
    ImageButton home;
    TextView wright,wrong,skipped,myscore;
    LinearLayout newhcr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_sum);
        home = (ImageButton) findViewById(R.id.home);
        wright = (TextView) findViewById(R.id.wright);
        wrong = (TextView) findViewById(R.id.wrong);
        skipped = (TextView) findViewById(R.id.skipped);
        myscore = (TextView) findViewById(R.id.sum);
        newhcr = (LinearLayout) findViewById(R.id.newhcr);

        Intent localIntent = getIntent();
        Bundle mySummary = localIntent.getExtras();
        int result[] = mySummary.getIntArray("summary");
        String cat = mySummary.getString("category");

        int scr = result[0]*10-result[1]*2;

        wright.setText(result[0]+" Right");
        wrong.setText(result[1]+" Wrong");
        skipped.setText(result[2]+" Skipped");
        myscore.setText("Your Score is " + scr);

        if(scr>getDefaults(cat,QuizSummary.this)){
            setDefaults(cat,scr,QuizSummary.this);
            newhcr.setVisibility(View.VISIBLE);
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gohome = new Intent(QuizSummary.this,MainActivity.class);
                startActivity(gohome);
            }
        });
    }

    public static void setDefaults(String key,int value,Context context){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key,value);
        editor.commit();

    }

    public static int getDefaults(String key, Context context){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPref.getInt(key,0);

    }

    public void onBackPressed(){
        Intent intent = new Intent(QuizSummary.this,Category.class);
        startActivity(intent);
    }
}