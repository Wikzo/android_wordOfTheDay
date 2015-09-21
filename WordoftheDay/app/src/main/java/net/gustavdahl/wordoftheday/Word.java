package net.gustavdahl.wordoftheday;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wikzo on 18-09-2015.
 */
public class Word
{

    // Word1,Meaning1,UsedCounter1,NextDate1,AddedDate1,Notes1,Category1,Language1

    private String word;
    private String meaning;
    private String usedCounter;
    private String nextDate;
    private String addedDate;
    private String notes;
    private String category;
    private String language;
    private String active;

    private int ID;

    public Word(int id, String dataWithDelimiters, String delimiterCharacter)
    {
        String delim = "[" + delimiterCharacter + "]";
        String[] splittedData = dataWithDelimiters.split(delim);


        //Log.d(MainActivity.TAG, "LENGTH: " + String.valueOf(splittedData.length));

        this.word = splittedData[0];
        this.meaning = splittedData[1];
        this.usedCounter = splittedData[2];
        this.nextDate = splittedData[3];
        this.addedDate = splittedData[4];
        this.notes = splittedData[5];
        this.category = splittedData[6];
        this.language = splittedData[7];

        if (splittedData.length > 8)
            this.active = splittedData[8];
        else
            this.active = "0";

        this.ID = id;

    }

    public Word(String word, String meaning)
    {
        this.word = word;
        this.meaning = meaning;
        this.usedCounter = "0";
        this.nextDate = "0";
        this.addedDate = "0";
        this.notes = "";
        this.category = "";
        this.language = "DK";
        this.active = "0";
        this.ID = 0;
    }

    public Word(){}

    public String ToString(boolean usePrefixes)
    {
        String s = "";

        String pWord, pMeaning, pUsedCounter, pNextDate, pAddedDate, pNotes, pCategory, pLanguage, pActive, pID;
        pWord = pMeaning= pUsedCounter = pNextDate = pAddedDate = pNotes = pCategory = pLanguage = pActive = pID = "";

        if (usePrefixes)
        {
            String equalSign = " =";

            pWord = "Word" + equalSign;
            pMeaning = "Meaning" + equalSign;
            pUsedCounter = "UsedCounter" + equalSign;
            pNextDate = "NextDate" + equalSign;
            pAddedDate = "AddedDate" + equalSign;
            pCategory = "Category" + equalSign;
            pLanguage = "Language" + equalSign;
            pActive = "Active" + equalSign;
            pID = "ID" + equalSign;
        }

        s = String.format("%S %s; %S %s; %S %s; %S %s; %S %s; %S %s; %S %s; %S %s; %S %s; %S %s",
                pWord, this.word,
                pMeaning, this.meaning,
                pUsedCounter, this.usedCounter,
                pNextDate, this.nextDate,
                pAddedDate, this.addedDate,
                pNotes, this.notes,
                pCategory, this.category,
                pLanguage, this.language,
                pActive, this.active,
                pID, this.ID);

        return s;

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

    public String getUsedCounter()
    {
        return usedCounter;
    }

    public void setUsedCounter(String usedCounter)
    {
        this.usedCounter = usedCounter;
    }

    public String getNextDate()
    {
        return nextDate;
    }

    public void setNextDate(String nextDate)
    {
        this.nextDate = nextDate;
    }

    public String getAddedDate()
    {
        return addedDate;
    }

    public void setAddedDate(String addedDate)
    {
        this.addedDate = addedDate;
    }

    public String getNotes()
    {
        return notes;
    }

    public void setNotes(String notes)
    {
        this.notes = notes;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int id)
    {
        this.ID = id;
    }
}
