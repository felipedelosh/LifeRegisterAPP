package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.liferegisterdiary.R;

import java.util.ArrayList;

import Models.ItemTimeInversion;

public class TimeDistributionAdapter extends BaseAdapter {

    public ArrayList<ItemTimeInversion> arrayListItemTimeInversion;
    public Context context;

    //Vars to generate a text in Spiner and hour
    public ArrayList<String> spinnerValues;

    public TimeDistributionAdapter(ArrayList<ItemTimeInversion> arrayListItemTimeInversion, Context context, ArrayList<String> spinnerValues) {
        this.arrayListItemTimeInversion = arrayListItemTimeInversion;
        this.context = context;
        this.spinnerValues = spinnerValues;
    }

    /**
     * Enter a integer (0, 23)
     * And convert this a 6 am  ... 12 pm... 5 am hour
     * return a String with 12h
     * */
    private String getHour(int iterator){

        String formatAMPM = "am";
        int hour12h = 6;

        if((hour12h + iterator) < 13){
            hour12h = hour12h + iterator;
        }else{
            hour12h = iterator - 6;
            formatAMPM = "pm";

            if ((iterator - 6)>12){
                hour12h = iterator - 18;
                formatAMPM = "am";
            }
        }

        return String.valueOf(hour12h)+formatAMPM;
    }

    @Override
    public int getCount() {
        return arrayListItemTimeInversion.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListItemTimeInversion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=LayoutInflater.from(context).inflate(R.layout.itemtimeinversion, null);
        }

        TextView listViewTimeInversionHourItem = convertView.findViewById(R.id.listViewTimeInversionHourItem);
        //Put hour
        String hour = getHour(position);
        if (hour.length() == 3){
            listViewTimeInversionHourItem.setText(hour+"   >>");
        }else {
            listViewTimeInversionHourItem.setText(hour+" >>");
        }
        //Put Spinner options
        Spinner listViewTimeInversionSpinnerActivity = convertView.findViewById(R.id.listViewTimeInversionSpinnerActivity);
        ArrayAdapter<String> sprAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, spinnerValues);
        listViewTimeInversionSpinnerActivity.setAdapter(sprAdapter);


        return convertView;
    }
}