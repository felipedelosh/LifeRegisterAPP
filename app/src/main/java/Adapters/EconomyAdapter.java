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

import Models.ItemTaccont;

public class EconomyAdapter extends BaseAdapter {

    public ArrayList<ItemTaccont> itemsTaccount;
    public Context context;

    public EconomyAdapter(ArrayList<ItemTaccont> itemsTaccount, Context context) {
        this.itemsTaccount = itemsTaccount;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsTaccount.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsTaccount.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=LayoutInflater.from(context).inflate(R.layout.itemtaccount, null);
            }

        TextView listViewEconomyNumberItem = convertView.findViewById(R.id.listViewEconomyNumberItem);
        EditText listViewEconomyConcept = convertView.findViewById(R.id.listViewEconomyConcept);
        EditText listViewEconomyDebit = convertView.findViewById(R.id.listViewEconomyDebit);
        EditText listViewEconomyCredit = convertView.findViewById(R.id.listViewEconomyCredit);

        listViewEconomyNumberItem.setText((position+1)+": ");


        return convertView;
    }
}
