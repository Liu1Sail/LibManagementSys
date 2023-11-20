package bookmanager;

import java.util.Date;

public class BookCopies {
    Integer CopyID;
    Integer BookID;
    String CopyNumber;
    Date AcquisitionDate;
    Boolean On_shelfStatus;
    String BookLocation;
    Boolean IsVisible;

    @Override
    public String toString() {
        return "BookCopies{" +
                "CopyID=" + CopyID +
                ", BookID=" + BookID +
                ", CopyNumber='" + CopyNumber + '\'' +
                ", AcquisitionDate=" + AcquisitionDate +
                ", On_shelfStatus=" + On_shelfStatus +
                ", BookLocation='" + BookLocation + '\'' +
                ", IsVisible=" + IsVisible +
                '}';
    }
}
