package com.example.android.quiz_o_matic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.quiz_o_matic.R.id.q1;
import static com.example.android.quiz_o_matic.R.id.q1No;
import static com.example.android.quiz_o_matic.R.id.q5;
import static com.example.android.quiz_o_matic.R.id.q5No;


public class MainActivity extends AppCompatActivity {


    int quizScore = 0;
    String endMessage;
    String extraStuff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createResult(View view) {

        // Figure out if the user wants takes umbrella
        CheckBox umbrellaCheckbox = (CheckBox) findViewById(R.id.checkbox_umbrella);
        boolean hasUmbrella = umbrellaCheckbox.isChecked();

        // Figure out if the user wants takes sun filter
        CheckBox sunFilterCheckbox = (CheckBox) findViewById(R.id.checkbox_sun_filter);
        boolean hasSunFilter = sunFilterCheckbox.isChecked();

        // Figure out if the user wants takes swiss army knife
        CheckBox swissArmyCheckbox = (CheckBox) findViewById(R.id.checkbox_swiss_army);
        boolean hasSwissArmyKnife = swissArmyCheckbox.isChecked();

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
        boolean q1Disagree = q1No.isChecked();
        RadioButton q2No = (RadioButton) findViewById(R.id.q2No);
        boolean q2Disagree = q2No.isChecked();
        RadioButton q3No = (RadioButton) findViewById(R.id.q3No);
        boolean q3Disagree = q3No.isChecked();
        RadioButton q4No = (RadioButton) findViewById(R.id.q4No);
        boolean q4Disagree = q4No.isChecked();
        RadioButton q5No = (RadioButton) findViewById(R.id.q5No);
        boolean q5Disagree = q5No.isChecked();


        EditText nameField = (EditText) findViewById(R.id.user_name_field);
        Editable nameEditable = nameField.getText();
        String userName = nameEditable.toString();


        /** checking if all answers are given */
        if (q1Yes.isChecked() || q2Yes.isChecked() || q3Yes.isChecked() || q4Yes.isChecked() || q5Yes.isChecked() || q1No.isChecked() || q2No.isChecked() || q3No.isChecked() || q4No.isChecked() || q5No.isChecked()) {
            Log.d("QAOD", "Answer is given");
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.toast_answer), Toast.LENGTH_SHORT).show();
            Log.d("QAOD", "Answer is Null");
        }

        displayMessage(createResultSummary(quizScore, userName, q1Agree, q2Agree, q3Agree, q4Agree, q5Agree, endMessage, hasUmbrella, hasSunFilter, hasSwissArmyKnife));

    }

    public void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.quiz_result);
        priceTextView.setText(message);
    }

    private String createResultSummary(int quizScore, String userName, boolean q1Agree, boolean q2Agree, boolean q3Agree, boolean q4Agree, boolean q5Agree, String endMessage, boolean hasUmbrella, boolean hasSunFilter, boolean hasSwissArmyKnife) {


        if (q1Agree == true) {
            quizScore = quizScore + 1;
        }

        if (q2Agree) {
            quizScore = quizScore + 1;
        }

        if (q3Agree) {
            quizScore = quizScore + 1;
        }

        if (q4Agree) {
            quizScore = quizScore + 1;
        }

        if (q5Agree) {
            quizScore = quizScore + 1;
        }

        if (quizScore < 3) {

            String wellDoneMessage = getString(R.string.score_low);
            endMessage = wellDoneMessage.toString();

        } else if (quizScore == 3) {
            String wellDoneMessage = getString(R.string.score_med);
            endMessage = wellDoneMessage.toString();
        } else {
            String wellDoneMessage = getString(R.string.score_high);
            endMessage = wellDoneMessage.toString();
        }

        if (hasUmbrella) {
            extraStuff = getString(R.string.checkbox_umbrella);
        } else {
            extraStuff = " ";
        }
        if (hasSunFilter) {
            extraStuff = extraStuff + " , " + getString(R.string.checkbox_sun_filter);
        }
        if (hasSwissArmyKnife) {
            extraStuff = extraStuff + " , " + getString(R.string.checkbox_swiss_knife);
        }

        String resultMessage = getString(R.string.congrats) + " " + userName;
        resultMessage = resultMessage + "\n" + getString(R.string.achieve) + " " + quizScore + " " + getString(R.string.points);
        resultMessage = resultMessage + "\n" + " " + endMessage;
        resultMessage = resultMessage + "\n" + " " + getString(R.string.stuff_string) + extraStuff;

        return resultMessage;
    }
}
