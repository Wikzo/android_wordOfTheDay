package net.gustavdahl.wordoftheday;

import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxUnlinkedException;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainActivity extends AppCompatActivity
{


    //private static final String TAG = MainActivity.class.getSimpleName();
    public static final String TAG = "MyDebug";

    CSVAdapter csvAdapter;


    public static DropboxAPI<AndroidAuthSession> mDBApi;
    final static private String APP_KEY = "akbycb3wan8az7a";
    final static private String APP_SECRET = "bqcweeocel5g75a";
    private Button mSubmit;
    private boolean mLoggedIn;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // CSV stuff ----------------
        // making list ready
        ListView list = (ListView) findViewById(R.id.mList);
        csvAdapter = new CSVAdapter(this, -1); // -1 is dummy value (not used)
        list.setAdapter(csvAdapter);

        // click callback
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Toast.makeText(view.getContext(), csvAdapter.getItem(position).getMeaning(), Toast.LENGTH_LONG).show();
            }
        });


        // ---------------------

        // SD card / file path stuff -----------


        /*File root = android.os.Environment.getExternalStorageDirectory();
        //File dir = new File (root.getAbsolutePath() +"/testFile.txt");
        //dir.mkdirs();

        PrintWriter writer = null;
        try
        {
            writer = new PrintWriter(root.getAbsolutePath() + "/testFilestxt", "UTF-8");
            // DROPBOX path: Android\data\com.dropbox.android\files\u113180\scratch
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        writer.println("The first line");
        writer.println("The second line");
        writer.close();*/

        // -------------


        // Dropbox stuff ----------

        InitializeDropbox();



        // ---------------

    }

    public void InitializeDropbox() {

        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        mDBApi.getSession().startOAuth2Authentication(MainActivity.this);

    }

    public void UploadDBText(View view) throws DropboxException, IOException
    {
        InputStream input = this.getAssets().open("csv_text.txt");
        DropboxHelper.UploadFile(input,true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onResume()
    {
        super.onResume();

        if (mDBApi.getSession().authenticationSuccessful())
        {
            try
            {
                // Required to complete auth, sets the access token on the session
                mDBApi.getSession().finishAuthentication();

                String accessToken = mDBApi.getSession().getOAuth2AccessToken(); // TODO: save the accessToken for later use
            } catch (IllegalStateException e)
            {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }
}
