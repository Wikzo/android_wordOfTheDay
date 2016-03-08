// edit word
package net.gustavdahl.wordoftheday;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONException;

public class EditWord extends AppCompatActivity
{

    private TextView wordTextView;
    private TextView meaningTextView;
    private TextView languageTextView;
    private TextView addedDateTextView;
    private TextView activationDateTextView;
    private TextView useCountTextView;
    private Switch activeTextView;
    private TextView indexTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_word);

        InitializeTextViews();

        UpdateTextViews();
    }

    private void InitializeTextViews()
    {
        wordTextView = (TextView)findViewById(R.id.word_name);
        meaningTextView = (TextView)findViewById(R.id.word_meaning);
        languageTextView = (TextView)findViewById(R.id.word_language);
        addedDateTextView = (TextView)findViewById(R.id.word_addedDate);
        activationDateTextView = (TextView)findViewById(R.id.word_activationDate);
        useCountTextView = (TextView)findViewById(R.id.word_useCount);
        activeTextView = (Switch)findViewById(R.id.word_active);
        indexTextView = (TextView)findViewById(R.id.word_index);
    }

    private void UpdateTextViews()
    {
        Word selectedWord = Word.getSelectedWord();

        if (selectedWord != null)
        {
            wordTextView.setText(selectedWord.getWord());
            meaningTextView.setText(selectedWord.getMeaning());
            languageTextView.setText(selectedWord.getLanguage());
            addedDateTextView.setText(selectedWord.getAddedDate());
            activationDateTextView.setText(selectedWord.getActivationDate());
            useCountTextView.setText(Integer.toString(selectedWord.getUsedCount()));
            activeTextView.setChecked(selectedWord.getActive());
            indexTextView.setText(String.valueOf(selectedWord.getIndex()));
        }
    }

    public void UpdateAndSaveCurrentWord(View view) throws JSONException
    {
        Word selectedWord = Word.getSelectedWord();

        if (selectedWord != null)
        {
            selectedWord.setWord(wordTextView.getText().toString());
            selectedWord.setMeaning(meaningTextView.getText().toString());
            selectedWord.setLanguage(languageTextView.getText().toString());
            selectedWord.setAddedDate(addedDateTextView.getText().toString());
            selectedWord.setActivationDate(activationDateTextView.getText().toString());
            selectedWord.setUsedCount(Integer.parseInt(useCountTextView.getText().toString()));
            selectedWord.setActive(activeTextView.isChecked());
            selectedWord.setIndex(Integer.parseInt(indexTextView.getText().toString()));
        }

        Word.SaveAllWords();
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        try
        {
            UpdateAndSaveCurrentWord(null);
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
