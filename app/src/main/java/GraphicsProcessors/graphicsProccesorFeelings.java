package GraphicsProcessors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.TimeController;

import java.util.HashMap;

import db.DbFeeling;

public class graphicsProccesorFeelings extends View {

    private DbFeeling dbFeeling;
    private TimeController timeController;


    public graphicsProccesorFeelings(Context context) {
        super(context);
        timeController = new TimeController();
        dbFeeling = new DbFeeling(context);
    }

    public graphicsProccesorFeelings(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        timeController = new TimeController();
        dbFeeling = new DbFeeling(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //create a white background
        canvas.drawARGB(255,255,255,255);

        //to paint a feelings lbl
        Paint brush = new Paint();
        brush.setARGB(255,0,0,255);
        brush.setStrokeWidth(8);
        brush.setTextSize(30);
        HashMap<String, Integer> informacion = dbFeeling.getFeelingReport(timeController.getCurrentYear());
        if(!informacion.isEmpty()){
            int maxValue = maxValue(informacion);
            float w = canvas.getWidth();
            float h = canvas.getHeight();
            float separation = (float) h/(float) (informacion.size()+1);

            int counter = 1;
            float x0 = 20;
            float y0 = 0;
            //Put all labels feelings
            for(String feeling: informacion.keySet()){

                y0 = counter * separation;
                canvas.drawText(feeling, x0, y0+8, brush);

                counter = counter + 1;
            }

            int rcounter = 1;
            float rx0 = 200;
            float ry0 = 0;
            float rx1 = 0;
            float ry1 = 0;
            float maxXScale = (w-(w*(float) 0.2));

            //Put all values
            for(int value: informacion.values()){
                ry0 = rcounter * separation;
                float factorR = ((float)value/(float)maxValue);
                if(factorR<0.4){
                    rx1 = (maxXScale*factorR)+rx0;
                }else{
                    rx1 = (maxXScale*factorR);
                }
                ry1 = ry0 + 20;
                canvas.drawRect(rx0, ry0, rx1, ry1, brush);
                canvas.drawText("+"+value, maxXScale+50, ry0+8, brush);
                rcounter = rcounter + 1;
            }


        }else{
            canvas.drawText("NODATA", 300,300, brush);
        }








    }

    /***
     * return a max value of hash map
     * */
    public int maxValue(HashMap<String, Integer> map){

        int k = 0;

        for(int value: map.values()){
            if(value>k){
                k = value;
            }
        }

        return k;
    }

}
