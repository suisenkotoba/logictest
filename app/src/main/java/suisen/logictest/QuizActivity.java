package suisen.logictest;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class QuizActivity extends AppCompatActivity {
    TextView header, question;
    RadioGroup opts;
    ImageButton back, forward,done;
    ImageView imagequest;
    String id;
    Quiz[] myQuizzes;
    private int increment;
    int [] myans ;
    Drawable d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_sheet);
        increment = 0;
        header = (TextView) findViewById(R.id.head);
        question = (TextView) findViewById(R.id.quest);
        imagequest = (ImageView) findViewById(R.id.imagequest);
        opts = (RadioGroup) findViewById(R.id.opts);
        back = (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.forward);
        done = (ImageButton) findViewById(R.id.done);

        Intent localIntent = getIntent();
        id = localIntent.getStringExtra("id");

        int i,j;
        Set<Integer> randidx = new LinkedHashSet<Integer>();
        myQuizzes = new Quiz[20];
        JSONObject obj;
        String JSONStr;
        String res;
        //get obj from assets
        try{
            if(id.equalsIgnoreCase("numeric"))
                res = "numeric.json";
            else if(id.equalsIgnoreCase("logical"))
                res = "logical.json";
            else if(id.equalsIgnoreCase("verbal"))
                res = "verbal.json";
            else
                res = "spatial.json";


            InputStream is = getAssets().open(res);
            int sizeOfJSONFile = is.available();
            //array that will store all the data
            byte[] bytes = new byte[sizeOfJSONFile];

            //reading data into the array from the file
            is.read(bytes);

            //close the input stream
            is.close();

            JSONStr = new String(bytes, "UTF-8");
            obj = new JSONObject(JSONStr);
            JSONArray aquizzes = obj.getJSONArray("quizzes");
            JSONObject tmp;
            JSONArray tmpans;
            Random randGenerator = new Random();
            int num =  aquizzes.length();
            while (randidx.size()<=myQuizzes.length){
                randidx.add(randGenerator.nextInt(num));
            }
            List<Integer> arr = new ArrayList<Integer>(randidx);
            for(i=0;i<myQuizzes.length;i++){
                //quiz object in array
                tmp=aquizzes.getJSONObject(arr.get(i));
                myQuizzes[i] = new Quiz();
                //question
                myQuizzes[i].setQuestion(tmp.getString("question"));

                //options
                tmpans = tmp.getJSONArray("options");
                for(j=0;j<5;j++)
                    myQuizzes[i].setOption(j,tmpans.getString(j));

                //key
                myQuizzes[i].setKey(tmp.getInt("answer"));
            }
            int score = obj.getInt("score");
        }catch (IOException e){
            Toast.makeText(QuizActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }catch (JSONException e){
            Toast.makeText(QuizActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        myans = new int[20];
        for(i=0;i<myans.length;i++)
            myans[i]=-1080;

        header.setText("Question " + 1 + " of " + 20);

        //TODO spatial
        if(id.equalsIgnoreCase("spatial")){
            if(myQuizzes[0].getQuestion().equalsIgnoreCase("Carilah gambar yang bukan kelompoknya!")){
                imagequest.setVisibility(View.GONE);
                question.setVisibility(View.VISIBLE);
                question.setText(myQuizzes[0].getQuestion());
            }
            else{
                question.setVisibility(View.GONE);
                imagequest.setVisibility(View.VISIBLE);
                imagequest.setImageResource(getResources().getIdentifier(myQuizzes[0].getQuestion(),
                        "drawable", getApplicationContext().getPackageName()));
            }

            for(j = 0; j<opts.getChildCount() ; j++){
                d = getResources().getDrawable(getResources().getIdentifier(myQuizzes[0].getOptions(j),
                        "drawable", getApplicationContext().getPackageName()),null);
                ((RadioButton)opts.getChildAt(j)).setCompoundDrawablesWithIntrinsicBounds(null, null, d, null);
            }
        } else {
            question.setText(myQuizzes[0].getQuestion());
            for(j = 0; j<opts.getChildCount() ; j++)
                ((RadioButton)opts.getChildAt(j)).setText(myQuizzes[0].getOptions(j));
        }

        if(increment==0){
            opts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (opts.getCheckedRadioButtonId() == R.id.optA)
                        myans[0] = R.id.optA;
                    else if (opts.getCheckedRadioButtonId() == R.id.optB)
                        myans[0] = R.id.optB;
                    else if (opts.getCheckedRadioButtonId() == R.id.optC)
                        myans[0] = R.id.optC;
                    else if (opts.getCheckedRadioButtonId() == R.id.optD)
                        myans[0] = R.id.optD;
                    else if (opts.getCheckedRadioButtonId() == R.id.optE)
                        myans[0] = R.id.optE;
                    else
                        myans[increment] = -1080;
                }
            });
//            Log.d("ANSWER", Integer.toString(myans[increment]));
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] scr = new int[3];
                scr[0]=0;scr[1]=0;scr[2]=0;
                //calculate the answer
                for(int i = 0; i<myans.length; i++){
                    switch(myans[i]){
                        case(R.id.optA): myans[i]=0;
                            break;
                        case(R.id.optB): myans[i]=1;
                            break;
                        case(R.id.optC): myans[i]=2;
                            break;
                        case(R.id.optD): myans[i]=3;
                            break;
                        case(R.id.optE): myans[i]=4;
                            break;
                        default: myans[i]=-1;
                            break;
                    }
                    if(myans[i]==myQuizzes[i].getKey())
                        scr[0]++;
                    else if(myans[i]==-1)
                        scr[2]++;
                    else
                        scr[1]++;
                }
                Intent myIntent = new Intent(QuizActivity.this,QuizSummary.class);
                Bundle myextras = new Bundle();
                myextras.putIntArray("summary",scr);
                myextras.putString("category",id);
                myIntent.putExtras(myextras);
                startActivity(myIntent);
            }
        });

    }

    public void clickThis(View v){
        opts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (opts.getCheckedRadioButtonId() == R.id.optA)
                    myans[increment] = R.id.optA;
                else if (opts.getCheckedRadioButtonId() == R.id.optB)
                    myans[increment] = R.id.optB;
                else if (opts.getCheckedRadioButtonId() == R.id.optC)
                    myans[increment] = R.id.optC;
                else if (opts.getCheckedRadioButtonId() == R.id.optD)
                    myans[increment] = R.id.optD;
                else if (opts.getCheckedRadioButtonId() == R.id.optE)
                    myans[increment] = R.id.optE;
                else
                    myans[increment] = -1080;

            }
        });
        Log.d("ANSWER,INCREMENT", Integer.toString(myans[increment])+" "+Integer.toString(increment));
        if(v.getId()==R.id.forward){
            increment++;
        }
        else if(v.getId()==R.id.back){
            increment --;
        }
        if(increment==0)
            back.setVisibility(View.INVISIBLE);
        else if(increment==1)
            back.setVisibility(View.VISIBLE);
        else if(increment==19){
            forward.setVisibility(View.GONE);
            done.setVisibility(View.VISIBLE);
        }
        Log.d("INCREMENT", Integer.toString(increment));
        header.setText("Question "+(increment+1) + " of " + 20);

        //TODO spatial
        if(id.equalsIgnoreCase("spatial")){
            if(myQuizzes[increment].getQuestion().equalsIgnoreCase("Carilah gambar yang bukan kelompoknya!")){
                imagequest.setVisibility(View.GONE);
                question.setVisibility(View.VISIBLE);
                question.setText(myQuizzes[increment].getQuestion());
            }
            else{
                question.setVisibility(View.GONE);
                imagequest.setVisibility(View.VISIBLE);
                imagequest.setImageResource(getResources().getIdentifier(myQuizzes[increment].getQuestion(),
                        "drawable",getApplicationContext().getPackageName()));
            }
            for(int j = 0; j<opts.getChildCount(); j++) {
                d = getResources().getDrawable(getResources().getIdentifier(myQuizzes[increment].getOptions(j),
                        "drawable",getApplicationContext().getPackageName()),null);
                ((RadioButton)opts.getChildAt(j)).setCompoundDrawablesWithIntrinsicBounds(null,null,d,null);
            }
        } else {
            question.setText(myQuizzes[increment].getQuestion());
            for(int j = 0; j<opts.getChildCount() ; j++)
                ((RadioButton)opts.getChildAt(j)).setText(myQuizzes[increment].getOptions(j));
        }

        switch(myans[increment]){
            case(R.id.optA) : opts.check(R.id.optA);
                break;
            case(R.id.optB) : opts.check(R.id.optB);
                break;
            case(R.id.optC) : opts.check(R.id.optC);
                break;
            case(R.id.optD) : opts.check(R.id.optD);
                break;
            case(R.id.optE) : opts.check(R.id.optE);
                break;
            default: ; opts.clearCheck();
                break;
        }
    }
}
