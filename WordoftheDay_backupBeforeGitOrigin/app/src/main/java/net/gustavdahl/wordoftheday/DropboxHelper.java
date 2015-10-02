package net.gustavdahl.wordoftheday;

import android.util.Log;
import com.dropbox.client2.DropboxAPI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class DropboxHelper
{
    public static void UploadFile(final InputStream input, final boolean useCSV)
    {
        if (MainActivity.mDBApi.getSession().authenticationSuccessful())
        {
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        File root = android.os.Environment.getExternalStorageDirectory();
                        File file = new File(root.getAbsolutePath() + "/db_test.txt");
                        FileInputStream inputStream = new FileInputStream(file);



                        PrintWriter writer = null;
                        try
                        {
                            writer = new PrintWriter(root.getAbsolutePath() + "/db_test.txt", "UTF-8");
                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e)
                        {
                            e.printStackTrace();
                        }
                        writer.println(System.currentTimeMillis());
                        writer.close();



                        if (useCSV) // asset
                        {
                            DropboxAPI.Entry response = MainActivity.mDBApi.putFileOverwrite("/csv_text.txt", input, input.available(), null);
                            Log.i("DbExampleLog", "The uploaded file's rev is: " + response.rev);

                        }
                        else // new file
                        {
                            DropboxAPI.Entry response = MainActivity.mDBApi.putFileOverwrite("/db_test.txt", inputStream, file.length(), null);
                            Log.i("DbExampleLog", "The uploaded file's rev is: " + response.rev);

                        }

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
        }
    }

    public static void DownloadFile()
    {

    }
}
