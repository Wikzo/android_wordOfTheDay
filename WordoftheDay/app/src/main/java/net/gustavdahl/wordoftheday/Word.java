package net.gustavdahl.wordoftheday;

public class Word
{
    private String word;
    private String meaning;
    private String language;
    private String addedDate;
    private String activationDate;
    private int usedCount;
    private boolean active;

    public Word(String word,
                           String meaning,
                           String language,
                           String addedDate,
                           String activationDate,
                           int usedCount,
                           boolean active)
    {
        this.word = word;
        this.meaning = meaning;
        this.language = language;
        this.addedDate = addedDate;
        this.activationDate = activationDate;
        this.usedCount = usedCount;
        this.active = active;

        // TODO: be able to handle null/empty data!
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
}
