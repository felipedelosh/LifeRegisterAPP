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

import models.ItemDrug;

public class DrugsConfigureAdapter extends BaseAdapter {

    private static List<ItemDrug> arrayItemDrugs;
    private static Context context;

    public DrugsConfigureAdapter(List<ItemDrug> arrayItemDrugs, Context context) {
        this.arrayItemDrugs = arrayItemDrugs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayItemDrugs.size();
    }

    public ItemDrug getDrugX(int position){
        return (ItemDrug) getItem(position);
    }

    @Override
    public Object getItem(int position) {
        return arrayItemDrugs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){

            convertView=LayoutInflater.from(context).inflate(R.layout.itemdrug, null);
        }

        TextView drugIdNumber = convertView.findViewById(R.id.drugIdNumber);
        drugIdNumber.setText(Integer.toString(position+1));

        EditText txtDrug = convertView.findViewById(R.id.txtDrug);
        String drugName = getDrugX(position).getDrugName();
        txtDrug.setText(drugName);

        return convertView;
    }
}
