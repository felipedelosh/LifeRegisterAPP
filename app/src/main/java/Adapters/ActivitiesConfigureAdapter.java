package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liferegisterdiary.R;

import java.util.ArrayList;

import Models.ItemActivity;

public class ActivitiesConfigureAdapter extends BaseAdapter {

    public ArrayList<ItemActivity> arrayItemActivities;
    public Context context;

    public ActivitiesConfigureAdapter(ArrayList<ItemActivity> arrayItemActivities, Context context) {
        this.arrayItemActivities = arrayItemActivities;
        this.context = context;
    }

    public ItemActivity getItemActivityX(int position){
        return (ItemActivity) getItem(position);
    }

    @Override
    public int getCount() {
        return arrayItemActivities.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayItemActivities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=LayoutInflater.from(context).inflate(R.layout.itemactivity, null);
        }

        TextView activityIdNumber = convertView.findViewById(R.id.activityIdNumber);
        activityIdNumber.setText(Integer.toString(position+1));

        EditText txtActivity = convertView.findViewById(R.id.txtActivity);
        String nameActivity =  getItemActivityX(position).getNameActivity();
        txtActivity.setText(nameActivity);

        return convertView;
    }
}
