package bookmanager;

import java.util.Date;

public class Books {
    Integer BookID;
    String Title;
    String ISBN;
    String Author;
    String Publisher;
    Date ReceiptDate;
    String Genre;
    Integer BookQuantity_Visible;
    Integer BookQuantity_Hidden;

    @Override
    public String toString() {
        return "Books{" +
                "BookID=" + BookID +
                ", Title='" + Title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", ReceiptDate=" + ReceiptDate +
                ", Genre='" + Genre + '\'' +
                ", BookQuantity_Visible=" + BookQuantity_Visible +
                ", BookQuantity_Hidden=" + BookQuantity_Hidden +
                '}';
    }
}
