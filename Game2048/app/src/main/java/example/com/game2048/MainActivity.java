package example.com.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public MainActivity(){
        mainActivity = this;

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVscore =(TextView) findViewById(R.id.TVscore);


    }
    public void clearscore(){
        score = 0;

    }
    public void  showscore(){
        TVscore.setText(""+score);
    }


    public void addscore(int k){
        score=score + k;
        showscore();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    private int score=0;
    private TextView TVscore;
    private static MainActivity mainActivity = null;
    public static MainActivity getMainActivity(){
        return mainActivity;
    }


}
