package net.gustavdahl.wordoftheday;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int count = 0;
    TextView quoteText;
    TextView personText;
    ArrayList<Word> WordList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteText = (TextView) findViewById(R.id.newWord);
        personText = (TextView) findViewById(R.id.wordMeaning);
        WordList = new ArrayList<Word>();

        RelativeLayout touch = (RelativeLayout) findViewById(R.id.touch);


        DatabaseTest();



    }

    public void DatabaseTest()
    {
        System.out.println("database stuff");

        DatabaseHelper db = new DatabaseHelper(this);

        // Inserting Contacts
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

        CreateWords(words);

    }

    public void CreateWords(List<Word> database)
    {
        for (Word w : database)
        {
            WordList.add(w);
        }
    }

    public void ChangeWord(View view)
    {
        //System.out.println(count);

        count++;


        if (count > WordList.size()-1)
            count = 0;

        Word w = WordList.get(count);

        quoteText.setText(w.getTheWord());
        personText.setText(w.getTheMeaning());
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
