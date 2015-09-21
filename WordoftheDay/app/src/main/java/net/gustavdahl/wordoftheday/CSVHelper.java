package net.gustavdahl.wordoftheday;


import android.os.Environment;
import android.util.Log;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper
{

    public static List<Word> ReadCSVFile(InputStream in, int numberOfRows, int numberOfColumns)
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


        if (numberOfRows != 0)
            rowMax = numberOfRows;

        if (numberOfColumns != 0)
            columnMax = numberOfColumns;

        List<Word> words = new ArrayList<Word>();

        for (int i = 0; i < rowMax; i++)
        {
            String t = "";

            for (int j = 0; j < columnMax; j++)
            {
                t += list.get(i)[j] + ";";
            }

            //Log.d(MainActivity.TAG, t);
            Word w = new Word(i, t, ";");
            words.add(w);
        }

        return words;

    }

    private static final char SEPARATOR = ';';
    public static void UpdateCSV(InputStream input, String  replace, int row, int col) throws IOException
    {

        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File (sdCard.getAbsolutePath() + "/dir");

        File file = new File(dir, "tsxt.txt");

        FileOutputStream f = new FileOutputStream(file);



        //////////

        CSVReader reader = new CSVReader(new InputStreamReader(input));

        List<String[]> csvBody = reader.readAll();
        csvBody.get(row)[col]=replace;
        reader.close();

        CSVWriter writer = new CSVWriter(new FileWriter(file),SEPARATOR,' ');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();
    }


}
