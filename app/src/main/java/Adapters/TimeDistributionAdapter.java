package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
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


    public ItemTimeInversion getItemX(int i){
        return (ItemTimeInversion) getItem(i);
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
        String hour = getItemX(position).getHour();
        if (hour.length() == 3){
            listViewTimeInversionHourItem.setText(hour+"   >>");
        }else {
            listViewTimeInversionHourItem.setText(hour+" >>");
        }
        //Put Spinner options
        Spinner listViewTimeInversionSpinnerActivity = convertView.findViewById(R.id.listViewTimeInversionSpinnerActivity);
        ArrayAdapter<String> sprAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, spinnerValues);
        listViewTimeInversionSpinnerActivity.setAdapter(sprAdapter);
        getItemX(position).setSpinner(listViewTimeInversionSpinnerActivity);

        return convertView;
    }
}
