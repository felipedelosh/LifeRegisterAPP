package Models;

import android.widget.TextView;

public class ItemTaccont {

   public String idTaccount;
   public String concept;
   public int debit;
   public int credit;

    public ItemTaccont(String idTaccount, String concept, int debit, int credit) {
        this.idTaccount = idTaccount;
        this.concept = concept;
        this.debit = debit;
        this.credit = credit;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getIdTaccount() {
        return idTaccount;
    }

    public void setIdTaccount(String idTaccount) {
        this.idTaccount = idTaccount;
    }

    public int getDebit() {
        return debit;
    }

    public void setDebit(int debit) {
        this.debit = debit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
