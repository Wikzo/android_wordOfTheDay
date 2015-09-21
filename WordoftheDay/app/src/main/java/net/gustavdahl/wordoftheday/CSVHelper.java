package net.gustavdahl.wordoftheday;


import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper
{

    public static List<Word> GetAllData(InputStream in, int row, int column)
    {
        String next[] = {};
        List<String[]> list = new ArrayList<String[]>();

        try
        {
            CSVReader reader = new CSVReader(new InputStreamReader(in));


            while (true)
            {
                next = reader.readNext();
                if (next != null)
                {
                    list.add(next);
                } else
                {
                    break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        int rowMax = list.size();
        int columnMax = list.get(0).length;


        if (row != 0)
            rowMax = row;

        if (column != 0)
            columnMax = column;

        String data = "";


        List<Word> words = new ArrayList<Word>();

        for (int i = 0; i < rowMax; i++)
        {
            String t = "";

            for (int j = 0; j < columnMax; j++)
            {
                t += list.get(i)[j] + ";";
                //data += list.get(i)[j];

            }

            //Log.d(MainActivity.TAG, t);
            Word w = new Word(i, t, ";");
            words.add(w);

           //Log.d(MainActivity.TAG, w.ToString(true));


        }

        return words;

    }
}
