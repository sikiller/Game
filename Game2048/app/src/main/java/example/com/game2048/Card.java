package example.com.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/2/25.
 */

public class Card extends FrameLayout{

    public Card(Context context) {
        super(context);
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setGravity(Gravity.CENTER);
        label.setBackgroundColor(0x33ffffff);

        LayoutParams lp = new LayoutParams(-1,-1); //使用此函数填充完整个布局
        addView(label,lp);
        lp.setMargins(10,10,0,0);


        setNum(0);
    }
    private int num = 0;
/*    public void setCardcolor(){
        label.setBackgroundColor();

    }*/

    public int getNum(){
        return num;
    }
    public void setNum(int num){
        this.num= num;
        label.setText(num+"");// setText需要字符串，num+"" 可强制把数字变为字符串；
        if (num <= 0){
            label.setText("");

        }else{
            label.setText(num+"");
        }

    }
    public boolean equals(Card o){
        return getNum() == o.getNum();


    }

    private TextView label;
}
