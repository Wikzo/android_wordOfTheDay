package net.gustavdahl.wordoftheday;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class DropboxHelper
{
    private static MainActivity parent;
    private static DropboxAPI<AndroidAuthSession> dropbox;

    /*public DropboxHelper(DropboxAPI<AndroidAuthSession> db)
    {
        dropbox = db;
    }*/


    public void UploadFile(final String fileName, final MainActivity parent, DropboxAPI<AndroidAuthSession> db)
    {
        this.parent = parent;
        this.dropbox = db;


        //if (dropbox.getSession().authenticationSuccessful())
        if (true)
        {
            Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        File root = android.os.Environment.getExternalStorageDirectory();
                        File file = new File(root.getAbsolutePath() + "/" + fileName);
                        FileInputStream inputStream = new FileInputStream(file);


                        DropboxAPI.Entry response = dropbox.putFileOverwrite("/" + fileName, inputStream, file.length(), null);

                        //Log.i("DbExampleLog", "The uploaded file's rev is: " + response.rev);
                        Log.i(MainActivity.TAG, "Dropbox successfully uploaded!");


                        parent.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                Toast.makeText(parent.getBaseContext(), "Upload successful", Toast.LENGTH_SHORT).show();
                            }
                        });


                    } catch (Exception e)
                    {
                        e.printStackTrace();

                        parent.runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                Toast.makeText(parent.getBaseContext(), "Upload unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }
            });
            thread.start();
        }
    }

    // https://stackoverflow.com/questions/28254321/android-dropbox-core-api-upload-file-sample
    public void DownloadFile(final String fileName, final MainActivity parent, DropboxAPI<AndroidAuthSession> db)
    {
        this.parent = parent;
        this.dropbox = db;

        //if (dropbox.getSession().authenticationSuccessful())
        if (true)
        {
            final Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        File root = Environment.getExternalStorageDirectory();
                        File file = new File(root.getAbsolutePath() + "/" + fileName);
                        try
                        {
                            FileOutputStream f = new FileOutputStream(file);

                            DropboxAPI.DropboxFileInfo info = dropbox.getFile("/" + fileName, null, f, null);

                            //Log.i("DbExampleLog", "The file's rev is: " + info.getMetadata().rev);
                            Log.i(MainActivity.TAG, "Dropbox downloaded successfully!");

                            parent.runOnUiThread(new Runnable()
                            {
                                public void run()
                                {
                                    Toast.makeText(parent.getBaseContext(), "Download successful", Toast.LENGTH_SHORT).show();
                                }
                            });


                        } catch (FileNotFoundException e)
                        {
                            Log.i(MainActivity.TAG, "******* File not found. Did you" +
                                    " add a WRITE_EXTERNAL_STORAGE permission to the manifest?");

                            e.printStackTrace();

                        } catch (IOException e)
                        {
                            Log.i(MainActivity.TAG, "IO exception!");
                            e.printStackTrace();


                        } catch (DropboxException e)
                        {
                            Log.i(MainActivity.TAG, "Dropbox exception!");
                            e.printStackTrace();

                            parent.runOnUiThread(new Runnable()
                            {
                                public void run()
                                {
                                    Toast.makeText(parent.getBaseContext(), "Download unsuccessful", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                    } catch (Exception e)
                    {
                        e.printStackTrace();
                        Log.i(MainActivity.TAG, "Stack exception!");

                    }
                }
            });
            thread.start();
        }
    }
}
