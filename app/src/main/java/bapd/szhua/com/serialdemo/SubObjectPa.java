package bapd.szhua.com.serialdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author szhua on 2017/11/23/023.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         SubObjectPa
 *         请添加注释说明
 */

public class SubObjectPa implements Parcelable {

    String id ;
    String name ;

    public SubObjectPa(String id, String name) {
        this.id = id;
        this.name = name;
    }

    protected SubObjectPa(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<SubObjectPa> CREATOR = new Creator<SubObjectPa>() {
        @Override
        public SubObjectPa createFromParcel(Parcel in) {
            return new SubObjectPa(in);
        }
        @Override
        public SubObjectPa[] newArray(int size) {
            return new SubObjectPa[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
    }

    @Override
    public String toString() {
        return "SubObjectPa{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
