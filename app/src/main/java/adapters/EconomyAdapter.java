package adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liferegisterdiary.R;

import java.util.List;

import models.ItemTaccont;

public class EconomyAdapter extends BaseAdapter {

    private static List<ItemTaccont> itemsTaccount;
    private static Context context;

    public EconomyAdapter(List<ItemTaccont> itemsTaccount, Context context) {
        this.itemsTaccount = itemsTaccount;
        this.context = context;
    }

    public ItemTaccont getItemX(int position){
        return (ItemTaccont) getItem(position);
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

            convertView=LayoutInflater.from(context).inflate(R.layout.itemtaccount, null);
            }

        TextView listViewEconomyNumberItem = convertView.findViewById(R.id.listViewEconomyNumberItem);
        getItemX(position).setIdTaccount(position);
        EditText listViewEconomyConcept = convertView.findViewById(R.id.listViewEconomyConcept);
        getItemX(position).setListViewEconomyConcept(listViewEconomyConcept);
        EditText listViewEconomyDebit = convertView.findViewById(R.id.listViewEconomyDebit);
        getItemX(position).setListViewEconomyDebit(listViewEconomyDebit);
        EditText listViewEconomyCredit = convertView.findViewById(R.id.listViewEconomyCredit);
        getItemX(position).setListViewEconomyCredit(listViewEconomyCredit);

        listViewEconomyNumberItem.setText((position+1)+": ");


        return convertView;
    }
}
