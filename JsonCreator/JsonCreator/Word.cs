using System;

namespace JsonCreator
{
    public class Word
    {
        public string word = "Word";
        public string meaning = "Meaning";
        public string language = "DK";
        public string addedDate = "31-04-2016";
        public string activationDate = "01-05-2016";
        public int usedCount = 0;
        public bool active = true;
        public int priority = 1;
        public string notes = "Notes";

        public Word(int index)
        {
            word = "Word_" + index;

            string time = DateTime.Now.ToString("dd-MM-yyyy");
            this.addedDate = time;
            this.activationDate = time;


        }

        public void CleanupStrings()
        {
            word.Trim();
            meaning.Trim();
            language.Trim();
            addedDate.Trim();
            activationDate.Trim();
            notes.Trim();
        }
    }

}