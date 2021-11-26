package GraphicsProcessors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.TimeController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DbTAccount;

public class graphicsProccesorEconomy extends View {

    private DbTAccount dbTAccount;
    private TimeController timeController;
    //Temporal to save a money counts {"concept:$value","concept:$value","concept:$value"...}
    private List<String> listOfEconomyValuesBedit;
    private List<String> listOfEconomyValuesCredit;

    public graphicsProccesorEconomy(Context context) {
        super(context);
        timeController = new TimeController();
        dbTAccount = new DbTAccount(context);
        listOfEconomyValuesBedit = new ArrayList<>();
        listOfEconomyValuesCredit = new ArrayList<>();
    }

    public graphicsProccesorEconomy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timeController = new TimeController();
        dbTAccount = new DbTAccount(context);
        listOfEconomyValuesBedit = new ArrayList<>();
        listOfEconomyValuesCredit = new ArrayList<>();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //create a white background
        canvas.drawARGB(255,255,255,255);

        //Get canvas dimenssions
        int w = canvas.getWidth();
        int h = canvas.getHeight();


        //Divide in two parts windows debit and credit
        Paint brush = new Paint();
        brush.setARGB(255,100,100,100);
        brush.setStrokeWidth(8);
        brush.setTextSize(30);
        canvas.drawLine(0,((float) h/((float) 2)),w,((float) h/((float) 2)),brush);

        //Get information
        HashMap<String, Integer> information = dbTAccount.getTAccountsInfo(timeController.getCurrentYear());

        if(information.size()>0){


            //Get max value of debit
            int maxDebit = getTotalDebit(information);
            //Get max value of credit
            int maxCredit = getTotalCredit(information);
            //Calculate a totalMoney
            int totalMoney = maxDebit - (maxCredit);

            //Put DEBIT CREDIT GRAPHICS
            //Debit is green
            brush.setARGB(255,0,100,0);
            float dx0 = 0;
            float dy0 = 0;
            float dx1 = (w)*((float)maxDebit/(float)totalMoney);
            float dy1 = dy0 + 60;
            canvas.drawRect(dx0, dy0, dx1, dy1, brush);
            //Put Label debit
            brush.setARGB(255,255,255,255);
            canvas.drawText("$: "+maxDebit, (w*(float) 0.1), (dy1*(float) 0.7), brush);


            brush.setARGB(255,100,100,100);
            //put 9 concepts
            float xlblDebit = (w)*(float) 0.08;
            float ylblDebit = 0;
            float separationYDebit = ((float) h/ (float) 2)/(float) 12;
            for(int i=0;i<listOfEconomyValuesBedit.size();i++){

                if(i < 10) {
                    ylblDebit = (separationYDebit * (float) i) + (dy1 * (float) 2);
                    canvas.drawText(listOfEconomyValuesBedit.get(i), xlblDebit, ylblDebit, brush);
                }
            }





            //Credit is red
            brush.setARGB(255,255,0,0);
            float cx0 = 0;
            float cy0 = h*(float) 0.5;
            float cx1 = (w)*((float)((float)maxCredit*(float)-1)/(float)totalMoney);
            float cy1 = cy0 +60;
            canvas.drawRect(cx0, cy0, cx1, cy1, brush);
            //Put label credit
            brush.setARGB(255,255,255,255);
            canvas.drawText("$:"+maxCredit, (w*(float) 0.1), (cy1*(float) 0.975), brush);

            //put 9 concepts
            float xlblCredit = (w)*(float) 0.08;
            float ylblCredit = 0;
            float separationYCredit = ((float) h/ (float) 2)/(float) 12;

            brush.setARGB(255,100,100,100);
            for(int i=0;i<listOfEconomyValuesCredit.size();i++){

                if(i < 10) {
                    ylblCredit = ((separationYCredit * (float) i) + (dy1 * (float) 2)) + (h/(float) 2);
                    canvas.drawText(listOfEconomyValuesCredit.get(i), xlblCredit, ylblCredit, brush);
                }
            }






        }else{
            canvas.drawText("NO DATA", (w/6), (h/3), brush);
            canvas.drawText("NO DATA", (w/6), (h*(float)0.7), brush);
        }

    }

    public int getTotalDebit(HashMap<String, Integer> information){
        int sum = 0;

        for(String key: information.keySet()){

            if(information.get(key)>=0){
                listOfEconomyValuesBedit.add(key+":"+String.valueOf(information.get(key)));
                sum = sum + information.get(key);
            }
        }

        return sum;
    }

    public int getTotalCredit(HashMap<String, Integer> information){

        int sum = 0;

        for(String key: information.keySet()){

            if(information.get(key)<0){
                listOfEconomyValuesCredit.add(key+":"+String.valueOf(information.get(key)));
                sum = sum + information.get(key);
            }
        }

        return sum;
    }


}
