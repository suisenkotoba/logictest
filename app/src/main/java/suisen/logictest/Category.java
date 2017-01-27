package suisen.logictest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by suisen on 08/07/16.
 */
public class Category extends AppCompatActivity {
    Button logic,numeric,spatial,verbal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_cat);
        numeric = (Button) findViewById(R.id.btn_cat1);
        numeric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Category.this,QuizActivity.class);
                myIntent.putExtra("id","numeric");
                startActivity(myIntent);
            }
        });
        verbal = (Button) findViewById(R.id.btn_cat2);
        verbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Category.this,QuizActivity.class);
                myIntent.putExtra("id","verbal");
                startActivity(myIntent);
            }
        });
        logic = (Button) findViewById(R.id.btn_cat3);
        logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Category.this,QuizActivity.class);
                myIntent.putExtra("id","logical");
                startActivity(myIntent);

            }
        });
        spatial = (Button) findViewById(R.id.btn_cat4);
        spatial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Category.this,QuizActivity.class);
                myIntent.putExtra("id","spatial");
                startActivity(myIntent);
            }
        });

    }

    public void onBackPressed(){
        Intent intent = new Intent(Category.this,MainActivity.class);
        startActivity(intent);
    }
}