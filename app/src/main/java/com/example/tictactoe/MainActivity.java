package com.example.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static String GAME_STATUS = "Game in progress";
    static String[] BUTTONS_STR = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};
    static String USER_TURN = "true";
    Button b;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;
    Button b8;
    Button b9;
    TextView status;
    private Set<Button> unselected_buttons;
    private Button[] buttons;
    private Random rand = new Random();
    private boolean userTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button8);
        b9 = (Button) findViewById(R.id.button9);
        status = (TextView) findViewById(R.id.status);

        buttons = new Button[] {b, b2, b3, b4, b5, b6, b7, b8, b9};

        if (savedInstanceState == null) {
            unselected_buttons = new HashSet<>(Arrays.asList(b, b2, b3, b4, b5, b6, b7, b8, b9));

            int randomNum = rand.nextInt(2);
            if (randomNum > 0) {
                computerTurn();
            }
            userTurn = true;
        } else {
            unselected_buttons = new HashSet<>();
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        status.setText(savedInstanceState.getString(GAME_STATUS));
        userTurn = savedInstanceState.getBoolean(USER_TURN);


        for (int i = 0; i < 9; i++) {
            buttons[i].setText(savedInstanceState.getString(BUTTONS_STR[i]));
            if ((savedInstanceState.getString(BUTTONS_STR[i]) != null &&
                    !savedInstanceState.getString(BUTTONS_STR[i]).equals("")) ||
                    !status.getText().toString().equals("Game in progress")) {
                buttons[i].setClickable(false);
            }
            if (savedInstanceState.getString(BUTTONS_STR[i]) == null) {
                unselected_buttons.add(buttons[i]);
            }
        }

        if (!userTurn) {
            computerTurn();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        String status_str = status.getText().toString();
        savedInstanceState.putString(GAME_STATUS, status_str);
        savedInstanceState.putBoolean(USER_TURN, userTurn);
        for (int i = 0; i < 9; i++) {
            String button_str = buttons[i].getText().toString();
            savedInstanceState.putString(BUTTONS_STR[i], button_str);
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void button(View v) {
        b.setText("X");
        b.setClickable(false);
        unselected_buttons.remove(b);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button2(View v) {
        b2.setText("X");
        b2.setClickable(false);
        unselected_buttons.remove(b2);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button3(View v) {
        b3.setText("X");
        b3.setClickable(false);
        unselected_buttons.remove(b3);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button4(View v) {
        b4.setText("X");
        b4.setClickable(false);
        unselected_buttons.remove(b4);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button5(View v) {
        b5.setText("X");
        b5.setClickable(false);
        unselected_buttons.remove(b5);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button6(View v) {
        b6.setText("X");
        b6.setClickable(false);
        unselected_buttons.remove(b6);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button7(View v) {
        b7.setText("X");
        b7.setClickable(false);
        unselected_buttons.remove(b7);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button8(View v) {
        b8.setText("X");
        b8.setClickable(false);
        unselected_buttons.remove(b8);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void button9(View v) {
        b9.setText("X");
        b9.setClickable(false);
        unselected_buttons.remove(b9);
        if (!gameOver()) {
            computerTurn();
        }
    }

    public void computerTurn() {
        int randomNum;
        while (true) {
            randomNum = rand.nextInt(9);
            if (unselected_buttons.contains(buttons[randomNum])) {
                break;
            }
        }
        buttons[randomNum].setText("O");
        buttons[randomNum].setClickable(false);
        unselected_buttons.remove(buttons[randomNum]);
        if (!gameOver()) {
            userTurn = true;
        }
    }

    public boolean gameOver() {
        if (buttons[0].getText().equals("O")) {
            if ((buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) ||
                    (buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) ||
                    (buttons[4].getText().equals("O") && buttons[8].getText().equals("O"))) {
                status.setText(R.string.computer_wins);
            }
        } else if (buttons[0].getText().equals("X")) {
            if ((buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) ||
                    (buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) ||
                    (buttons[4].getText().equals("X") && buttons[8].getText().equals("X"))) {
                status.setText(R.string.user_wins);
            }
        }

        if (buttons[6].getText().equals("O")) {
            if (buttons[4].getText().equals("O") && buttons[2].getText().equals("O")) {
                status.setText(R.string.computer_wins);
            }
        } else if (buttons[6].getText().equals("X")) {
            if (buttons[4].getText().equals("X") && buttons[2].getText().equals("X")) {
                status.setText(R.string.user_wins);
            }
        }

        if (buttons[8].getText().equals("O")) {
            if ((buttons[6].getText().equals("O") && buttons[7].getText().equals("O")) ||
                    (buttons[2].getText().equals("O") && buttons[5].getText().equals("O"))) {
                status.setText(R.string.computer_wins);
            }
        } else if (buttons[8].getText().equals("X")) {
            if ((buttons[6].getText().equals("X") && buttons[7].getText().equals("X")) ||
                    (buttons[2].getText().equals("X") && buttons[5].getText().equals("X"))) {
                status.setText(R.string.user_wins);
            }
        }

        if (buttons[4].getText().equals("O")) {
            if ((buttons[1].getText().equals("O") && buttons[7].getText().equals("O")) ||
                    (buttons[3].getText().equals("O") && buttons[5].getText().equals("O"))) {
                status.setText(R.string.computer_wins);
            }
        } else if (buttons[4].getText() == "X") {
            if ((buttons[1].getText().equals("X") && buttons[7].getText().equals("X")) ||
                    (buttons[3].getText().equals("X") && buttons[5].getText().equals("X"))) {
                status.setText(R.string.user_wins);
            }
        }

        if (status.getText().equals("Computer wins") || status.getText().equals("User wins")) {
            for (Button button : unselected_buttons) {
                button.setClickable(false);
            }
            return true;
        }

        if (unselected_buttons.size() == 0) {
            status.setText(R.string.game_over);
            return true;
        }
        return false;
    }

    public void reset(View v) {
        for (int i = 0; i < 9; i++) {
            buttons[i].setClickable(true);
            buttons[i].setText("");
            unselected_buttons.add(buttons[i]);
        }
        status.setText(R.string.game_in_progress);

        int randomNum = rand.nextInt(2);
        if (randomNum > 0) {
            computerTurn();
        }
        userTurn = true;
    }
}
