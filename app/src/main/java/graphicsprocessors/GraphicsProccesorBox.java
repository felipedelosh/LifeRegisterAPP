package graphicsprocessors;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.liferegisterdiary.TimeController;

import java.util.List;

import db.DbBox;

public class GraphicsProccesorBox extends View {

    private DbBox dbBox;
    private TimeController timeController;

    public GraphicsProccesorBox(Context context) {
        super(context);
        dbBox = new DbBox(context);
        timeController = new TimeController();
    }

    public GraphicsProccesorBox(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dbBox = new DbBox(context);
        timeController = new TimeController();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //create a white background
        canvas.drawARGB(255,255,255,255);


        //Paint a little box status
        Paint brush = new Paint();
        brush.setARGB(255,0,0,255);
        brush.setStrokeWidth(8);
        //Get date
        String date = timeController.getCurrentYear();
        //Get maximun value
        List<Integer> littleBoxvalues = dbBox.getAllCurrentValuesOfLitleBox(date);
        if(littleBoxvalues.size()>0){
            brush.setTextSize(30);
            int maxValue = maxValue(littleBoxvalues);
            //Get canvas dimensions
            float w = canvas.getWidth();
            float h = canvas.getHeight();
            //Paint max value
            canvas.drawText("$"+maxValue, 20,50, brush);
            //Paint medium
            canvas.drawText("$"+Math.round(maxValue/2), 20,h/2, brush);
            //paint min
            canvas.drawText("$0", 20,h-50, brush);

            //I need a separation scale to graph
            float separationScale = w/littleBoxvalues.size();

            //Graph points
            for(int i=0;i<=littleBoxvalues.size()-1;i++){

                if(i==0){
                    float x0 =  0;
                    float y0 =  h;
                    float x1 = separationScale * (i+1);
                    float y1 =  h - ( h * ((float) littleBoxvalues.get(i)/(float) maxValue));
                    canvas.drawLine(x0,y0,x1,y1,brush);
                }else{
                    float x0 = separationScale * i;
                    float y0 =  h - ( h * ((float) littleBoxvalues.get(i-1)/(float) maxValue));
                    float x1 = separationScale * (i+1);
                    float y1 =  h - ( h * ((float) littleBoxvalues.get(i)/(float) maxValue));
                    canvas.drawLine(x0,y0,x1,y1,brush);
                }


            }

        }//End to piant a litle box values


        //Paint big box values
        List<Integer> bigBoxValues = dbBox.getAllCurrentValuesOfBigBox(date);
        if(bigBoxValues.size() > 0){
            brush.setARGB(255,0,255,0);
            int maxValue = maxValue(bigBoxValues);
            //Get canvas dimensions
            int w = canvas.getWidth();
            int h = canvas.getHeight();
            //Paint max value
            canvas.drawText("$"+maxValue, w*(float)0.8,50, brush);
            //Paint medium
            canvas.drawText("$"+Math.round((float) maxValue/2), w*(float)0.8,(float) h/2, brush);
            //paint min
            canvas.drawText("$0", w*(float)0.8,(float) h-50, brush);

            //I need a separation scale to graph
            float separationScale = w/(float)bigBoxValues.size();

            //Graph points
            for(int i=0;i<=bigBoxValues.size()-1;i++){

                if(i==0){
                    float x0 =  0;
                    float y0 =  h;
                    float x1 = separationScale * (i+1);
                    float y1 =  h - ( h * ((float) bigBoxValues.get(i)/(float) maxValue));
                    canvas.drawLine(x0,y0,x1,y1,brush);
                }else{
                    float x0 = separationScale * i;
                    float y0 = h - ( h * ((float) bigBoxValues.get(i-1)/(float) maxValue));
                    float x1 = separationScale * (i+1);
                    float y1 =  h - ( h * ((float) bigBoxValues.get(i)/(float) maxValue));
                    canvas.drawLine(x0,y0,x1,y1,brush);
                }
            }

        }




    }

    private int maxValue(List<Integer> values){

        int max = 0;
        for(int i=0;i<values.size();i++){
            if(values.get(i)>max){
                max = values.get(i);
            }
        }
        return max;
    }
}
