package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity
{

    LinearLayout layout;
    TextView nameTextView;
    TextView scoreTextView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
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

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);
        layout = (LinearLayout) findViewById(R.id.content);

        nameTextView = (TextView) layout.findViewById(R.id.name);
        scoreTextView = (TextView) layout.findViewById(R.id.score);
        listView = (ListView) layout.findViewById(R.id.listView);
        layout.addView(textView);


        //textView.setText("Could write to documents: " + WriteToExternalStorage(message));

        if (IsExternalStorageWritable())
        {
            try
            {
                SetText(textView, GetFilePath());
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        } else
            Log.i(this.toString(), "Could not write to " + GetFilePath());

    }

    private void SetText(TextView textView, String filePath) throws JSONException
    {
        String read = "";
        try
        {
            read = ReadFile(GetFilePath());
        } catch (IOException e)
        {
            e.printStackTrace();
        }



        Log.i(this.toString(), "BEFORE STUFF");


        JSONArray jsonarray = new JSONArray(read);


        final ArrayList<Word> list = new ArrayList<Word>();


        for (int i = 0; i < jsonarray.length(); i++)
        {
            JSONObject jsonobject = jsonarray.getJSONObject(i);
            String word = "";
            word = jsonobject.getString("word");
            String meaning = "";
            meaning = jsonobject.getString("meaning");
            String language = "";
            language = jsonobject.getString("language");
            String addedDate = "";
            addedDate = jsonobject.getString("added-date");
            String activationDate = "";
            activationDate = jsonobject.getString("activation-date");
            int usedCount;
            usedCount = jsonobject.getInt("used-count");
            boolean active;
            active = jsonobject.getBoolean("active");

            Word word1 = new Word(word,
                    meaning,
                    language,
                    addedDate,
                    activationDate,
                    usedCount,
                    active);

            list.add(word1);

        }

        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id)
            {
                Word word = (Word)parent.getItemAtPosition(position);
                MainActivity.CurrentWord = word;
                Toast.makeText(DisplayMessageActivity.this, "Current word has been set: " + word.getWord(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private String GetFilePath()
    {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);

        return path + "/1AndroidTest.txt";
    }


    public boolean IsExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
            return true;
        else
            return false;
    }

    public boolean IsExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;
        else
            return false;
    }

    void WriteFile(String textToWrite, String filePath)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write(WriteJSON());
            out.close();
        } catch (IOException e)
        {

        }
    }

    public String ReadFile(String path) throws IOException
    {
        String text = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        try
        {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null)
            {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            text = sb.toString();
        } finally
        {
            br.close();
        }

        return text;
    }

    public String ReadJSON(String input) throws JSONException
    {
        JSONObject json = new JSONObject(input);
        Log.i(this.toString(), json.toString());

        return "";
    }

    public String WriteJSON()
    {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();

        try
        {
            object.put("name", "Jack Hack");
            object.put("score", new Integer(200));
            object.put("current", new Double(152.32));
            object.put("nickname", "Hacker");
        } catch (JSONException e)
        {
            e.printStackTrace();
        }
        System.out.println(object);

        return object.toString();
    }


}
