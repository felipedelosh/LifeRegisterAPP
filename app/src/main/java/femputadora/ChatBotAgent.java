package femputadora;


import android.content.Context;

import com.example.liferegisterdiary.R;

import models.User;

/****
 * This is a main interface
 *
 */

public class ChatBotAgent {

    private Context context;

    private User user;

    //Load words
    private String [] grettings;



    public ChatBotAgent(Context context, User user) {
        this.context = context;
        grettings = context.getResources().getStringArray(R.array.grettings);
        this.user = user;
    }

    /***
     * Enter a sms and the the machine response
     * @return "Bla bla bla"
     */
    public String responseSMS(String sms){

        //If the user is gretting
        for(int i=0;i<grettings.length;i++){
            if(sms.trim().equals(grettings[i])){
                int nextGretting = (int) (grettings.length * Math.random());
                return grettings[nextGretting] + " " + user.getUsername();
            }
        }


        return "Bla bla bla";
    }

}
