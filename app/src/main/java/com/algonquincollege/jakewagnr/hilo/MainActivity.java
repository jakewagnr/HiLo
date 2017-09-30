/**
 *  Hi-Lo guessing game. Random number gets generated and
 *  user aims to guess the number in as few guesses as possible
 *  @author Jacob Wagner (wagn0070@algonquinlive.com)
 */


package com.algonquincollege.jakewagnr.hilo;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.algonquincollege.jakewagnr.hilo.MainActivity.LoginActivity.ABOUT_DIALOG_TAG;

public class MainActivity extends Activity {

    public Button resetButton;
    int guessCount = 0;
    int target = (int )(Math.random() * 1000 + 1);
    int guessInt;


    //set click listeners
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = (Button) findViewById(R.id.resetBtn);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
            reset();
            }
        });

        resetButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public  boolean  onLongClick(View v) {
                resetHold();

                return true;
            }
        });


    }

    //inflate and show menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //populate menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //validate input for guess field
    public void guessIsValid (View v){
        Button btn = (Button) v;
        EditText etGuess = findViewById(R.id.guessEntry);
        String guess = etGuess.getText().toString().trim();

        String pattern = "^([1-9][0-9]{0,2}|1000)$";
        if(!guess.matches((pattern))) {
            etGuess.setError(getString(R.string.guess_error));
            etGuess.requestFocus();
        }if(guess.matches((pattern))){
            guessInt = Integer.parseInt(guess);
            guessCount++;
            guessValid();
        }else{
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        }

    }

    //if guess is valid, process the results
    public void  guessValid() {
        EditText etGuess = findViewById(R.id.guessEntry);
        if(guessCount > 10){
            Toast.makeText(this, getText(R.string.fail) + " " + Integer.toString(target) + " " + getText(R.string.fail2), Toast.LENGTH_SHORT).show();
            etGuess.requestFocus();
        }if(guessInt == target){
            if(guessCount <= 5){
                Toast.makeText(this, getText(R.string.superior)+" "+Integer.toString(guessCount), Toast.LENGTH_SHORT).show();
            }if(guessCount >5){
                Toast.makeText(this, getText(R.string.excellent)+" "+Integer.toString(guessCount), Toast.LENGTH_SHORT).show();
            }
        }if(guessInt > target){
            Toast.makeText(this, R.string.high, Toast.LENGTH_SHORT).show();
            etGuess.requestFocus();
        }if(guessInt < target){
            Toast.makeText(this, R.string.low, Toast.LENGTH_SHORT).show();
            etGuess.requestFocus();
        }else{
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        }
    }

    //game reset
    public void reset(){
        guessCount = 0;
        target = (int )(Math.random() * 1000 + 1);
        Toast.makeText(this, R.string.new_game, Toast.LENGTH_LONG).show();
    }

    //hint generated by holding the reset button
    public void  resetHold(){
        Toast.makeText(this, getText(R.string.hint)+" "+Integer.toString(target), Toast.LENGTH_LONG).show();
    }

    public class LoginActivity extends Activity {
        // CLASS VARIABLES (by convention, class vars in upper-case)
        public static final String ABOUT_DIALOG_TAG = "About Dialog";
    }





}
