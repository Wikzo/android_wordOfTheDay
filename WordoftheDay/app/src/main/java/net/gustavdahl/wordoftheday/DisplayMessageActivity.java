package net.gustavdahl.wordoftheday;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DisplayMessageActivity extends AppCompatActivity
{

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
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.content);
        layout.addView(textView);

        //textView.setText("Could write to documents: " + WriteToExternalStorage(message));

        if (IsExternalStorageWritable())
        {
            WriteFile(message, GetFilePath());
            try
            {
                SetText(textView, GetFilePath());
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        else
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

        JSONObject json = new JSONObject(read);
        String name = json.getString("name");
        int score = json.getInt("score");

        textView.setText("Name: " + name + "; Score: " + score);
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
