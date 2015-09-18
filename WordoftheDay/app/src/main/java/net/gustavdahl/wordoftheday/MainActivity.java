package net.gustavdahl.wordoftheday;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView currentWord;
    TextView currentMeaning;

    EditText newWord;
    EditText newMeaning;

    List<Word> WordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentWord = (TextView) findViewById(R.id.currentWord);
        currentMeaning = (TextView) findViewById(R.id.currentMeaning);

        newWord = (EditText)findViewById(R.id.newWord);
        newMeaning = (EditText)findViewById(R.id.newMeaning);

        WordList = new ArrayList<Word>();


        //PopulateDatabase();
        UpdateWordList();


        NextWord();

    }

    public void ClearDatabase(View view)
    {
        DatabaseHelper db = new DatabaseHelper(this);
        db.deleteAllWords();

        UpdateWordList();

    }

    public void PopulateDatabase()
    {
        DatabaseHelper db = new DatabaseHelper(this);

        // Inserting words
        Log.d("Insert: ", "Inserting ..");
        db.addWords(new Word("Ambivalent", "Splittet mellem to modstridende meninger, holdninger, følelser"));
        db.addWords(new Word("Kontroversiel", "Omdiskuteret"));
        db.addWords(new Word("Holisme", "Lægger vægt på at betragte fænomener som helheder snarere end som sammensatte enkeltdele"));
        db.addWords(new Word("Test", "asdasd"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");

        List<Word> words = db.getAllWords();

        for (Word cn : words)
        {
            String log = "Id: " + cn.getId() + " ,Word: " + cn.getTheWord() + " ,Meaning: " + cn.getTheMeaning();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }


    }

    public void UpdateWordList()
    {
        DatabaseHelper db = new DatabaseHelper(this);
        WordList = db.getAllWords();

        System.out.println(WordList.size());


        if (WordList.size() > 0)
        {
            Word w = WordList.get(count);

            currentWord.setText(w.getTheWord());
            currentMeaning.setText(w.getTheMeaning());
        }
        else
        {
            currentWord.setText("[no words in database]");
            currentMeaning.setText("[no meanings in database]");
        }
    }

    public void InsertNewWord(View view)
    {
        //Username = usernameEditText.getText().toString();

        String newWordText = newWord.getText().toString();
        String newMeaningText = newMeaning.getText().toString();

        if (newWordText.matches("") || newMeaningText.matches(""))
        {
            Toast.makeText(this, "Need to write word and meaning!", Toast.LENGTH_SHORT).show();
            return;
        }

            DatabaseHelper db = new DatabaseHelper(this);

            db.addWords(new Word(newWordText, newMeaningText));

        UpdateWordList();
    }

    void NextWord()
    {
        //System.out.println(count);

        if (WordList.size() == 0)
            return;

        count++;


        if (count > WordList.size()-1)
            count = 0;

        Word w = WordList.get(count);

        currentWord.setText(w.getTheWord());
        currentMeaning.setText(w.getTheMeaning());
    }

    public void ChangeWord(View view)
    {
        NextWord();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
