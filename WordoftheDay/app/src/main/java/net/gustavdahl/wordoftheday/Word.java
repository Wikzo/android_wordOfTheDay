package net.gustavdahl.wordoftheday;

/**
 * Created by Wikzo on 18-09-2015.
 */
public class Word
{
    private String theWord;
    private String theMeaning;
    private int usedTimes;
    private int nextDate;
    private int id;

    public Word() { }

    public Word(String theWord, String theMeaning)
    {
        this.theWord = theWord;
        this.theMeaning = theMeaning;
    }

    public Word(int id, String theWord, String theMeaning)
    {
        this.theWord = theWord;
        this.theMeaning = theMeaning;
        this.id = id;
    }

    public String getTheWord()
    {
        return theWord;
    }

    public void setTheWord(String theWord)
    {
        this.theWord = theWord;
    }

    public String getTheMeaning()
    {
        return theMeaning;
    }

    public void setTheMeaning(String theMeaning)
    {
        this.theMeaning = theMeaning;
    }

    public int getUsedTimes()
    {
        return usedTimes;
    }

    public void setUsedTimes(int usedTimes)
    {
        this.usedTimes = usedTimes;
    }

    public int getNextDate()
    {
        return nextDate;
    }

    public void setNextDate(int nextDate)
    {
        this.nextDate = nextDate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }
}
