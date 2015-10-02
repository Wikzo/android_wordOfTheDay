package net.gustavdahl.wordoftheday;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CSVAdapter extends ArrayAdapter<Word>
{
    Context context;

    public CSVAdapter(Context context, int textViewResourceID)
    {
        super(context, textViewResourceID);

        this.context = context;

        LoadArrayFromFile("csvTest.csv");
    }


    /*
	 * getView() is the method responsible for building a View out of a some data that represents
	 * one row within the ListView. For this example our row will be a single TextView that
	 * gets populated with the state name.
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
    @Override
    public View getView(final int pos, View convertView, final ViewGroup parent){
		/*
		 * Using convertView is important. The system will pass back Views that have been
		 * created but scrolled off of the top (or bottom) of the screen, and thus are no
		 * longer being shown on the screen. Since they are unused, we can "recycle" them
		 * instead of creating a new View object for every row, which would be wasteful,
		 * and lead to poor performance. The diference may not be noticeable in this
		 * small example. But with larger more complex projects it will make a significant
		 * improvement by recycling Views rather than creating new ones for each row.
		 */
        TextView mView = (TextView)convertView;
        //If convertView was null then we have to create a new TextView.
        //If it was not null then we'll re-use it by setting the appropriate
        //text String to it.
        if(null == mView){
            mView = new TextView(parent.getContext());
            mView.setTextSize(28);
        }

        //Set the state name as the text.
        mView.setText(getItem(pos).getWord());

        //We could handle the row clicks from here. But instead
        //we'll use the ListView.OnItemClickListener from inside
        //of MainActivity, which provides some benefits over doing it here.

		/*mView.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				Toast.makeText(parent.getContext(), getItem(pos).getCapital(), Toast.LENGTH_SHORT).show();
			}
		});*/

        return mView;
    }

    private void LoadArrayFromFile(String filePath)
    {
        try
        {
            InputStream input = context.getAssets().open(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] RowData = line.split(",");

                Word word = new Word();
                word.setWord(RowData[0]);
                word.setMeaning(RowData[1]);
                // .... etc.

                this.add(word);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
