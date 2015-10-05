package net.gustavdahl.wordoftheday;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
{


    //private static final String TAG = MainActivity.class.getSimpleName();
    public static final String TAG = "MyDebug";
    private static final String ACCOUNT_PREFS_NAME = "";

    CSVAdapter csvAdapter;


    private static DropboxAPI<AndroidAuthSession> mDBApi;
    public DropboxAPI<AndroidAuthSession> GetDB()
    {
        if (mDBApi == null)
        {
            mDBApi = getDropboxAPI();
        }
        return mDBApi;
    }
    final static private String APP_KEY = "akbycb3wan8az7a";
    final static private String APP_SECRET = "bqcweeocel5g75a";
    private Button mSubmit;
    private boolean mLoggedIn;

    private String FileName = "MyWords1.txt";

    private DropboxHelper dropboxHelper;


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


        Log.i(TAG, "Token: " + RetriveAccessToken());

        dropboxHelper = new DropboxHelper();

        Toast.makeText(this, "Began, stored key: " + RetriveAccessToken(), Toast.LENGTH_SHORT).show();




    }

    boolean dropboxHasInitialized;


    public DropboxAPI<AndroidAuthSession> getDropboxAPI()
    {
        // https://stackoverflow.com/questions/19083564/android-dropbox-api-not-saving-login

        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);


        String savedAccessToken = RetriveAccessToken();
        if (savedAccessToken != "")
            mDBApi.getSession().setOAuth2AccessToken(savedAccessToken);
        else
            mDBApi.getSession().startOAuth2Authentication(MainActivity.this);

        return mDBApi;
    }


    public void InitializeDropbox(View view)
    {

        mDBApi = getDropboxAPI();
        /*
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        //mDBApi.getSession().startOAuth2Authentication(MainActivity.this);


        String savedAccessToken = RetriveAccessToken();
        if (savedAccessToken != "")
            mDBApi.getSession().setOAuth2AccessToken(savedAccessToken);
        else
            mDBApi.getSession().startOAuth2Authentication(MainActivity.this);




        // https://stackoverflow.com/questions/19083564/android-dropbox-api-not-saving-login

/*
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);
        //mDBApi.getSession().startOAuth2Authentication(MainActivity.this);

        mDBApi = new DropboxAPI<AndroidAuthSession>(session);

        String savedAccessToken = RetriveAccessToken();

        if (!TextUtils.isEmpty(savedAccessToken))
        {
            Log.i(TAG, "Using stored token");
            mDBApi.getSession().setOAuth2AccessToken(savedAccessToken);
        } else
        {
            mDBApi.getSession().startOAuth2Authentication(MainActivity.this);
            Log.i(TAG, "Getting new token");

        }


        dropboxHasInitialized = true;

        Log.i(TAG, "Dropbox successfully initialized!");
        Toast.makeText(view.getContext(), "Dropbox successfully initialized!", Toast.LENGTH_SHORT).show();
        */
    }

    public boolean isDropboxLinked()
    {
        Log.i(TAG, "Getting token ...");
        return mDBApi != null && (mDBApi.getSession().isLinked() || mDBApi.getSession().authenticationSuccessful());
    }

    public void UploadDropbox(View view) throws DropboxException, IOException
    {



        Log.i(TAG, "Dropbox uploading...");
        Toast.makeText(view.getContext(), "Uploading started...", Toast.LENGTH_SHORT).show();

        dropboxHelper.UploadFile(FileName, this, getDropboxAPI());


    }

    public void DownloadDropbox(View view) throws FileNotFoundException, DropboxException
    {


        Log.i(TAG, "Dropbox downloading...");
        Toast.makeText(view.getContext(), "Downloading started...", Toast.LENGTH_SHORT).show();

        dropboxHelper.DownloadFile(FileName, this, getDropboxAPI());

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

        if (!dropboxHasInitialized)
            return;

        if (mDBApi.getSession().authenticationSuccessful())
        {
            try
            {
                // Required to complete auth, sets the access token on the session
                mDBApi.getSession().finishAuthentication();

                String accessToken = mDBApi.getSession().getOAuth2AccessToken(); // TODO: save the accessToken for later use
                StoreAccessToken(accessToken);
                Log.i(TAG, "The new token: " + accessToken);
            } catch (IllegalStateException e)
            {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    final String AccessToken = "AccessToken";

    private void StoreAccessToken(String token)
    {
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(AccessToken, token);
        editor.commit();

    }

    private String RetriveAccessToken()
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(AccessToken, null);

        if (restoredText != null)
            return restoredText;
        else
            return "";
    }
}
