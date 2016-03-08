package net.gustavdahl.wordoftheday;


import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonFileManager
{
    private static final String dummyText = "[ { \"word\": \"DUMMY1 \", \"meaning\": \"Splittet mellem to modstridende meninger, holdninger, følelser\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 2, \"active\": true, \"index\": 0 }, { \"word\": \"DUMMY2\", \"meaning\": \"Omdiskuteret\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": true, \"index\": 0 }, { \"word\": \"DUMMY3\", \"meaning\": \"Lægger vægt på at betragte fænomener som helheder snarere end som sammensatte enkeltdele\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": false, \"index\": 0 }, { \"word\": \"DUMMY4\", \"meaning\": \"Vanemæssig, en kendsgerning\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": true, \"index\": 0 }, { \"word\": \"DUMMY5\", \"meaning\": \"Ikke-religiøst, alt hvad der ikke har med kirken og religion at gøre\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": true, \"index\": 0 }, { \"word\": \"DUMMY6\", \"meaning\": \"Nerver og psykiske lidelser\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": false, \"index\": 0 }, { \"word\": \"DUMMY7\", \"meaning\": \"Hul, utæthed, læk\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": false, \"index\": 0 }, { \"word\": \"DUMMY8\", \"meaning\": \"Bager med fine kager og desserter\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": true, \"index\": 0 }, { \"word\": \"DUMMY9\", \"meaning\": \"Meaning\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": false, \"index\": 0 }, { \"word\": \"DUMMY10\", \"meaning\": \"Meaning test\", \"language\": \"DK\", \"addedDate\": \"08-03-2016\", \"activationDate\": \"08-03-2016\", \"usedCount\": 0, \"active\": false, \"index\": 0 } ]";

    public static List<Word> InitializeJsonWords() throws JSONException
    {
        String read = "";
        try
        {
            read = ReadFile(GetFilePath());

            if (read == null || read == "")
            {
                WriteDummyFile();
                read = dummyText;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        JSONArray jsonarray = new JSONArray(read);
        List<Word> words = new ArrayList<Word>();

        for (int i = 0; i < jsonarray.length(); i++)
        {
            JSONObject jsonobject = jsonarray.getJSONObject(i);

            String word = "";
            if (jsonobject.has(Word.JsonWord))
                word = jsonobject.getString(Word.JsonWord);

            String meaning = "";
            if (jsonobject.has(Word.JsonMeaning))
                meaning = jsonobject.getString(Word.JsonMeaning);

            String language = "";
            if (jsonobject.has(Word.JsonLanguage))
                language = jsonobject.getString(Word.JsonLanguage);

            String addedDate = "";
            if (jsonobject.has(Word.JsonAddedDate))
                addedDate = jsonobject.getString(Word.JsonAddedDate);

            String activationDate = "";
            if (jsonobject.has(Word.JsonActivationDate))
                activationDate = jsonobject.getString(Word.JsonActivationDate);

            int usedCount = 0;
            if (jsonobject.has(Word.JsonUsedCount))
                usedCount = jsonobject.getInt(Word.JsonUsedCount);

            boolean active = false;
            if (jsonobject.has(Word.JsonActive))
                active = jsonobject.getBoolean(Word.JsonActive);

            int priority = i;
            if (jsonobject.has(Word.JsonPriority))
                priority = jsonobject.getInt(Word.JsonPriority);

            String notes = "";
            if (jsonobject.has(Word.JsonNotes))
                addedDate = jsonobject.getString(Word.JsonNotes);


            Word newWord = new Word(
                    word,
                    meaning,
                    language,
                    addedDate,
                    activationDate,
                    usedCount,
                    active,
                    i,
                    notes);

            words.add(newWord);
        }

        return words;
    }

    public static String ReadFile(String path) throws IOException
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

    public static String GetFilePath()
    {
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS) + "/MyWords.json";
    }

    public static boolean WriteJsonArray() throws JSONException
    {
        return WriteFile(ConvertWordsToJsonArray(), JsonFileManager.GetFilePath());
    }

    public static boolean WriteDummyFile()
    {
        return WriteFile(dummyText, GetFilePath());
    }

    public static String ConvertWordsToJsonArray() throws JSONException
    {
        JSONArray jsonArray = new JSONArray();

        for (Word word : Word.getAllWordObjects())
        {
            JSONObject json = word.ToJsonObject();
            jsonArray.put(json);
        }

        return jsonArray.toString();
    }

    private static boolean WriteFile(String textToWrite, String filePath)
    {
        try
        {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write(textToWrite);
            out.close();
            return true;
        } catch (IOException e)
        {
            return false;
        }
    }

    public static boolean IsExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state))
            return true;
        else
            return false;
    }

    public static boolean IsExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
            return true;
        else
            return false;
    }

}
