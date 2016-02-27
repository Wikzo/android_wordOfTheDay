package net.gustavdahl.wordoftheday;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter
{

    int resource;
    String response;
    Context context;

    public WordAdapter(Context context, int resource, List<Word> words)
    {
        super(context, resource, words);

        this.resource=resource;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        LinearLayout wordView;
        //Get the current alert object
        Word word = (Word) getItem(position);

        Log.i(this.toString(), word.getWord());

        //Inflate the view
        if(convertView==null)
        {
            wordView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, wordView, true);
        }
        else
        {
            wordView = (LinearLayout) convertView;
        }
        //Get the text boxes from the listitem.xml file
        TextView wordText = new TextView(context);
        wordText.setText("hejsa");

        wordView.addView(wordText);

        return wordView;
    }

/*
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return super.getView(position, convertView, parent);
    }*/
}
