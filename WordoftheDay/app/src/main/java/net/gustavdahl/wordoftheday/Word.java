package net.gustavdahl.wordoftheday;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Word
{
    private static ArrayList<Word> allWordObjects = new ArrayList<Word>();
    private static ArrayList<String> allWordsAlreadyAdded = new ArrayList<String>();

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
        if (!allWordsAlreadyAdded.contains(word.getWord()))
        {
            allWordsAlreadyAdded.add(word.getWord());
            getAllWordObjects().add(word);
        }
    }

    public static ArrayList<Word> getAllWordObjects()
    {
        return allWordObjects;
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
}
