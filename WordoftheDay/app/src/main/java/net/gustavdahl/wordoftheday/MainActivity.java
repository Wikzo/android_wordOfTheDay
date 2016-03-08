// main

package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;

public class MainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    private TextView wordTextView;
    private TextView meaningTextView;
    private RatingBar usedCountStars;


    private void InitializeTextViews()
    {
        wordTextView = (TextView)findViewById(R.id.word_name);
        meaningTextView = (TextView)findViewById(R.id.word_meaning);
        usedCountStars = (RatingBar)findViewById(R.id.usedCountStars);
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

        Word.setSelectedWord(Word.allActiveWordObjects.get(0));
        UpdateTextViews();
    }

    private void CheckIfJsonFileExists()
    {
        File f = new File(JsonFileManager.GetFilePath());
        if(!f.exists())
            JsonFileManager.WriteDummyFile();
    }

    private void UpdateTextViews()
    {
        Word selectedWord = Word.getSelectedWord();

        if (selectedWord != null)
        {
            wordTextView.setText(selectedWord.getWord());
            meaningTextView.setText(selectedWord.getMeaning());

            usedCountStars.setRating(Math.min(3, selectedWord.getUsedCount()));
        }
    }

    public void CheckWordHasBeenUsed(View view) throws JSONException
    {
        Word.getSelectedWord().incrementUsedCount();

        Word.SaveAllWords();
        UpdateTextViews();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        UpdateTextViews();
    }

    public void ShowWordList(View view)
    {
        Intent intent = new Intent(this, WordListActivity.class);
       // String message = "test_message";
       //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void EditCurrentWord(View view)
    {
        //Word.getSelectedWord().setUsedCount(Word.getSelectedWord().getUsedCount() + 1);
        //UpdateTextViews();

        Intent intent = new Intent(this, EditWord.class);
        //String message = "test_message";
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void GetNextActiveWord(View view)
    {
        Word.setSelectedWord(Word.GetNextWord());
        UpdateTextViews();
    }

    public void GetPreviousActiveWord(View view)
    {
        Word.setSelectedWord(Word.GetPreviousWord());
        UpdateTextViews();
    }

    public void WriteDummyFile(View view)
    {
        boolean success = JsonFileManager.WriteDummyFile();

        String text = "Wrote dummy file in:" + JsonFileManager.GetFilePath();

        if (!success)
            text = "Could NOT write dummy file in: " + JsonFileManager.GetFilePath();

        Toast.makeText(
                MainActivity.this,
                text,
                Toast.LENGTH_SHORT).show();
    }

}
