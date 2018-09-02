package masterung.androidthai.in.th.tutornop.utility;

import android.os.Parcel;
import android.os.Parcelable;

public class MyModel implements Parcelable{

    private String displayNameString, idCardString, uidString;

    public MyModel() {
    }   // Constructor Getter

    public MyModel(String displayNameString, String idCardString, String uidString) {
        this.displayNameString = displayNameString;
        this.idCardString = idCardString;
        this.uidString = uidString;
    }   // Constructor Setter

    protected MyModel(Parcel in) {
        displayNameString = in.readString();
        idCardString = in.readString();
        uidString = in.readString();
    }

    public static final Creator<MyModel> CREATOR = new Creator<MyModel>() {
        @Override
        public MyModel createFromParcel(Parcel in) {
            return new MyModel(in);
        }

        @Override
        public MyModel[] newArray(int size) {
            return new MyModel[size];
        }
    };

    public String getDisplayNameString() {
        return displayNameString;
    }

    public void setDisplayNameString(String displayNameString) {
        this.displayNameString = displayNameString;
    }

    public String getIdCardString() {
        return idCardString;
    }

    public void setIdCardString(String idCardString) {
        this.idCardString = idCardString;
    }

    public String getUidString() {
        return uidString;
    }

    public void setUidString(String uidString) {
        this.uidString = uidString;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(displayNameString);
        dest.writeString(idCardString);
        dest.writeString(uidString);
    }
}   // Main Class
