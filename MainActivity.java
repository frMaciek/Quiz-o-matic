package com.example.android.quiz_o_matic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Locale;

import static com.example.android.quiz_o_matic.R.id.user_given_answer_field;

public class MainActivity extends AppCompatActivity {

    Boolean allAnswersGiven;
    String textAnswerResult;
    String endMessage;
    int mQuizScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createResult(View view) {

        // Figure out if the user checks answer one
        CheckBox answerOneCheckbox = (CheckBox) findViewById(R.id.checkbox_answer_one);
        boolean hasAnswerOne = answerOneCheckbox.isChecked();

        // Figure out if the user checks answer two
        CheckBox answerTwoCheckbox = (CheckBox) findViewById(R.id.checkbox_answer_two);
        boolean hasAnswerTwo = answerTwoCheckbox.isChecked();

        // Figure out if the user checks answer three
        CheckBox answerThreeCheckbox = (CheckBox) findViewById(R.id.checkbox_answer_three);
        boolean hasAnswerThree = answerTwoCheckbox.isChecked();

        RadioButton q1Yes = (RadioButton) findViewById(R.id.q1Yes);
        boolean q1Agree = q1Yes.isChecked();
        RadioButton q2Yes = (RadioButton) findViewById(R.id.q2Yes);
        boolean q2Agree = q2Yes.isChecked();
        RadioButton q3Yes = (RadioButton) findViewById(R.id.q3Yes);
        boolean q3Agree = q3Yes.isChecked();
        RadioButton q4Yes = (RadioButton) findViewById(R.id.q4Yes);
        boolean q4Agree = q4Yes.isChecked();
        RadioButton q5Yes = (RadioButton) findViewById(R.id.q5Yes);
        boolean q5Agree = q5Yes.isChecked();
        RadioButton q1No = (RadioButton) findViewById(R.id.q1No);
        RadioButton q2No = (RadioButton) findViewById(R.id.q2No);
        RadioButton q3No = (RadioButton) findViewById(R.id.q3No);
        RadioButton q4No = (RadioButton) findViewById(R.id.q4No);
        RadioButton q5No = (RadioButton) findViewById(R.id.q5No);

        // checking if EditText field has any value entered by user

        EditText nameField = (EditText) findViewById(R.id.user_name_field);
        String userName = nameField.getText().toString();

        EditText usersAnswer = (EditText) findViewById(user_given_answer_field);
        String userGivenAnswer = usersAnswer.getText().toString();


        /** checking if all answers are given */
        if ((q1Yes.isChecked() || q1No.isChecked()) && (q2Yes.isChecked() || q2No.isChecked()) && (q3Yes.isChecked() || q3No.isChecked()) && (q4Yes.isChecked() || q4No.isChecked()) && (q5Yes.isChecked() || q5No.isChecked()) && (answerOneCheckbox.isChecked() || answerTwoCheckbox.isChecked() || answerThreeCheckbox.isChecked())) {
            Log.d("QAOD", "Answer is given");
            allAnswersGiven = true;
        } else {

            Log.d("QAOD", "Answer is Null");
            allAnswersGiven = false;
        }
        displayMessage(createResultSummary(mQuizScore, userName, q1Agree, q2Agree, q3Agree, q4Agree, q5Agree, endMessage, hasAnswerOne, hasAnswerTwo, hasAnswerThree, userGivenAnswer));
    }

    public void displayMessage(String message) {
        if (allAnswersGiven) {
            // if all answers are given results will be displayed
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_answer), Toast.LENGTH_SHORT).show();
        }
    }

    //Creating a result
    private String createResultSummary(int mQuizScore, String userName, boolean q1Agree, boolean q2Agree, boolean q3Agree, boolean q4Agree, boolean q5Agree, String endMessage, boolean hasAnswerOne, boolean hasAnswerTwo, boolean hasAnswerThree, String userGivenAnswer) {

        if (q1Agree) {
            mQuizScore += 1;
        }

        if (q2Agree) {
            mQuizScore += 1;
        }

        if (q3Agree) {
            mQuizScore += 1;
        }

        if (q4Agree) {
            mQuizScore += 1;
        }

        if (q5Agree) {
            mQuizScore += 1;
        }
        if (hasAnswerOne) {
            mQuizScore += 2;
        }

        if (hasAnswerTwo) {
            mQuizScore += 1;
        }

        if (hasAnswerThree) {
            mQuizScore += 0;
        }

        if (mQuizScore < 3) {
            endMessage = getString(R.string.score_low);
        } else if (mQuizScore == 3) {
            endMessage = getString(R.string.score_med);
        } else {
            endMessage = getString(R.string.score_high);
        }

        // checking answer given by the user in question seven
        boolean correct = "twelve".equals(userGivenAnswer.toLowerCase(Locale.getDefault()));

        if (correct) {
            textAnswerResult = getString(R.string.text_field_string_good_a) + " " + userGivenAnswer + " " + getString(R.string.text_field_string_good);
        } else {
            textAnswerResult = getString(R.string.text_field_string_bad_a) + " " + userGivenAnswer + " " + getString(R.string.text_field_string_bad);
        }

        //creating final message with quiz results
        String resultMessage = getString(R.string.congrats) + " " + userName;
        resultMessage = resultMessage + "\n" + getString(R.string.achieve) + " " + mQuizScore + " " + getString(R.string.points);
        resultMessage = resultMessage + "\n" + " " + textAnswerResult;
        resultMessage = resultMessage + "\n" + " " + endMessage;
//
        return resultMessage;
    }
}
