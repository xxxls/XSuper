package com.xxxxls.example.ui.tools.store;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Max
 * @date 1/29/21.
 */
class TestParcelable implements Parcelable {

    int iValue;
    String sValue;
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(iValue);
        parcel.writeString(sValue);
        // parcel.writeTypedList(list);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    TestParcelable(int i, String s) {
        iValue = i;
        sValue = s;
    }

    private TestParcelable(Parcel in) {
        iValue = in.readInt();
        sValue = in.readString();
    }

    @Override
    public String toString() {
        return "TestParcelable{" +
                "iValue=" + iValue +
                ", sValue='" + sValue + '\'' +
                '}';
    }

    public static final Creator<TestParcelable> CREATOR = new Creator<TestParcelable>() {
        @Override
        public TestParcelable createFromParcel(Parcel parcel) {
            return new TestParcelable(parcel);
        }

        @Override
        public TestParcelable[] newArray(int i) {
            return new TestParcelable[i];
        }
    };
}