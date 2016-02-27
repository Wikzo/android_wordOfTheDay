package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    public static Word CurrentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if (CurrentWord != null)
        {
            TextView wordTextView = (TextView)findViewById(R.id.word_name);
            TextView meaningTextView = (TextView)findViewById(R.id.word_meaning);
            TextView languageTextView = (TextView)findViewById(R.id.word_language);
            TextView addedDateTextView = (TextView)findViewById(R.id.word_addedDate);
            TextView activationDateTextView = (TextView)findViewById(R.id.word_activationDate);
            TextView useCountTextView = (TextView)findViewById(R.id.word_useCount);
            TextView activeTextView = (TextView)findViewById(R.id.word_active);

            wordTextView.setText(CurrentWord.getWord());
            meaningTextView.setText(CurrentWord.getMeaning());
            languageTextView.setText(CurrentWord.getLanguage());
            addedDateTextView.setText(CurrentWord.getAddedDate());
            activationDateTextView.setText(CurrentWord.getActivationDate());
            useCountTextView.setText(Integer.toString(CurrentWord.getUsedCount()));
            activeTextView.setText(String.valueOf(CurrentWord.getActive()));
        }
    }

    public void sendMessage(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.edit_message);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
