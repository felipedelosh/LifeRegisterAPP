package com.example.liferegisterdiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import Adapters.EconomyAdapter;
import Models.ItemTaccont;
import db.DbTAccount;

public class TAccounts extends AppCompatActivity {

    private Spinner spinner_days;
    private Spinner spinner_mounth;
    //private ListView listViewEconomy;
    private ArrayList<EditText> allConcepts;
    private ArrayList<EditText> allDebit;
    private ArrayList<EditText> allCredit;


    private EditText listViewEconomyConcept1;
    private EditText listViewEconomyDebit1;
    private EditText listViewEconomyCredit1;

    private EditText listViewEconomyConcept2;
    private EditText listViewEconomyDebit2;
    private EditText listViewEconomyCredit2;

    private EditText listViewEconomyConcept3;
    private EditText listViewEconomyDebit3;
    private EditText listViewEconomyCredit3;

    private EditText listViewEconomyConcept4;
    private EditText listViewEconomyDebit4;
    private EditText listViewEconomyCredit4;

    private EditText listViewEconomyConcept5;
    private EditText listViewEconomyDebit5;
    private EditText listViewEconomyCredit5;

    private EditText listViewEconomyConcept6;
    private EditText listViewEconomyDebit6;
    private EditText listViewEconomyCredit6;

    private EditText listViewEconomyConcept7;
    private EditText listViewEconomyDebit7;
    private EditText listViewEconomyCredit7;

    private EditText listViewEconomyConcept8;
    private EditText listViewEconomyDebit8;
    private EditText listViewEconomyCredit8;

    private EditText listViewEconomyConcept9;
    private EditText listViewEconomyDebit9;
    private EditText listViewEconomyCredit9;

    private EditText listViewEconomyConcept10;
    private EditText listViewEconomyDebit10;
    private EditText listViewEconomyCredit10;

    private Button btn_load_taccount;
    private Button btn_save_taccount;


    //private EconomyAdapter economyAdapter;
    //private ArrayList <ItemTaccont> arrayTaccounts;
    private Context context;

    private TimeController timeController;
    private DbTAccount dbTAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taccounts);

        context = this;
        timeController = new TimeController();
        dbTAccount = new DbTAccount(context);


        allConcepts = new ArrayList<>();
        allDebit = new ArrayList<>();
        allCredit = new ArrayList<>();
        initAllEdittext();

        spinner_days = findViewById(R.id.spinner_days);
        spinner_mounth = findViewById(R.id.spinner_mounth);

        //listViewEconomy = findViewById(R.id.listViewEconomy);

        putAMonthsInSpinner();
        //Put all days of current mounth
        refreshDaysOfSpinner();
        //Put currently mont in spinner
        spinner_mounth.setSelection(timeController.getNumberOfCurrentMounth());


        //Generate Taccount items
        //arrayTaccounts = new ArrayList<>();
        //economyAdapter = new EconomyAdapter(arrayTaccounts, context);
        //listViewEconomy.setAdapter(economyAdapter);
        //for(int i=0;i<10;i++){
        //    createNewTAccountsItem();
        //}

        spinner_mounth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               refreshDaysOfSpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        setupView();
    }

    private void setupView() {

        btn_load_taccount = findViewById(R.id.btn_load_taccount);
        btn_load_taccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTaacountReport();
            }
        });
        btn_save_taccount = findViewById(R.id.btn_save_taccount);
        btn_save_taccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFliendsOdEconomyReport()){
                    saveEconomyRegisterInDatabase();
                }
            }
        });


    }

    public void initAllEdittext(){
        listViewEconomyConcept1 = findViewById(R.id.listViewEconomyConcept1);
        allConcepts.add(listViewEconomyConcept1);
        listViewEconomyDebit1 = findViewById(R.id.listViewEconomyDebit1);
        allDebit.add(listViewEconomyDebit1);
        listViewEconomyCredit1 = findViewById(R.id.listViewEconomyCredit1);
        allCredit.add(listViewEconomyCredit1);


        listViewEconomyConcept2 = findViewById(R.id.listViewEconomyConcept2);
        allConcepts.add(listViewEconomyConcept2);
        listViewEconomyDebit2 = findViewById(R.id.listViewEconomyDebit2);
        allDebit.add(listViewEconomyDebit2);
        listViewEconomyCredit2 = findViewById(R.id.listViewEconomyCredit2);
        allCredit.add(listViewEconomyCredit2);

        listViewEconomyConcept3 = findViewById(R.id.listViewEconomyConcept3);
        allConcepts.add(listViewEconomyConcept3);
        listViewEconomyDebit3 = findViewById(R.id.listViewEconomyDebit3);
        allDebit.add(listViewEconomyDebit3);
        listViewEconomyCredit3 = findViewById(R.id.listViewEconomyCredit3);
        allCredit.add(listViewEconomyCredit3);

        listViewEconomyConcept4 = findViewById(R.id.listViewEconomyConcept4);
        allConcepts.add(listViewEconomyConcept4);
        listViewEconomyDebit4 = findViewById(R.id.listViewEconomyDebit4);
        allDebit.add(listViewEconomyDebit4);
        listViewEconomyCredit4 = findViewById(R.id.listViewEconomyCredit4);
        allCredit.add(listViewEconomyCredit4);

        listViewEconomyConcept5 = findViewById(R.id.listViewEconomyConcept5);
        allConcepts.add(listViewEconomyConcept5);
        listViewEconomyDebit5 = findViewById(R.id.listViewEconomyDebit5);
        allDebit.add(listViewEconomyDebit5);
        listViewEconomyCredit5 = findViewById(R.id.listViewEconomyCredit5);
        allCredit.add(listViewEconomyCredit5);

        listViewEconomyConcept6 = findViewById(R.id.listViewEconomyConcept6);
        allConcepts.add(listViewEconomyConcept6);
        listViewEconomyDebit6 = findViewById(R.id.listViewEconomyDebit6);
        allDebit.add(listViewEconomyDebit6);
        listViewEconomyCredit6 = findViewById(R.id.listViewEconomyCredit6);
        allCredit.add(listViewEconomyCredit6);

        listViewEconomyConcept7 = findViewById(R.id.listViewEconomyConcept7);
        allConcepts.add(listViewEconomyConcept7);
        listViewEconomyDebit7 = findViewById(R.id.listViewEconomyDebit7);
        allDebit.add(listViewEconomyDebit7);
        listViewEconomyCredit7 = findViewById(R.id.listViewEconomyCredit7);
        allCredit.add(listViewEconomyCredit7);

        listViewEconomyConcept8 = findViewById(R.id.listViewEconomyConcept8);
        allConcepts.add(listViewEconomyConcept8);
        listViewEconomyDebit8 = findViewById(R.id.listViewEconomyDebit8);
        allDebit.add(listViewEconomyDebit8);
        listViewEconomyCredit8 = findViewById(R.id.listViewEconomyCredit8);
        allCredit.add(listViewEconomyCredit8);

        listViewEconomyConcept9 = findViewById(R.id.listViewEconomyConcept9);
        allConcepts.add(listViewEconomyConcept9);
        listViewEconomyDebit9 = findViewById(R.id.listViewEconomyDebit9);
        allDebit.add(listViewEconomyDebit9);
        listViewEconomyCredit9 = findViewById(R.id.listViewEconomyCredit9);
        allCredit.add(listViewEconomyCredit9);

        listViewEconomyConcept10 = findViewById(R.id.listViewEconomyConcept10);
        allConcepts.add(listViewEconomyConcept10);
        listViewEconomyDebit10 = findViewById(R.id.listViewEconomyDebit10);
        allDebit.add(listViewEconomyDebit10);
        listViewEconomyCredit10 = findViewById(R.id.listViewEconomyCredit10);
        allCredit.add(listViewEconomyCredit10);

    }


    //public void createNewTAccountsItem(){
    //    ItemTaccont itemTaccont = new ItemTaccont(0, "", 0, 0);
    //    arrayTaccounts.add(itemTaccont);
    //}


    public void putAMonthsInSpinner(){
        spinner_mounth = findViewById(R.id.spinner_mounth);
        List<String> months = timeController.getCurrentMonths();
        ArrayAdapter <String> adapterSpinnerMounth = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, months);
        spinner_mounth.setAdapter(adapterSpinnerMounth);
        //Put a Mounth in spinner
        spinner_mounth.setSelection(timeController.getNumberOfCurrentMounth());
    }

    public void refreshDaysOfSpinner(){

        String nameMonth = spinner_mounth.getSelectedItem().toString().trim();
        int days = timeController.getNumberDaysOfMounthX(nameMonth);

        String [] spnerDays = new String[days];
        for(int i=0;i<days;i++){
            spnerDays[i] = String.valueOf(i+1);
        }

        ArrayAdapter <String> adapterSpinnerDays = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, spnerDays);
        spinner_days.setAdapter(adapterSpinnerDays);

        //If currentle month put currentle day
        if(nameMonth == timeController.getCurrentMonth()){
            spinner_days.setSelection(timeController.getCurrentDayNumberOfMount()-1);
        }
    }

    //Valitate if the text is rigth
    public boolean validateText(String txt){
        return txt != "" && txt.trim() != "" && txt.trim().length() > 0;
    }

    //Valitate if the value is rigth
    public boolean validateMoney(String txt){
        try {
            int value = Integer.valueOf(txt);
            return value > 0;
        }catch (Exception e){
            return false;
        }
    }

    public boolean moneyIsEmpty(String txt){
        return txt.trim().length() == 0;
    }

    /****
     * read economy report arrays and search errros
     */
    public boolean validateFliendsOdEconomyReport(){

        int errors = 0;

        for(int i=0;i<allConcepts.size();i++){

            //Erase background color


            String concep = allConcepts.get(i).getText().toString();
            String debit = allDebit.get(i).getText().toString().trim();
            String credit = allCredit.get(i).getText().toString().trim();

            //if exits concep information
            if(validateText(concep)){

                //You need some value
                if(!moneyIsEmpty(debit)|!moneyIsEmpty(credit)){
                    //Not write both debit and credit
                    if(!moneyIsEmpty(debit)&!moneyIsEmpty(credit)){
                        allDebit.get(i).setBackgroundColor(Color.RED);
                        allCredit.get(i).setBackgroundColor(Color.RED);
                        errors =  errors + 1;
                    }else{
                        //Only numbers?
                        if(!moneyIsEmpty(debit)){

                            if(!validateMoney(debit)){
                                allDebit.get(i).setBackgroundColor(Color.RED);
                                errors =  errors + 1;
                            }

                        }

                        if(!moneyIsEmpty(credit)){

                            if(!validateMoney(credit)){
                                allCredit.get(i).setBackgroundColor(Color.RED);
                                errors =  errors + 1;
                            }

                        }

                    }
                }else{
                    allConcepts.get(i).setBackgroundColor(Color.RED);
                    errors =  errors + 1;
                }
            }else{
            //If not exist concep information
                if(!moneyIsEmpty(debit)){
                    allDebit.get(i).setBackgroundColor(Color.RED);
                    errors =  errors + 1;
                }

                if(!moneyIsEmpty(credit)){
                    allCredit.get(i).setBackgroundColor(Color.RED);
                    errors =  errors + 1;
                }
            }


        }

        return errors == 0;
    }

    //Save economy registers in DataBase
    public void saveEconomyRegisterInDatabase(){

        int inserted = 0; //The ID register
        int rowsProceced = 0; // Counter a rows inserted
        String timeStampYYYY = timeController.getCurrentYear();
        String timeStampMM = spinner_mounth.getSelectedItem().toString().trim();
        String timeStampDD = spinner_days.getSelectedItem().toString().trim();
        String timeStamp = timeStampYYYY+":"+timeStampMM+":"+timeStampDD;
        for(int i=0;i<allConcepts.size();i++){

            String concep = allConcepts.get(i).getText().toString();
            String debit = allDebit.get(i).getText().toString().trim();
            String credit = allCredit.get(i).getText().toString().trim();

            if(validateText(concep)){

                int d = 0;
                int c = 0;

                if(!moneyIsEmpty(debit)){
                    d = Integer.valueOf(debit);
                    c = 0;
                }

                if(!moneyIsEmpty(credit)){
                    d = 0;
                    c = Integer.valueOf(credit);
                }

                Long id = dbTAccount.insertTAccount(timeStamp, inserted, concep, d, c);

                if(id>0){
                    clearDataInScreem(i);
                    rowsProceced = rowsProceced + 1;
                }
                inserted = inserted + 1;
            }
            Toast.makeText(TAccounts.this, "Inserted: "+rowsProceced, Toast.LENGTH_LONG).show();
        }
    }

    public void loadTaacountReport(){

        String timeStampYYYY = timeController.getCurrentYear();
        String timeStampMM = spinner_mounth.getSelectedItem().toString().trim();
        String timeStampDD = spinner_days.getSelectedItem().toString().trim();
        String timeStamp = timeStampYYYY+":"+timeStampMM+":"+timeStampDD;

        List<String> information = dbTAccount.getTAccountByTimeStamp(timeStamp);

        if(information.size()>0){
            //Delete all info in scrrem
            for(int i=0;i<allConcepts.size();i++){
                clearDataInScreem(i);
            }
            //Put info in screem
            for(int i=0;i<information.size();i++){
                String [] values = information.get(i).toString().split(";");
                allConcepts.get(i).setText(values[0]);
                if(values[2].equals("0")){
                    allDebit.get(i).setText(values[1]);
                }else{
                    allCredit.get(i).setText(values[2]);
                }
            }

        }

    }

    public void clearDataInScreem(int i){
            allConcepts.get(i).setText("");
            allDebit.get(i).setText("");
            allCredit.get(i).setText("");
    }



}