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
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import db.DbTAccount;

public class TAccounts extends AppCompatActivity {

    private Spinner spinnerDays;
    private Spinner spinnerMonth;
    private ArrayList<EditText> allConcepts;
    private ArrayList<EditText> allDebit;
    private ArrayList<EditText> allCredit;

    private TimeController timeController;
    private DbTAccount dbTAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taccounts);

        Context context = this;
        timeController = new TimeController();
        dbTAccount = new DbTAccount(context);


        allConcepts = new ArrayList<>();
        allDebit = new ArrayList<>();
        allCredit = new ArrayList<>();
        initAllEdittext();

        spinnerDays = findViewById(R.id.spinnerDays);
        spinnerMonth = findViewById(R.id.spinnerMonth);


        putAMonthsInSpinner();
        //Put all days of current mounth
        refreshDaysOfSpinner();
        //Put currently mont in spinner
        spinnerMonth.setSelection(timeController.getNumberOfCurrentMounth());

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               refreshDaysOfSpinner();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });

        setupView();
    }

    private void setupView() {

        Button btnLoadTAccount = findViewById(R.id.btnLoadTAccount);
        btnLoadTAccount.setOnClickListener(v -> loadTaacountReport());
        Button btnSaveTAccount = findViewById(R.id.btnSaveTAccount);
        btnSaveTAccount.setOnClickListener(v -> {
            if(validateFliendsOdEconomyReport()){
                saveEconomyRegisterInDatabase();
            }
        });


    }

    public void initAllEdittext(){
        EditText listViewEconomyConcept1 = findViewById(R.id.listViewEconomyConcept1);
        allConcepts.add(listViewEconomyConcept1);
        EditText listViewEconomyDebit1 = findViewById(R.id.listViewEconomyDebit1);
        allDebit.add(listViewEconomyDebit1);
        EditText listViewEconomyCredit1 = findViewById(R.id.listViewEconomyCredit1);
        allCredit.add(listViewEconomyCredit1);


        EditText listViewEconomyConcept2 = findViewById(R.id.listViewEconomyConcept2);
        allConcepts.add(listViewEconomyConcept2);
        EditText listViewEconomyDebit2 = findViewById(R.id.listViewEconomyDebit2);
        allDebit.add(listViewEconomyDebit2);
        EditText listViewEconomyCredit2 = findViewById(R.id.listViewEconomyCredit2);
        allCredit.add(listViewEconomyCredit2);

        EditText listViewEconomyConcept3 = findViewById(R.id.listViewEconomyConcept3);
        allConcepts.add(listViewEconomyConcept3);
        EditText listViewEconomyDebit3 = findViewById(R.id.listViewEconomyDebit3);
        allDebit.add(listViewEconomyDebit3);
        EditText listViewEconomyCredit3 = findViewById(R.id.listViewEconomyCredit3);
        allCredit.add(listViewEconomyCredit3);

        EditText listViewEconomyConcept4 = findViewById(R.id.listViewEconomyConcept4);
        allConcepts.add(listViewEconomyConcept4);
        EditText listViewEconomyDebit4 = findViewById(R.id.listViewEconomyDebit4);
        allDebit.add(listViewEconomyDebit4);
        EditText listViewEconomyCredit4 = findViewById(R.id.listViewEconomyCredit4);
        allCredit.add(listViewEconomyCredit4);

        EditText listViewEconomyConcept5 = findViewById(R.id.listViewEconomyConcept5);
        allConcepts.add(listViewEconomyConcept5);
        EditText listViewEconomyDebit5 = findViewById(R.id.listViewEconomyDebit5);
        allDebit.add(listViewEconomyDebit5);
        EditText listViewEconomyCredit5 = findViewById(R.id.listViewEconomyCredit5);
        allCredit.add(listViewEconomyCredit5);

        EditText listViewEconomyConcept6 = findViewById(R.id.listViewEconomyConcept6);
        allConcepts.add(listViewEconomyConcept6);
        EditText listViewEconomyDebit6 = findViewById(R.id.listViewEconomyDebit6);
        allDebit.add(listViewEconomyDebit6);
        EditText listViewEconomyCredit6 = findViewById(R.id.listViewEconomyCredit6);
        allCredit.add(listViewEconomyCredit6);

        EditText listViewEconomyConcept7 = findViewById(R.id.listViewEconomyConcept7);
        allConcepts.add(listViewEconomyConcept7);
        EditText listViewEconomyDebit7 = findViewById(R.id.listViewEconomyDebit7);
        allDebit.add(listViewEconomyDebit7);
        EditText listViewEconomyCredit7 = findViewById(R.id.listViewEconomyCredit7);
        allCredit.add(listViewEconomyCredit7);

        EditText listViewEconomyConcept8 = findViewById(R.id.listViewEconomyConcept8);
        allConcepts.add(listViewEconomyConcept8);
        EditText listViewEconomyDebit8 = findViewById(R.id.listViewEconomyDebit8);
        allDebit.add(listViewEconomyDebit8);
        EditText listViewEconomyCredit8 = findViewById(R.id.listViewEconomyCredit8);
        allCredit.add(listViewEconomyCredit8);

        EditText listViewEconomyConcept9 = findViewById(R.id.listViewEconomyConcept9);
        allConcepts.add(listViewEconomyConcept9);
        EditText listViewEconomyDebit9 = findViewById(R.id.listViewEconomyDebit9);
        allDebit.add(listViewEconomyDebit9);
        EditText listViewEconomyCredit9 = findViewById(R.id.listViewEconomyCredit9);
        allCredit.add(listViewEconomyCredit9);

        EditText listViewEconomyConcept10 = findViewById(R.id.listViewEconomyConcept10);
        allConcepts.add(listViewEconomyConcept10);
        EditText listViewEconomyDebit10 = findViewById(R.id.listViewEconomyDebit10);
        allDebit.add(listViewEconomyDebit10);
        EditText listViewEconomyCredit10 = findViewById(R.id.listViewEconomyCredit10);
        allCredit.add(listViewEconomyCredit10);

    }


    public void putAMonthsInSpinner(){
        spinnerMonth = findViewById(R.id.spinnerMonth);
        List<String> months = timeController.getCurrentMonths();
        ArrayAdapter <String> adapterSpinnerMounth = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, months);
        spinnerMonth.setAdapter(adapterSpinnerMounth);
        //Put a Mounth in spinner
        spinnerMonth.setSelection(timeController.getNumberOfCurrentMounth());
    }

    public void refreshDaysOfSpinner(){

        String nameMonth = spinnerMonth.getSelectedItem().toString().trim();
        int days = timeController.getNumberDaysOfMounthX(nameMonth);

        String [] spnerDays = new String[days];
        for(int i=0;i<days;i++){
            spnerDays[i] = String.valueOf(i+1);
        }

        ArrayAdapter <String> adapterSpinnerDays = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spnerDays);
        spinnerDays.setAdapter(adapterSpinnerDays);

        //If currentle month put currentle day
        if(nameMonth.equals(timeController.getCurrentMonth())){
            spinnerDays.setSelection(timeController.getCurrentDayNumberOfMount()-1);
        }
    }

    //Valitate if the text is rigth
    public boolean validateText(String txt){
        return !txt.equals("") && !txt.trim().equals("") && txt.trim().length() > 0;
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
                if(!moneyIsEmpty(debit)||!moneyIsEmpty(credit)){
                    //Not write both debit and credit
                    if(!moneyIsEmpty(debit)&&!moneyIsEmpty(credit)){
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
        String timeStampMM = spinnerMonth.getSelectedItem().toString().trim();
        String timeStampDD = spinnerDays.getSelectedItem().toString().trim();
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
        String timeStampMM = spinnerMonth.getSelectedItem().toString().trim();
        String timeStampDD = spinnerDays.getSelectedItem().toString().trim();
        String timeStamp = timeStampYYYY+":"+timeStampMM+":"+timeStampDD;

        List<String> information = dbTAccount.getTAccountByTimeStamp(timeStamp);

        if(information.size()>0){
            //Delete all info in scrrem
            for(int i=0;i<allConcepts.size();i++){
                clearDataInScreem(i);
            }
            //Put info in screem
            for(int i=0;i<information.size();i++){
                String [] values = information.get(i).split(";");
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