package femputadora;


import android.content.Context;
import com.example.liferegisterdiary.R;
import com.example.liferegisterdiary.TimeController;

import java.util.Random;

import db.DbEvaluateActivity;
import db.DbQualifyDay;
import db.DbTimeDistribution;
import models.User;

/****
 * This is a main interface
 *
 */

public class ChatBotAgent {

    private Context context;
    private TimeController timeController;
    private User user;

    //Here load a response
    private String response;

    //To chatBot Personality
    private int countSMS;
    private int timeResponse;
    private int behavior;

    //Mode
    //To make a questions
    private boolean makeAQuestion;

    //Load words
    private String [] grettings;
    private String [] questions01;
    private String [] questions02;
    private String [] questions03;
    private String [] yes;
    private String [] not;
    private String [] happyAnswers;
    private String [] unHapinnesAnswers;
    private String [] fillers;

    //Load database
    private DbTimeDistribution dbTimeDistribution;
    private DbQualifyDay dbQualifyDay;
    private DbEvaluateActivity dbEvaluateActivity;

    //Type of answer
    private boolean yesornot;
    private boolean onetoten;
    private String temp;






    public ChatBotAgent(Context context, User user) {
        this.context = context;
        grettings = context.getResources().getStringArray(R.array.grettings);
        questions01 = context.getResources().getStringArray(R.array.question_01);
        questions02 = context.getResources().getStringArray(R.array.question_02);
        questions03 = context.getResources().getStringArray(R.array.question_03);
        happyAnswers = context.getResources().getStringArray(R.array.happy);
        unHapinnesAnswers = context.getResources().getStringArray(R.array.unhappiness);
        yes =  context.getResources().getStringArray(R.array.yes);
        not = context.getResources().getStringArray(R.array.not);
        fillers = context.getResources().getStringArray(R.array.fillers);
        this.user = user;

        //Database
        dbTimeDistribution = new DbTimeDistribution(context);
        dbQualifyDay = new DbQualifyDay(context);
        dbEvaluateActivity = new DbEvaluateActivity(context);
        timeController = new TimeController();

        response = "";
        timeResponse = 2000;
        behavior = 0;
        makeAQuestion = false;

        yesornot = false;
        onetoten = false;

        countSMS = 0;
        temp = "";

    }

    /***
     * Enter a sms and the the machine response
     * in self.response
     */
    public void enterSMS(String sms){

        if(systemIsFree()){
            //If the user is gretting
            for(int i=0;i<grettings.length;i++){
                if(sms.trim().equals(grettings[i])){
                    sayHello();
                    countSMS++;
                }
            }

        }else{
            //The system make a question
            if(behavior == 1){
                
                //Answer 1 to 10
                if(onetoten){
                    try{
                        int k = Integer.parseInt(sms);

                        if(k>5 && k<=10){
                            Speak("sayHappinnes");
                        }else{
                            Speak("sayUnHappinnes");
                        }

                        if (k>=0 && k<=10){
                            save1to10Question(k);
                        }

                    }catch (Exception e){
                        response = ":/";
                        countSMS++;
                    }
                    onetoten = false;
                }


                //Aswer yes or not
                if(yesornot) {

                    //Is a positive answer
                    for(int i=0; i< yes.length; i++){
                        if(sms.equals(yes[i])) {
                            saveYESORNOTQuestion("yes");
                            Speak("sayHappinnes");
                        }
                    }

                    if(yesornot) {
                        //Is a negative answer
                        for (int j = 0; j < not.length; j++) {
                            if (sms.equals(not[j])) {
                                saveYESORNOTQuestion("no");
                                Speak("sayFiller");
                            }
                        }
                    }
                    yesornot = false;
                }


                makeAQuestion = false;
            }
        }
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getTimeResponse() {
        return timeResponse;
    }

    public void setTimeResponse(int timeResponse) {
        this.timeResponse = timeResponse;
    }

    public void eraseSMS(){
        setResponse("");
    }


    //Behavoir
    public void generateBehavior(){

        if(systemIsFree()){
            //throw the dice
            int k = getRandonInRange(1, 5);

            //Make a Question
            if(k==1 & countSMS > 1){
                behavior = 1;
                makeAQuestion = true;
                Speak("sayQuestion");
            }

            //
            if(k == 2  && countSMS < 2){
                behavior = 2;
                Speak("sayHello");
            }


        }
        //Change a sleep time to response
        setTimeResponse(getRandonInRange(3000, 9000));
    }

    public int getRandonInRange(int min, int max){
        int random = new Random().nextInt((max - min) + 1) + min;
        return  random;
    }

    /***
     * Say if system not hav work
     * @return
     */
    public boolean systemIsFree(){
        return !makeAQuestion;
    }


    /***
     * Here the chatbot speat whit String code
     *
     * @param code
     */
    public void Speak(String code){

        if(code.equals("sayQuestion"))
        {
            sayQuestion();
        }

        if(code.equals("sayHello"))
        {
            sayHello();
        }

        if(code.equals("sayHappinnes"))
        {
            sayHappinnes();
        }

        if(code.equals("sayUnHappinnes"))
        {
            sayUnHappinnes();
        }

        if(code.equals("sayFiller"))
        {
            sayFiller();
        }



        countSMS = countSMS + 1;
    }

    public void sayQuestion(){
        //Throw the dice
        int k = getRandonInRange(1, 6);
        //This cuestions be astract
        if(k == 1){
            int nextQ = (int) (questions01.length * Math.random());
            response = questions01[nextQ];
        }
        //This questions abouts you feel response 1 to 10
        if(k == 2  && !dbQualifyDay.todayYouQuestion(timeController.timeStamp())){
            int nextQ = (int) (questions02.length * Math.random());
            onetoten = true;
            response = questions02[nextQ];
        }
        //This questions Abouts you enjoy activities yes or not
        if(k > 3){
            int nextQ = (int) (questions03.length * Math.random());
            yesornot = true;
            temp = dbTimeDistribution.getRndActiviry();
            String item = context.getString(R.string.itemPUT, temp);
            response = questions03[nextQ] + " " + item + " " + context.getString(R.string.yesornot);
        }
    }

    //Speak
    public void sayHello(){
        int sayName = getRandonInRange(1, 10);
        int nextGretting = (int) (grettings.length * Math.random());
        if(sayName > 6){
            response = grettings[nextGretting] + " " + user.getUsername();
        }else{
            response = grettings[nextGretting];
        }
    }

    public void sayHappinnes(){
        //Throw the dice
        int sayName = getRandonInRange(1, 10);
        int nextTXT = (int) (happyAnswers.length * Math.random());
        if(sayName > 7){
            response = happyAnswers[nextTXT] + " " + user.getUsername();
        }else{
            response = happyAnswers[nextTXT];
        }
    }

    public void sayUnHappinnes(){
        int sayName = getRandonInRange(1, 10);
        int nextTXT = (int) (unHapinnesAnswers.length * Math.random());
        if(sayName > 8){
            response = unHapinnesAnswers[nextTXT] + " " + user.getUsername();
        }else{
            response = unHapinnesAnswers[nextTXT];
        }
    }

    public void sayFiller(){
        int nextTXT = (int) (fillers.length * Math.random());
        response = fillers[nextTXT];

        countSMS = countSMS + 1;
    }

    public void save1to10Question(int value){
        try {
            dbQualifyDay.insertDayQualify(timeController.timeStamp(), value);
        }catch (Exception e){
            //Do nothing
        }
    }

    public void saveYESORNOTQuestion(String evaluate){
        try {
            dbEvaluateActivity.insertEvaluateActivityYESORNOT(temp, timeController.timeStamp(), evaluate);
        }catch (Exception e){
            //Do nothing
        }
    }


}
