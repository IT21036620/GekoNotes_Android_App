package com.geckolabs.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.geckolabs.dao.QuizDAO;

public class WrittenAnsView extends AppCompatActivity {

    TextView queDisplay;
    Button submit;
    EditText userAns;
    EditText correctAns;
    ImageButton editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_written_ans_view);

        QuizDAO db = new QuizDAO(this);

        queDisplay = findViewById(R.id.viewQuestion);
        submit = findViewById(R.id.viewAns);
        userAns = findViewById(R.id.writtenAns);
        correctAns = findViewById(R.id.correctAns);
        editButton = findViewById(R.id.editAns);

        Integer questionId;
        Intent intent = getIntent();
        questionId = intent.getIntExtra("queID",0);
        QuestionModel questionModel = db.getAQuizQuestion(questionId);
        Log.d("Question Id", String.valueOf(questionId));
        Log.d("QuestionType", questionModel.getqType());
        Log.d("Question", questionModel.getqText());

        queDisplay.setText(questionModel.getqText());

        AnswerModel answerModel = db.getAnswerForQuestion(questionId);
        Integer ansID = answerModel.getAnsID();
        //String answer = String.valueOf(answerModel.getqAns());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctAns.setText(answerModel.getqAns());
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WrittenAnsView.this, EditQuizAns.class);
                intent.putExtra("answerID",ansID);
                Log.d("WrittenAnsIDTEST", String.valueOf(ansID));
                startActivity(intent);
            }
        });
    }
}