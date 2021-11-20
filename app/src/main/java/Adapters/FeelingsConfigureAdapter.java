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
import Models.ItemFeeling;

public class FeelingsConfigureAdapter extends BaseAdapter {

    public ArrayList<ItemFeeling> arrayItemFeelings;
    public Context context;

    public FeelingsConfigureAdapter(ArrayList<ItemFeeling> arrayItemFeelings, Context context) {
        this.arrayItemFeelings = arrayItemFeelings;
        this.context = context;
    }

    public ItemFeeling getItemFeelingX(int position){
        return (ItemFeeling) getItem(position);
    }

    @Override
    public int getCount() {
        return arrayItemFeelings.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayItemFeelings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=LayoutInflater.from(context).inflate(R.layout.itemfeeling, null);
        }

        TextView feelingIdNumber = convertView.findViewById(R.id.feelingIdNumber);
        feelingIdNumber.setText(Integer.toString(position+1));

        String feelingName = getItemFeelingX(position).getNameFeeling();
        EditText txtFeeling = convertView.findViewById(R.id.txtFeeling);
        txtFeeling.setText(feelingName);


        return convertView;
    }
}
