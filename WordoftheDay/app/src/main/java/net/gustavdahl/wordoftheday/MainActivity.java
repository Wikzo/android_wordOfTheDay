package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    public static Word CurrentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            WordListActivity.InitializeJsonWords();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        WordListActivity.SetCurrentWord();
        UpdateCurrentWord();
    }

    private void UpdateCurrentWord()
    {
        if (CurrentWord != null)
        {
            TextView wordTextView = (TextView)findViewById(R.id.word_name);
            TextView meaningTextView = (TextView)findViewById(R.id.word_meaning);
            TextView languageTextView = (TextView)findViewById(R.id.word_language);
            TextView addedDateTextView = (TextView)findViewById(R.id.word_addedDate);
            TextView activationDateTextView = (TextView)findViewById(R.id.word_activationDate);
            TextView useCountTextView = (TextView)findViewById(R.id.word_useCount);
            TextView activeTextView = (TextView)findViewById(R.id.word_active);
            TextView indexTextView = (TextView)findViewById(R.id.word_index);

            wordTextView.setText(CurrentWord.getWord());
            meaningTextView.setText(CurrentWord.getMeaning());
            languageTextView.setText(CurrentWord.getLanguage());
            addedDateTextView.setText(CurrentWord.getAddedDate());
            activationDateTextView.setText(CurrentWord.getActivationDate());
            useCountTextView.setText(Integer.toString(CurrentWord.getUsedCount()));
            activeTextView.setText(String.valueOf(CurrentWord.getActive()));
            indexTextView.setText(String.valueOf(CurrentWord.getIndex()));
        }
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, WordListActivity.class);
        String message = "test_message";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void EditCurrentWord(View view)
    {
        CurrentWord.setUsedCount(CurrentWord.getUsedCount() + 1);

        UpdateCurrentWord();


    }
}
