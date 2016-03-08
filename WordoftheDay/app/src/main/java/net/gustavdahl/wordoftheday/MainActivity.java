// main

package net.gustavdahl.wordoftheday;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";

    private TextView wordTextView;
    private TextView meaningTextView;
    private RatingBar usedCountStars;

    private Calendar calendar;


    private void InitializeTextViews()
    {
        wordTextView = (TextView)findViewById(R.id.word_name);
        meaningTextView = (TextView)findViewById(R.id.word_meaning);
        usedCountStars = (RatingBar)findViewById(R.id.usedCountStars);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeTextViews();
        CheckIfJsonFileExists();

        try
        {
            Word.LoadAllWords();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        Word.setSelectedWord(Word.allActiveWordObjects.get(0));
        UpdateTextViews();

        //TODO: save calendar as persitable
        // https://developer.android.com/guide/components/activities.html

        if (calendar == null)
            calendar = GetRandomTime();

        scheduleNotification(getNotification(Word.getSelectedWord().getWord()), calendar);
        Toast.makeText(MainActivity.this, "Next time: " + calendar.getTime(), Toast.LENGTH_LONG).show();

    }

    private Calendar GetRandomTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int minMinute = 0;
        int maxMinute = 60;

        int minHour = 8;
        int maxHour = 17;

        Random random = new Random();

        int minute = random.nextInt(maxMinute - minMinute + 1) + minMinute;
        int hour = random.nextInt(maxHour - minHour + 1) + minHour;

        int tries = 0;
        while (hour < Calendar.getInstance().getTime().getHours() && tries < 10)
        {
            tries++;

            if (tries < 10)
                hour = random.nextInt(maxHour - minHour + 1) + minHour;
            else // not sure if this works...
                calendar.add(Calendar.DAY_OF_YEAR, 1);

        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        return calendar;
    }

    private void scheduleNotification(Notification notification, Calendar calendar) {

        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private Notification getNotification(String content)
    {
        // https://gist.github.com/BrandonSmith/6679223
        // https://stackoverflow.com/questions/3052149/using-alarmmanager-to-start-a-service-at-specific-time#3058837

        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle(content);
        builder.setContentText("Remember to use this word.");
        builder.setSmallIcon(R.drawable.checkmark);
        builder.setContentIntent(PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));

        return builder.build();
    }


    private void CheckIfJsonFileExists()
    {
        File f = new File(JsonFileManager.GetFilePath());
        if(!f.exists())
            JsonFileManager.WriteDummyFile();
    }

    private void UpdateTextViews()
    {
        Word selectedWord = Word.getSelectedWord();

        if (selectedWord != null)
        {
            wordTextView.setText(selectedWord.getWord());
            meaningTextView.setText(selectedWord.getMeaning());

            usedCountStars.setRating(Math.min(3, selectedWord.getUsedCount()));
        }
    }

    public void CheckWordHasBeenUsed(View view) throws JSONException
    {
        Word.getSelectedWord().incrementUsedCount();

        Word.SaveAllWords();
        UpdateTextViews();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        UpdateTextViews();
    }

    public void ShowWordList(View view)
    {
        Intent intent = new Intent(this, WordListActivity.class);
       // String message = "test_message";
       //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void EditCurrentWord(View view)
    {
        //Word.getSelectedWord().setUsedCount(Word.getSelectedWord().getUsedCount() + 1);
        //UpdateTextViews();

        Intent intent = new Intent(this, EditWord.class);
        //String message = "test_message";
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void GetNextActiveWord(View view)
    {
        Word.setSelectedWord(Word.GetNextWord());
        UpdateTextViews();
    }

    public void GetPreviousActiveWord(View view)
    {
        Word.setSelectedWord(Word.GetPreviousWord());
        UpdateTextViews();
    }

    public void WriteDummyFile(View view)
    {
        boolean success = JsonFileManager.WriteDummyFile();

        String text = "Wrote dummy file in:" + JsonFileManager.GetFilePath();

        if (!success)
            text = "Could NOT write dummy file in: " + JsonFileManager.GetFilePath();

        Toast.makeText(
                MainActivity.this,
                text,
                Toast.LENGTH_SHORT).show();
    }

}
