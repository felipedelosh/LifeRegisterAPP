package models;


import android.widget.EditText;

public class ItemTaccont {

    private int idTaccount;
    private String concept;
    private int debit;
    private int credit;
    private EditText listViewEconomyConcept;
    private EditText listViewEconomyDebit;
    private EditText listViewEconomyCredit;

    public ItemTaccont(int idTaccount, String concept, int debit, int credit) {
        this.idTaccount = idTaccount;
        this.concept = concept;
        this.debit = debit;
        this.credit = credit;
    }

    public EditText getListViewEconomyConcept() {
        return listViewEconomyConcept;
    }

    public void setListViewEconomyConcept(EditText listViewEconomyConcept) {
        this.listViewEconomyConcept = listViewEconomyConcept;
    }

    public EditText getListViewEconomyDebit() {
        return listViewEconomyDebit;
    }

    public void setListViewEconomyDebit(EditText listViewEconomyDebit) {
        this.listViewEconomyDebit = listViewEconomyDebit;
    }

    public EditText getListViewEconomyCredit() {
        return listViewEconomyCredit;
    }

    public void setListViewEconomyCredit(EditText listViewEconomyCredit) {
        this.listViewEconomyCredit = listViewEconomyCredit;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getIdTaccount() {
        return idTaccount;
    }

    public void setIdTaccount(int idTaccount) {
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
