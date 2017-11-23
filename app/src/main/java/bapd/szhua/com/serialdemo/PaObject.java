package bapd.szhua.com.serialdemo;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author szhua on 2017/11/23/023.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         PaObject
 *         请添加注释说明
 */

public class PaObject implements Parcelable {

    private int num ;
    private String name ;
    private String url ;
    private SubObjectPa object;

    public PaObject(int num, String name, String url, SubObjectPa object) {
        this.num = num;
        this.name = name;
        this.url = url;
        this.object = object;
    }

    protected PaObject(Parcel in) {
        num = in.readInt();
        name = in.readString();
        url = in.readString();
        object = in.readParcelable(SubObjectPa.class.getClassLoader());
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(num);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeParcelable(object, flags);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<PaObject> CREATOR = new Creator<PaObject>() {
        @Override
        public PaObject createFromParcel(Parcel in) {
            return new PaObject(in);
        }

        @Override
        public PaObject[] newArray(int size) {
            return new PaObject[size];
        }
    };

    @Override
    public String toString() {
        return "PaObject{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", object=" + object +
                '}';
    }
}
