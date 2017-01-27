package suisen.logictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by suisen on 08/07/16.
 */
public class Credit extends AppCompatActivity {

    ImageView pic1,pic2;
    TextView one,two;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit);
        one = (TextView) findViewById(R.id.one);
        one.setText("Kurniatul Faiqoh");
        two = (TextView) findViewById(R.id.two);
        two.setText("Achmad Rizal");
        pic1 = (ImageView) findViewById(R.id.pic1);
        pic1.setImageResource(R.drawable.sr2128);
        pic2 = (ImageView) findViewById(R.id.pic2);
        pic2.setImageResource(R.drawable.sr4128);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
