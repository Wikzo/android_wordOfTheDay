package net.gustavdahl.wordoftheday;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    int count = 0;
    TextView currentWord;
    TextView currentMeaning;

    EditText newWord;
    EditText newMeaning;

    List<Word> WordList;

    //private static final String TAG = MainActivity.class.getSimpleName();
    public static final String TAG = "MyDebug";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentWord = (TextView) findViewById(R.id.currentWord);
        currentMeaning = (TextView) findViewById(R.id.currentMeaning);

        newWord = (EditText)findViewById(R.id.newWord);
        newMeaning = (EditText)findViewById(R.id.newMeaning);

        WordList = new ArrayList<Word>();


        try
        {
            saveText();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


        //LoadCSVData();


    }

    void InternalAGain()
    {
        // Create a file in the Internal Storage

        String fileName = "MyFile";
        String content = "hello world";

        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        // external
    }

    void ExternalAGain()
    {
        String content = "hello world";
        File file;
        FileOutputStream outputStream;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "MyCache");

            outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final char SEPARATOR = ';';

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    void WriteInternal()
    {
        String filename = "myfile.csv";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void WriteCSV() throws IOException
    {

        boolean b = isExternalStorageAvailable();
        Log.d(TAG, String.valueOf(b));

        String text = "JA DA JA DA";
        String FILE_NAME = "file.txt";
        if (isExternalStorageAvailable() && isExternalStorageReadOnly()) {
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            File file = new File(baseDir, FILE_NAME);
            FileWriter writer = null;
            try {
                writer = new FileWriter(file);
                writer.write(text.toString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /*

        String sdpath, sd1path, usbdiskpath, sd0path;
        if (new File("/storage/extSdCard/").exists()) {
            sdpath="/storage/extSdCard/";
            Log.i("Sd Cardext Path", sdpath);
        }
        if (new File("/storage/sdcard1/").exists()) {
            sd1path="/storage/sdcard1/";
            Log.i("Sd Card1 Path", sd1path);
        }
        if (new File("/storage/usbcard1/").exists()) {
            usbdiskpath="/storage/usbcard1/";
            Log.i("USB Path", usbdiskpath);
        }
        if (new File("/storage/sdcard0/").exists()) {
            sd0path="/storage/sdcard0/";
            Log.i("Sd Card0 Path", sd0path);
        }

        //

        String csv = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        CSVWriter writer = new CSVWriter(new FileWriter(csv));

        List<String[]> data = new ArrayList<String[]>();
        data.add(new String[] {"India", "New Delhi"});
        data.add(new String[] {"United States", "Washington D.C"});
        data.add(new String[] {"Germany", "Berlin"});

        writer.writeAll(data);

        writer.close();*/
    }


    public void saveText() throws IOException
    {
        // text file: https://stackoverflow.com/questions/27415074/android-save-to-file-txt-appending
        try {
            //open file for writing
            OutputStreamWriter out1 = new OutputStreamWriter(openFileOutput("save.csv", MODE_PRIVATE));
            out1.write("Word1,Meaning1,UsedCounter1,NextDate1,AddedDate1,Notes1,Category1,Language1");
            out1.close();


            Toast.makeText(this, out1.toString() ,Toast.LENGTH_LONG).show();


            OutputStreamWriter out = new OutputStreamWriter(openFileOutput("save.csv", Context.MODE_APPEND));
            //OutputStreamWriter out = new OutputStreamWriter(openFileOutput("save.csv", MODE_PRIVATE));


            // IN CSV:
            InputStreamReader input = new InputStreamReader(openFileInput("save.csv"));
            CSVReader reader = new CSVReader(input);
            List<String[]> csvBody = reader.readAll();
            csvBody.get(0)[0]="WordREPLACED";
            csvBody.get(0)[1]="MeaningREPLACED";
            reader.close();



            // OUT CSV:
            CSVWriter writer = new CSVWriter(out, ',');
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
            ///


            //write information to file
            //String text2 = "HJSA SAD ASD SD";
            //out.write(text2);
            //out.write('\n');

            //close file

            out.close();


            Toast.makeText(this,"Text Saved",Toast.LENGTH_LONG).show();

        } catch (java.io.IOException e) {
            //if caught
            Toast.makeText(this, "Text Could not be added",Toast.LENGTH_LONG).show();
        }
    }


void WriteNewCSVFile()
{
    File folder = new File(Environment.getExternalStorageDirectory()
            + "/Folder");

    boolean var = false;
    if (!folder.exists())
        var = folder.mkdir();

    System.out.println("" + var);


    final String filename = folder.toString() + "/" + "Test.csv";


}


    public void updateCSV(String replace, int row, int col) throws IOException {

        String filename = "CasdasdSVStuffmyfile.csv";
        String string = "Hello world!asdasdasd";
        FileOutputStream outputStream;


        InputStream in = getAssets().open("csvTest.csv");
        CSVReader reader = new CSVReader(new InputStreamReader(in));


        CSVWriter writer = new CSVWriter(new FileWriter("yourfile.csv"), '\t');
        // feed in your array (or convert your data to an array)
        String[] entries = "first#second#third".split("#");
        writer.writeNext(entries);
        writer.close();


        /// ORIGINAL:

        List<String[]> csvBody = reader.readAll();
        csvBody.get(row)[col]=replace;
        reader.close();

        /*//CSVWriter writer = new CSVWriter(new FileWriter(output),SEPARATOR,' ');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();*/


        // mine:

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);

            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    void UpdateCSV()
    {
        try
        {
            InputStream input = getAssets().open("csvTest.csv");


            CSVHelper.UpdateCSV(input, "lol", 1, 1);


            //Log.d(TAG,s);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void LoadCSVData()
    {

        try
        {
            InputStream input = getAssets().open("csvTest.csv");

            List<Word> w = CSVHelper.ReadCSVFile(input, 3, 0);

            Log.d(TAG,w.get(1).ToString(true));

            currentWord.setText(w.get(1).getWord());

            //Log.d(TAG,s);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }




    public void UpdateWordList()
    {

        System.out.println(WordList.size());


        if (WordList.size() > 0)
        {
            Word w = WordList.get(count);

            currentWord.setText(w.getWord());
            currentMeaning.setText(w.getMeaning());
        } else
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


        UpdateWordList();
    }

    void NextWord()
    {
        //System.out.println(count);

        if (WordList.size() == 0)
            return;

        count++;


        if (count > WordList.size() - 1)
            count = 0;

        Word w = WordList.get(count);

        currentWord.setText(w.getWord());
        currentMeaning.setText(w.getMeaning());
    }

    public void ChangeWord(View view)
    {
        NextWord();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
