package com.example.diveshsoni.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // 0 for Jerry 1 for tom

    int activePlayer = 0;

    //2 means unvisited cell

    static int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gameIsActive = true;



    public void dropIn(View view){


        Button btn = findViewById(R.id.button3);
        ImageView counter = (ImageView)view;

        int tappedState = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedState]==2 && gameIsActive) {
            gameState[tappedState] = activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.jerry);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.tom);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(100);

            for(int[] winningPosition : winningPositions){
                if( (gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[0]] != 2) ){

                    gameIsActive = false;
                    String winner = "";
                    if(gameState[winningPosition[0]] == 0)
                        winner = "Jerry";
                    else
                        winner = "Tom";

                    TextView  winnerMessage = findViewById(R.id.textView3);

                    winnerMessage.setText(winner+ " has won");
                    btn.setVisibility(View.VISIBLE);
                    LinearLayout layout =(LinearLayout) findViewById(R.id.linearLayout);
                    layout.setVisibility(View.VISIBLE);
                }

            }
        }
        else {

            boolean gameIsOver = true;
            for(int gameCounter : gameState)
            {
                if(gameCounter == 2)
                    gameIsOver = false;
            }
            if(gameIsOver){
                btn.setVisibility(View.VISIBLE);
                TextView  winnerMessage = findViewById(R.id.textView3);
                winnerMessage.setText("It's a draw");
                LinearLayout layout =(LinearLayout) findViewById(R.id.linearLayout);
                layout.setVisibility(View.VISIBLE);
            }
        }



    }
    public void playAgain(View view){
        Button btn = findViewById(R.id.button3);
        gameIsActive = true;
        LinearLayout layout =(LinearLayout) findViewById(R.id.linearLayout);
        layout.setVisibility(View.INVISIBLE);
        btn.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i = 0; i<gameState.length;i++)
            gameState[i] = 2;

        GridLayout grid = findViewById(R.id.gridLayout);
       for(int i=0; i<grid.getChildCount(); i++)
           ((ImageView)grid.getChildAt(i)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
