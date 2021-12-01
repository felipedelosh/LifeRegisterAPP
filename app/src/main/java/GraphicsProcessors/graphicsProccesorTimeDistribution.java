package GraphicsProcessors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.TimeController;

import java.util.HashMap;

import db.DbTimeDistribution;

public class graphicsProccesorTimeDistribution extends View {

    private TimeController timeController;
    private DbTimeDistribution dbTimeDistribution;
    private RectF my0val; //To paint % in circle

    public graphicsProccesorTimeDistribution(Context context) {
        super(context);
        timeController = new TimeController();
        dbTimeDistribution = new DbTimeDistribution(context);
    }

    public graphicsProccesorTimeDistribution(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timeController = new TimeController();
        dbTimeDistribution = new DbTimeDistribution(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        timeController = new TimeController();

        //create a white background
        canvas.drawARGB(255,255,255,255);
        //Create a main circle
        Paint brush = new Paint();
        brush.setTextSize(30);
        brush.setARGB(255,155,155,155);
        brush.setStrokeWidth(8);

        //get canvas dimensions
        float w = canvas.getWidth();
        float h = canvas.getHeight();
        float radius = w/(float) 2.5;

        float x0 = w / (float) 2;
        float y0 = h/(float) 4;
        canvas.drawCircle(x0, y0, radius, brush);

        HashMap<String, Integer> information = dbTimeDistribution.getDataInfo(timeController.getCurrentYear());
        //OrderInfo
        //information = orderInformation(information);


        if(information.size()>0){

            //get max value
            int maxValue = maxValue(information);
            //get total value
            int total = total(information);
            //canvas.drawText("M:"+total, 400, 700, brush);
            //Create a oval

            /***
             * Calculate a new dimensions of Rect
             *
             */

            my0val = new RectF();
            float x0MyRect = x0 - radius;
            float y0MyRect = y0 - radius;
            float x1MyRect = x0MyRect+radius+radius;
            float y1MyRect = y0MyRect+radius+radius;
            float yLabels = h/(float)1.8;
            float yIterator = yLabels /(float) 15;
            my0val.set(x0MyRect,y0MyRect,x1MyRect,y1MyRect);



            //Calculate a Arcts
            float startAngle = 0;
            float endAngle = 0;
            int counter = 0;
            for(String key: information.keySet()){
                //The end angle is based in percent
                // 360 = MaxValue
                //  x  = iValue

                //Percent of count
                float percent = ((float)information.get(key)/(float) total);

                //Percent of circle
                float percentS = ((float) 360)* (float) percent;

                endAngle = startAngle + percentS;

                int [] color = colors(counter);
                brush.setARGB(255,color[0],color[1],color[2]);
                canvas.drawArc(my0val, startAngle, percentS,true, brush);

                if(counter<12) {
                    //Put some labels
                    canvas.drawText(key, w * (float) 0.1, yLabels + ((float) counter * yIterator), brush);
                    canvas.drawText("% " + percent, w * (float) 0.6, yLabels + ((float) counter * yIterator), brush);
                }

                startAngle = endAngle;

                counter = counter + 1;
            }

        }else{
            canvas.drawText("Error 404", w/(float) 2.5, h/(float) 1.8, brush);
        }

    }

    /***
     *
     * @param information
     * @return a max value Integer of HashMap
     */
    public int maxValue(HashMap<String, Integer> information){

        int k = 0;

        for(int value: information.values()){
            if(value>k){
                k = value;
            }
        }

        return k;
    }

    /***
     *
     * @param information
     * @return return a total of hashMap
     */
    public int total(HashMap<String, Integer> information){
        int k = 0;

        for(int value: information.values()){
            k = k + value;
        }

        return k;
    }


    public int [] colors(int i) {

        i = i % 10;

        int[] color = new int[3];

        color[0] = 45;
        color[1] = 135;
        color[2] = 200;

        //rosa mexicano
        if (i == 0) {
            color[0] = 288;
            color[1] = 0;
            color[2] = 124;
        }

        //verde-cian
        if (i == 1) {
            color[0] = 27;
            color[1] = 255;
            color[2] = 131;
        }

        //coral
        if (i == 2) {
            color[0] = 229;
            color[1] = 29;
            color[2] = 46;
        }


        //chocolate
        if (i == 3) {
            color[0] = 69;
            color[1] = 50;
            color[2] = 46;
        }

        //yellow
        if (i == 4) {
            color[0] = 255;
            color[1] = 255;
            color[2] = 46;
        }

        //Azul
        if (i == 5) {
            color[0] = 0;
            color[1] = 0;
            color[2] = 255;
        }

        //viridián
        if (i == 5) {
            color[0] = 0;
            color[1] = 130;
            color[2] = 90;
        }

        //negro intenso
        if (i == 6) {
            color[0] = 10;
            color[1] = 10;
            color[2] = 10;
        }

        //magenta
        if (i == 7) {
            color[0] = 252;
            color[1] = 68;
            color[2] = 240;
        }

        //verde-cian
        if (i == 8) {
            color[0] = 0;
            color[1] = 190;
            color[2] = 75;
        }

        //beige
        if (i == 9) {
            color[0] = 239;
            color[1] = 231;
            color[2] = 219;
        }

        //Lila oscuro
        if(i == 10){
            color[0] = 108;
            color[1] = 70;
            color[2] = 177;
        }

        return color;
    }

    public HashMap<String, Integer> orderInformation(HashMap<String, Integer> information){


        HashMap<String, Integer> orderInfo = new HashMap<>();
        int size = information.size();
        String [] keys = new String[size];
        int [] values = new int[size];

        //Etract info
        int counter = 0;
        for(String key: information.keySet()){
            keys[counter] = key;
            values[counter] = information.get(key);

            counter = counter + 1;
        }

        String tempS = "";
        int tempI = 0;
        for(int i=0;i<information.size();i++){

            for(int j=0;j<information.size()-1;j++){

                if(values[j] < values[j+1]){
                    //Save
                    tempS = keys[j+1];
                    tempI = values[j+1];
                    //Copy
                    keys[j+1] = keys[j];
                    values[j+1] = values[1];
                    //Backup
                    keys[j] = tempS;
                    values[j] = tempI;

                }
            }

        }

        for(int i=0;i< keys.length;i++){
            orderInfo.put(keys[i], values[i]);
        }



        return orderInfo;
    }

}
