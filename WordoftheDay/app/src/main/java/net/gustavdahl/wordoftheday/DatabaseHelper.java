package net.gustavdahl.wordoftheday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "WordsManager";

    // Contacts table name
    private static final String TABLE_WORDS = "words1";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_WORD = "word";
    private static final String KEY_MEANING = "meaning";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_WORDS_TABLE = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                + KEY_MEANING + " TEXT" + ")";
        db.execSQL(CREATE_WORDS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addWords(Word w)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, w.getTheWord()); // word
        values.put(KEY_MEANING, w.getTheMeaning()); // meaning

        // Inserting Row
        db.insert(TABLE_WORDS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    public Word getWord(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WORDS, new String[] { KEY_ID,
                        KEY_WORD, KEY_MEANING}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Word w = new Word(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return w;
    }

    // Getting All Contacts
    public List<Word> getAllWords()
    {
        List<Word> wordList = new ArrayList<Word>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_WORDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Word w = new Word();
                w.setId(Integer.parseInt(cursor.getString(0)));
                w.setTheWord(cursor.getString(1));
                w.setTheMeaning(cursor.getString(2));
                // Adding contact to list
                wordList.add(w);
            } while (cursor.moveToNext());
        }

        // return contact list
        return wordList;
    }

    // Getting contacts Count
    public int getWordsCount()
    {
        String countQuery = "SELECT  * FROM " + TABLE_WORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
    // Updating single contact
    public int updateWord(Word w)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, w.getTheWord());
        values.put(KEY_MEANING, w.getTheMeaning());

        // updating row
        return db.update(TABLE_WORDS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(w.getId())});
    }

    // Deleting single contact
    public void deleteWord(Word w)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, KEY_ID + " = ?",
                new String[]{String.valueOf(w.getId())});
        db.close();

    }

    public void deleteAllWords()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WORDS, null, null);
        db.close();

    }
}