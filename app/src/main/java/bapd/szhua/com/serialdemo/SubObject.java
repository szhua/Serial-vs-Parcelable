package bapd.szhua.com.serialdemo;

import com.twitter.serial.serializer.ObjectSerializer;
import com.twitter.serial.serializer.SerializationContext;
import com.twitter.serial.stream.SerializerInput;
import com.twitter.serial.stream.SerializerOutput;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @author szhua on 2017/11/23/023.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         SubObject
 *         作为一个序列化对象中的对象；
 */

public class SubObject {

    public static final ObjectSerializer<SubObject> SERIALIZER = new SubObjectSerializer();

    String id ;
    String name ;

    public SubObject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private static final class SubObjectSerializer extends ObjectSerializer<SubObject> {
        @Override
        protected void serializeObject(@NotNull SerializationContext context,
                                       @NotNull SerializerOutput output,
                                       @NotNull SubObject object) throws IOException {
            output.writeString(object.id)
                    .writeString(object.name);
        }

        @Nullable
        @Override
        protected SubObject deserializeObject(@NotNull SerializationContext context,
                                              @NotNull SerializerInput input,
                                              int versionNumber) throws IOException, ClassNotFoundException {
            //TODO 是否是next模式；
            String id =input.readString();
            String name =input.readString();
            return new SubObject(id,name);
        }
    }

    @Override
    public String toString() {
        return "SubObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
