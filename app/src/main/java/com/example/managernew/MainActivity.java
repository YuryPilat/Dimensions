package com.example.managernew;

import android.app.Activity;
import android.content.SearchRecentSuggestionsProvider;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

    private final String DB_NAME = "danyquest.db";
    private final String NEXT_QUEST = "NextQuest";
    public TextView textView;
    private DBManager dbManager;
    private Button btnFirst;
    private Button btnSecond;
    private Button btnThird;
    private Button btnRead;
    private ImageView imageView;
    private int nextQid1;
    private int nextQid2;
    private int nextQid3;
    private int nextQid4;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnFirst = (Button) findViewById(R.id.btnFirst);
        btnSecond = (Button) findViewById(R.id.btnSecond);
        btnThird = (Button) findViewById(R.id.btnThird);
        btnRead = (Button)findViewById(R.id.btnRead);
        imageView = (ImageView)findViewById(R.id.imageView);
        dbManager = new DBManager(this, DB_NAME);

        superCoolMethodForWork(Root.getInteger(NEXT_QUEST,1));
   }

    public String getQuest (int qid){
        Cursor cursorWithData = dbManager.getDataByParams(R.string.DB_CREATE_TABLE1, R.string.COLUMN_ID, qid);
        int quest = cursorWithData.getColumnIndex(Root.getStringRes(R.string.COLUMN_QUESTIONS));
        String questInfo = cursorWithData.getString(quest);
        return questInfo;
    }

    public String getAnswer (int anid) {
        if (anid <= 0) return null;
        Cursor cursorWithAnswer = dbManager.getDataByParams(R.string.DB_CREATE_TABLE2, R.string.COLUMN_ID, anid);
        int answer = cursorWithAnswer.getColumnIndex(Root.getStringRes(R.string.COLUMN_ANSWER));
        String answerInfo = cursorWithAnswer.getString(answer);
        return answerInfo;
    }

    public void superCoolMethodForWork (int nextQuestionId) {
        int answerId;
        int count = 0;
        int nextQid;
        int background;
        String question = getQuest(nextQuestionId);
        textView.setText(question);
        Cursor dataFromTable3 = dbManager.getDataByParams(R.string.DB_CREATE_TABLE3, R.string.COLUMN_QID, nextQuestionId);
        if (dataFromTable3.moveToFirst()) {
            do {
                count++;
                answerId = dataFromTable3.getInt(dataFromTable3.getColumnIndex(Root.getStringRes(R.string.COLUMN_ANID)));
                nextQid = dataFromTable3.getInt(dataFromTable3.getColumnIndex(Root.getStringRes(R.string.COLUMN_NEXTQID)));
                background = dataFromTable3.getInt(dataFromTable3.getColumnIndex(Root.getStringRes(R.string.COLUMN_BACKGRND)));
                setAnswerToButton(count, answerId, nextQid);
                setBackground(background);
            }
            while (dataFromTable3.moveToNext());
            dataFromTable3.close();
        }

        for (int i=count; i<4; i++){
            count++;
            setAnswerToButton(count, 0, 0);
        }
    }

   private void setBackground(int background) {
        switch (background) {
            case 1:
                imageView.setImageResource(R.drawable.darkage);
                break;
            case 2:
                imageView.setImageResource(R.drawable.steampunk);
                break;
            case 3:
                imageView.setImageResource(R.drawable.middleage);
                break;
            case 4:
                imageView.setImageResource(R.drawable.futurism);
                break;
        }
    }

    public void setAnswerToButton(int count, int answerId, int nextQid) {
        switch (count) {
            case 1:
                setTextToButton(btnFirst, getAnswer(answerId));
                nextQid1 = nextQid;
                break;
            case 2:
                setTextToButton(btnSecond, getAnswer(answerId));
                nextQid2 = nextQid;
                break;
            case 3:
                setTextToButton(btnThird, getAnswer(answerId));
                nextQid3 = nextQid;
                break;
            case 4:
                setTextToButton(btnRead, getAnswer(answerId));
                nextQid4 = nextQid;
                break;
        }
    }

    private void setTextToButton(Button button, String answer) {
        if (answer==null) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
            button.setText(answer);
        }
    }

    public void onClick(View view) {
        int currentNextQid = 0;
        switch (view.getId()) {
            case R.id.btnFirst:
                currentNextQid = nextQid1;
                break;
            case R.id.btnSecond:
                currentNextQid = nextQid2 ;
                break;
            case R.id.btnThird:
                currentNextQid = nextQid3;
                break;
            case R.id.btnRead:
                currentNextQid = nextQid4 ;
                break;
        }
        Root.setInteger(NEXT_QUEST, currentNextQid);
        superCoolMethodForWork(currentNextQid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}