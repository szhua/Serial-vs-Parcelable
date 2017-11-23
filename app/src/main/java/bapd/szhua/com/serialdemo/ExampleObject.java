package bapd.szhua.com.serialdemo;

import com.twitter.serial.serializer.ObjectSerializer;
import com.twitter.serial.serializer.SerializationContext;
import com.twitter.serial.stream.Serial;
import com.twitter.serial.stream.SerializerInput;
import com.twitter.serial.stream.SerializerOutput;
import com.twitter.serial.util.SerializationUtils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @author szhua on 2017/11/23/023.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         ExampleObject
 *         For most classes, you can create a subclass of ObjectSerializer and implement serializeObject and deserializeObject
 *
 *         the example for creating a serial object
 *         作为一个例子创建序列化对象；
 *
 */

public  class ExampleObject {

    public static final ObjectSerializer<ExampleObject> SERIALIZER = new ExampleObjectSerializer();

    private int num ;
    private String name ;
    private String url ;
    private SubObject object ;

    public ExampleObject(int num, String name, String url, SubObject object) {
        this.num = num;
        this.name = name;
        this.url = url;
        this.object = object;
    }

    private static final class ExampleObjectSerializer extends ObjectSerializer<ExampleObject>{

        //解析对象成功为bytes数据：
        @Override
        protected void serializeObject(@NotNull SerializationContext context,
                                       @NotNull SerializerOutput output,
                                       @NotNull ExampleObject object) throws IOException {
            output.writeInt(object.num)
                   .writeString(object.name)
                   .writeString(object.url)
                   .writeObject(new MySerializationContext(),object.object,SubObject.SERIALIZER);

        }

        //将bytes数据转化为object
        @Nullable
        @Override
        protected ExampleObject deserializeObject(@NotNull SerializationContext context,
                                                  @NotNull SerializerInput input,
                                                  int versionNumber) throws IOException, ClassNotFoundException {
            int num =input.readInt();
            String name =input.readString();
            String url =input.readString();
            SubObject obj =input.readObject(context,SubObject.SERIALIZER);
            return new ExampleObject(num,name,url,obj);
        }
    }

    @Override
    public String toString() {
        return "ExampleObject{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", object=" + object +
                '}';
    }
}
