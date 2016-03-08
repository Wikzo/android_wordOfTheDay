package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

public class WordListActivity extends AppCompatActivity
{

    LinearLayout layout;
    ListView listView;

    public static void SetCurrentWord(Word selectedWord)
    {
        if (selectedWord != null)
            Word.setSelectedWord(selectedWord);
        else
            Word.setSelectedWord(Word.GetActiveWords().get(0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_word_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Word.AllWords.clear();

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        layout = (LinearLayout) findViewById(R.id.content);

        listView = (ListView) layout.findViewById(R.id.listView);
        layout.addView(textView);


        //textView.setText("Could write to documents: " + WriteToExternalStorage(message));

        if (JsonFileReader.IsExternalStorageWritable())
        {
            try
            {
                SetText(textView, JsonFileReader.GetFilePath());
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        } else
            Log.i(this.toString(), "Could not write to " + JsonFileReader.GetFilePath());

    }

    private void SetText(TextView textView, String filePath) throws JSONException
    {

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, Word.getAllWordObjects());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id)
            {
                Word word = (Word) parent.getItemAtPosition(position);

                word.setActive(!word.getActive());
                boolean isActive = word.getActive();

                Toast.makeText(
                        WordListActivity.this,
                        word.getWord() + " is active: " + word.getActive(),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void UpdateJsonFile(View view) throws JSONException
    {
        boolean success = Word.SaveAllWords();
        String text = "Words succesfully saved in: " + JsonFileReader.GetFilePath();

        if (!success)
            text = "Words NOT saved in: " + JsonFileReader.GetFilePath();

        Toast.makeText(
                WordListActivity.this,
                text,
                Toast.LENGTH_SHORT).show();

        //System.out.println(JsonFileReader.ConvertWordsToJsonArray());
    }


}
