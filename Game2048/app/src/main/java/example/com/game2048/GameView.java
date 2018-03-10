package example.com.game2048;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.net.sip.SipAudioCall;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/25.
 */

public class GameView extends GridLayout {
    public GameView(Context context) {
        super(context);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initGameView();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGameView();
    }



    private void initGameView(){


        setColumnCount(4);  //指定为4列
        setBackgroundColor(0xffbbada0);


        setOnTouchListener(new View.OnTouchListener(){
            private float startX,startY,offsetX,offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX =event.getX();
                        startY=event.getY();

                        break;
                    case MotionEvent.ACTION_UP :
                        offsetX =event.getX()-startX;    //偏移计算 = 后位置 - 前位置
                        offsetY = event.getY()-startY;
                        if (Math.abs(offsetX)>Math.abs(offsetY))
                        {
                            if (offsetX<-5){
                                System.out.print("left");
                                swipeLeft();



                            }else if (offsetX>5){
                                System.out.print("right");
                                swipeRight();
                            }
                        } else {
                            if (offsetY<-5){
                                System.out.print("up");
                                swipeUp();
                            }else if (offsetY>5){
                                System.out.print("down");
                                swipeDown();
                            }

                        }

                        break;
                }

                return true;
            }
        });


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int cardWidth = (Math.min(w,h) -10)/4;

        addCards(cardWidth,cardWidth);
        startGame();

    }
    private void addCards(int cardWidth,int cardHeight){
        Card c;
        for (int y=0;y<4;y++)
            for (int x=0;x<4;x++)
            {
                c = new Card(getContext());
                c.setNum(0);
                addView(c,cardWidth,cardHeight);
                cardMap[x][y] = c;
            }

    }

    private void swipeLeft(){
        for (int y=0;y<4;y++) {
            for (int k = 0; k < 3; k++) {
                for (int x = 3; x > 0; x--) {
                    int x1;
                    x1 = x - 1;

                    if (cardMap[x1][y].getNum() <= 0) {
                        cardMap[x1][y].setNum(cardMap[x][y].getNum());
                        cardMap[x][y].setNum(0);


                    } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                        cardMap[x1][y].setNum(cardMap[x][y].getNum() * 2);
                        cardMap[x][y].setNum(0);
                        MainActivity.getMainActivity().addscore(cardMap[x1][y].getNum());


                    }

                }
            }
        }
        Gamerule();

        }


    private void swipeRight(){
        for (int y=0;y<4;y++) {
            for (int k = 0; k < 3; k++) {
                for (int x = 0; x < 3; x++) {
                    int x1;
                    x1 = x + 1;

                    if (cardMap[x1][y].getNum() <= 0) {
                        cardMap[x1][y].setNum(cardMap[x][y].getNum());
                        cardMap[x][y].setNum(0);

                    } else if (cardMap[x][y].equals(cardMap[x1][y])) {
                        cardMap[x1][y].setNum(cardMap[x][y].getNum() * 2);
                        cardMap[x][y].setNum(0);
                        MainActivity.getMainActivity().addscore(cardMap[x1][y].getNum());


                    }

                }
            }
        }
        Gamerule();


    }
    private void swipeUp(){
        for (int x=0;x<4;x++) {
            for (int k = 0; k < 3; k++) {
                for (int y = 3; y > 0; y--) {
                    int y1;
                    y1 = y - 1;

                    if (cardMap[x][y1].getNum() <= 0) {
                        cardMap[x][y1].setNum(cardMap[x][y].getNum());
                        cardMap[x][y].setNum(0);

                    } else if (cardMap[x][y1].equals(cardMap[x][y])) {
                        cardMap[x][y1].setNum(cardMap[x][y].getNum() * 2);
                        cardMap[x][y].setNum(0);
                        MainActivity.getMainActivity().addscore(cardMap[x][y1].getNum());


                    }

                }
            }
        }
        Gamerule();

    }
    private void swipeDown(){
        for (int x=0;x<4;x++) {
            for (int k = 0; k < 3; k++) {
                for (int y = 0; y < 3; y++) {
                    int y1;
                    y1 = y + 1;

                    if (cardMap[x][y1].getNum() <= 0) {
                        cardMap[x][y1].setNum(cardMap[x][y].getNum());
                        cardMap[x][y].setNum(0);

                    } else if (cardMap[x][y1].equals(cardMap[x][y])) {
                        cardMap[x][y1].setNum(cardMap[x][y].getNum() * 2);
                        cardMap[x][y].setNum(0);
                        MainActivity.getMainActivity().addscore(cardMap[x][y1].getNum());


                    }

                }
            }
        }
        Gamerule();
    }
    private void startGame(){

        for (int x=0;x<4;x++)

            for (int y=0;y<4;y++)
                {
               cardMap[x][y].setNum(0);
                }
        addRandomNum();
        addRandomNum();









    }
    private Card[][] cardMap = new  Card[4][4];
    private void addRandomNum(){
        emptyPoints.clear();
        for (int y=0;y<4;y++)
            for (int x=0;x<4;x++){
                if (cardMap[x][y].getNum()<=0) {
                    emptyPoints.add(new Point(x,y));
            }

    }
        Point p = emptyPoints.remove((int)(Math.random()*emptyPoints.size()));
        cardMap[p.x][p.y] .setNum(Math.random()>0.1?2:4);

    }
    private List<Point> emptyPoints = new ArrayList<Point>();

    private int Gamescore;
    private void addscore(int toscore){
        Gamescore = Gamescore+toscore;


    }
    private  boolean Allover() {
        boolean k = true;
        for (int x = 0; x < 4; x++)
            for (int y = 0; y < 4; y++)
                if (cardMap[x][y].getNum()<=0){
                    k = false;


                    break;
                }
        return k;



    }
    private boolean Gameover() {
        boolean k = false;
        if (cardMap[0][3].getNum() != cardMap[1][3].getNum()
                && cardMap[1][3].getNum() != cardMap[2][3].getNum()
                && cardMap[2][3].getNum() != cardMap[3][3].getNum()) {
            for (int x = 1; x < 4; x++)
                for (int y = 1; y < 4; y++)
                    if (cardMap[x][y].getNum() != cardMap[x + 1][y].getNum()
                            && cardMap[x][y].getNum() != cardMap[x - 1][y].getNum()
                            && cardMap[x][y].getNum() != cardMap[x][y - 1].getNum()
                            && cardMap[x][y].getNum() != cardMap[x][y + 1].getNum()

                            ) {
                        k = true;

                    }else {
                        k = false;
                        break;
                    }

        }else {
            k = false;
        }
        return k;
    }

    private void Gamerule(){
        if (Allover()){
            if (Gameover()){
                new AlertDialog.Builder(getContext()).setTitle("hello").setMessage("Gameover").setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                    }
                }).show();



            }
        }else if (!Allover()){
            try {
                Thread.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();

            }
            addRandomNum();

        }
    }

/*    private void cardcolor(){
        for(int x=0;x<4;x++)
            for (int y=0;y<4;y++){
                if (cardMap[x][y].getNum()==4){

                }
            }
    }*/


}

