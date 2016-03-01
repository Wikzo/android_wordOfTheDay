package net.gustavdahl.wordoftheday;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Word
{
    private static Word SelectedWord;

    private static List<Word> allWordObjects = new ArrayList<Word>();
    private static List<String> allWordsAlreadyAdded = new ArrayList<String>();

    public static final String JsonWord = "word";
    public static final String JsonMeaning = "meaning";
    public static final String JsonLanguage = "language";
    public static final String JsonAddedDate = "added-date";
    public static final String JsonActivationDate = "activation-date";
    public static final String JsonUsedCount = "used-count";
    public static final String JsonActive = "active";
    public static final String JsonIndex = "index";

    private String word;
    private String meaning;
    private String language;
    private String addedDate;
    private String activationDate;
    private int usedCount;
    private boolean active;
    private int index;

    public Word(String word,
                           String meaning,
                           String language,
                           String addedDate,
                           String activationDate,
                           int usedCount,
                           boolean active, int index)
    {
        this.word = word;
        this.meaning = meaning;
        this.language = language;
        this.addedDate = addedDate;
        this.activationDate = activationDate;
        this.usedCount = usedCount;
        this.active = active;
        this.index = index;


        // TODO: be able to handle null/empty data!
    }

    public static void AddWord(Word word)
    {
        allWordObjects.add(word);

        // TODO: error check that no duplicates exist
        /*if (!allWordsAlreadyAdded.contains(word.getWord()))
        {
            allWordsAlreadyAdded.add(word.getWord());
            allWordObjects.add(word);
        }*/
    }

    public static List<Word> getAllWordObjects()
    {
        return allWordObjects;
    }

    public static Word getSelectedWord()
    {
        if (SelectedWord == null)
        {
            if (allWordObjects.size() > 0)
                return allWordObjects.get(0);
            else
            {
                Log.i("ERROR", "ERROR! No words avilable for selection");
                return null;
            }
        }
        else
            return SelectedWord;
    }

    public static void setSelectedWord(Word selectedWord)
    {
        if (selectedWord == null)
            Log.i("ERROR", "ERROR. Cannot set selected word to null!");
        else
            SelectedWord = selectedWord;
    }

    public JSONObject ToJsonObject() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JsonWord, this.word);
        json.put(JsonMeaning, this.meaning);
        json.put(JsonLanguage, this.language);
        json.put(JsonAddedDate, this.addedDate);
        json.put(JsonActivationDate, this.activationDate);
        json.put(JsonUsedCount, this.usedCount);
        json.put(JsonActive, this.active);
        json.put(JsonIndex, this.index);

        return json;
    }

    public static boolean SaveAllWords() throws JSONException
    {
        return JsonFileReader.WriteJsonArray();
    }

    public static void LoadAllWords() throws JSONException
    {
       // if
        allWordObjects = new ArrayList<Word>(JsonFileReader.InitializeJsonWords());
    }

    public static List<Word> GetActiveWords()
    {
        List<Word> activeWords = new ArrayList<Word>();
        for (Word word : allWordObjects)
        {
            if (word.active)
                activeWords.add(word);
        }

        return activeWords;
    }

    @Override
    public String toString()
    {
        return this.word;
    }

    public String getWord()
    {
        return word;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public String getMeaning()
    {
        return meaning;
    }

    public void setMeaning(String meaning)
    {
        this.meaning = meaning;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getAddedDate()
    {
        return addedDate;
    }

    public void setAddedDate(String addedDate)
    {
        this.addedDate = addedDate;
    }

    public String getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate(String activationDate)
    {
        this.activationDate = activationDate;
    }

    public int getUsedCount()
    {
        return usedCount;
    }

    public void setUsedCount(int usedCount)
    {
        this.usedCount = usedCount;
    }

    public boolean getActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
}
