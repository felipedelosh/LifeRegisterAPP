package graphicsprocessors;

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

import db.DbBox;

/**
 * Show a Economy box in last 8 years
 *
 * **/

public class GraphicsProccesorBoxEconomy extends View {

    private TimeController timeController;
    private DbBox dbBox;

    public GraphicsProccesorBoxEconomy(Context context) {
        super(context);
        timeController = new TimeController();
        dbBox = new DbBox(context);
    }

    public GraphicsProccesorBoxEconomy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timeController = new TimeController();
        dbBox = new DbBox(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //create a white background
        canvas.drawARGB(255,255,255,255);

        HashMap<String, Integer> information = dbBox.getALLInfoBoxes();

        Paint brush = new Paint();
        brush.setARGB(255,100,100,100);
        brush.setStrokeWidth(8);
        brush.setTextSize(40);

        //Get canvas dimensions
        float w = canvas.getWidth();
        float h = canvas.getHeight();

        if(information.size()>0) {

            List<String> getInformation = getBoxValueInformation(information);
            int maxValueBigBox = maxValueBigBox(getInformation);
            int maxValueLitleBox = maxValueLitleBox(getInformation);


            //Puts labels Big Box litle Box
            brush.setARGB(255,0,0,255);
            canvas.drawRect((w*(float)0.06), (h*(float)0.02), (w*(float)0.1), (h*(float)0.04), brush);
            canvas.drawText("Litle Box", (w*(float)0.12), (h*(float)0.04), brush);
            brush.setARGB(255,0,255,0);
            canvas.drawRect((w*(float)0.56), (h*(float)0.02), (w*(float)0.6), (h*(float)0.04), brush);
            canvas.drawText("Big Box", (w*(float)0.62), (h*(float)0.04), brush);

            float separation = h / (float) 8;
            for(int i=0;i<getInformation.size();i++){

                if(i<7){

                    //Put label year
                    brush.setARGB(255,100,100,100);
                    float x0LblYear = w * (float) 0.05;
                    float y0LblYear = separation * (i+1);
                    canvas.drawText(getInformation.get(i).split(":")[0], x0LblYear, y0LblYear, brush);
                    //Put big box
                    float bx0 = (w * (float) 0.15) * (float) 1.3;
                    float by0 = ((separation * (i+1))) - ( 50) ;
                    float bx1 = bx0 + (w * (float) 0.6);
                    int currentlY = Integer.parseInt(getInformation.get(i).split(":")[1]);
                    bx1 = bx1 * ((float) currentlY/ (float) maxValueBigBox);
                    float by1 = by0 + 15;

                    brush.setARGB(255,0,255,0);
                    canvas.drawRect(bx0, by0, bx1, by1, brush);
                    //Put Litle Box
                    float lx0 = bx0;
                    float ly0 = ((separation * (float) (i+1)));
                    float lx1 = bx0 + (w * (float) 0.6);
                    currentlY = Integer.valueOf(getInformation.get(i).split(":")[2]);
                    lx1 = lx1 * ((float) currentlY/ (float) maxValueLitleBox);
                    float ly1 = ly0 +  15;
                    brush.setARGB(255,0,0,255);
                    canvas.drawRect(lx0, ly0, lx1, ly1, brush);
                }

            }

        }else{
            canvas.drawText("Error 404", w/(float) 2.2, h / (float) 2, brush);
        }
    }

    /**
     * Enter hashMap <Tipe:Year:Date, value>
     * output
     * <Year><bigBox:average><litleBox:averange>
     * */
    public List<String> getBoxValueInformation(HashMap<String, Integer> information){

        //This list return a general information
        List<String> info = new ArrayList<>();
        //To save big box information <Year, value>
        HashMap<String, List<Integer>> bigBoxInfo = new HashMap<>();
        //To save litle box information <Year, Value>
        HashMap<String, List<Integer>> litleBoxInfo = new HashMap<>();


        //Group all data
        for(String key: information.keySet()){


            if(key.split(":")[0].equals("LITLE")){

                String year = key.split(":")[1];

                if(litleBoxInfo.get(year) == null){
                    List<Integer> newArray = new ArrayList<>();
                    litleBoxInfo.put(year, newArray);
                    litleBoxInfo.get(year).add(information.get(key));
                }else{
                    litleBoxInfo.get(year).add(information.get(key));
                }

            }else{

                String year = key.split(":")[1];

                if(bigBoxInfo.get(year) == null){
                    List<Integer> newArray = new ArrayList<>();
                    bigBoxInfo.put(year, newArray);
                    bigBoxInfo.get(year).add(information.get(key));
                }else{
                    bigBoxInfo.get(year).add(information.get(key));
                }

            }

        }

        //To save "Year", "AverangeBig":"AverangeLitle" in temporal
        HashMap<String, String> toEndInfo = new HashMap<>();
        //Put big values in info
        //Put litle values in info
        for(String key: bigBoxInfo.keySet()) {

            //Calculate the averange
            int accumulator = 0;

            for (int i = 0; i < bigBoxInfo.get(key).size(); i++) {
                accumulator = accumulator + bigBoxInfo.get(key).get(i);
            }

            accumulator = accumulator / litleBoxInfo.get(key).size();

            //Save
            toEndInfo.put(key, String.valueOf(accumulator));
        }

        //Put <Year><bigBox:average><litleBox:averange>
        for(String key: litleBoxInfo.keySet()){

            //Calculate the averange
            int accumulator = 0;

            for(int i=0; i<litleBoxInfo.get(key).size(); i++){
                accumulator = accumulator + litleBoxInfo.get(key).get(i);
            }

            accumulator = accumulator / litleBoxInfo.get(key).size();

            //Save
            if(toEndInfo.get(key) == null){
                toEndInfo.put(key, String.valueOf(accumulator));
            }else{
                String temp = toEndInfo.get(key);
                temp = temp + ":" + accumulator;
                toEndInfo.put(key, temp);
            }
        }

        //Retrun real information
        for(String key: toEndInfo.keySet()){
            info.add(key+":"+toEndInfo.get(key));
        }

        orderInfoASC(info);

        return info;
    }



    /**
     * Order all info
     * <Year><bigBox:average><litleBox:averange>
     * */
    public void orderInfoASC(List<String> information){

        for(int i=0;i<information.size();i++){
            for(int j=0;j<information.size()-1;j++){

                int valueA = Integer.parseInt(information.get(j).split(":")[0]);
                int valueB = Integer.parseInt(information.get(j+1).split(":")[0]);

                String temp = "";

                if(valueA < valueB){

                    temp = information.get(j+1);
                    information.set(j+1, information.get(j));
                    information.set(j, temp);

                }
            }
        }
    }


    /***
     *
     * @param information <Year><bigBox:average><litleBox:averange>
     * @return Max value <BigBox>
     */
    public int maxValueBigBox(List<String> information){

        int k = 0;


        for(int i=0;i<information.size();i++){

            int value = Integer.parseInt(information.get(i).split(":")[1]);

            if(value > k){
                k = value;
            }

        }


        return k;
    }

    /***
     *
     * @param information <Year><bigBox:average><litleBox:averange>
     * @return Max value <litleBox>
     */
    public int maxValueLitleBox(List<String> information){

        int k = 0;


        for(int i=0;i<information.size();i++){

            int value = Integer.parseInt(information.get(i).split(":")[2]);

            if(value > k){
                k = value;
            }

        }


        return k;
    }


}
