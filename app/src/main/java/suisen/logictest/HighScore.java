package suisen.logictest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suisen on 08/07/16.
 */
public class HighScore extends AppCompatActivity {
    RecyclerView rv;
    ImageButton back;
    int[] scores;
    final String[] CATS = {"Numeric","Verbal","Logical","Spatial"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        rv = (RecyclerView) findViewById(R.id.rvcategories);

        scores = new int[4];

        for(int i = 0 ; i<scores.length ; i++)
            scores[i] = getDefaults(CATS[i].toLowerCase(),HighScore.this);

        rv.setLayoutManager(new LinearLayoutManager(this));
        ScorECategory allScoreAdapter = new ScorECategory(getScoreInformation(), this);
        rv.setAdapter(allScoreAdapter);
    }
    private List<Score> getScoreInformation() {

        List<Score> scoreList = new ArrayList<>();

        for(int i = 0 ; i<scores.length ; i++)
            scoreList.add(new Score(R.drawable.champ, scores[i], CATS[i]));
        return scoreList;
    }

    public class Score {
        private int picId;
        private int scr;
        private String cat;

        public int getPicId() {
            return picId;
        }

        public int getScr() {
            return scr;
        }

        public String getCat() {
            return cat;
        }

        public Score(int imageResourceId, int scr, String cat) {
            this.picId = imageResourceId;
            this.scr = scr;
            this.cat = cat;
        }
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
}