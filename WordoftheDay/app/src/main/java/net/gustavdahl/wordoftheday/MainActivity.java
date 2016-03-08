package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

   private TextView wordTextView;
   private TextView meaningTextView;
   private TextView languageTextView;
   private TextView addedDateTextView;
   private TextView activationDateTextView;
   private TextView useCountTextView;
   private TextView activeTextView;
   private TextView indexTextView;

    private void InitializeTextViews()
    {
        wordTextView = (TextView)findViewById(R.id.word_name);
        meaningTextView = (TextView)findViewById(R.id.word_meaning);
        languageTextView = (TextView)findViewById(R.id.word_language);
        addedDateTextView = (TextView)findViewById(R.id.word_addedDate);
        activationDateTextView = (TextView)findViewById(R.id.word_activationDate);
        useCountTextView = (TextView)findViewById(R.id.word_useCount);
        activeTextView = (TextView)findViewById(R.id.word_active);
        indexTextView = (TextView)findViewById(R.id.word_index);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTextViews();
        CheckIfJsonFileExists();

        try
        {
            Word.LoadAllWords();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Word.setSelectedWord(Word.GetActiveWords().get(0));
        UpdateTextViews();
    }

    private void CheckIfJsonFileExists()
    {
        File f = new File(JsonFileReader.GetFilePath());
        if(!f.exists())
            JsonFileReader.WriteDummyFile();
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
            selectedWord.setActive(Boolean.parseBoolean(activeTextView.getText().toString()));
            selectedWord.setIndex(Integer.parseInt(indexTextView.getText().toString()));
        }

        Word.SaveAllWords();
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
            activeTextView.setText(String.valueOf(selectedWord.getActive()));
            indexTextView.setText(String.valueOf(selectedWord.getIndex()));
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
        Word.getSelectedWord().setUsedCount(Word.getSelectedWord().getUsedCount() + 1);
        UpdateTextViews();
    }

    public void WriteDummyFile(View view)
    {
        boolean success = JsonFileReader.WriteDummyFile();

        String text = "Wrote dummy file in:" + JsonFileReader.GetFilePath();

        if (!success)
            text = "Could NOT write dummy file in: " + JsonFileReader.GetFilePath();

        Toast.makeText(
                MainActivity.this,
                text,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        try
        {
            UpdateAndSaveCurrentWord(null);

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
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
