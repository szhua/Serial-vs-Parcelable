package bapd.szhua.com.serialdemo;

import com.twitter.serial.object.Builder;
import com.twitter.serial.serializer.BuilderSerializer;
import com.twitter.serial.serializer.SerializationContext;
import com.twitter.serial.stream.SerializerInput;
import com.twitter.serial.stream.SerializerOutput;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * @author szhua on 2017/11/24/024.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         Person
 *      builder 模式下使用serial
 */

public class Person {

    private String firstName ;
    private String lastName ;
    private int age ;

    public  Person(Builder builder){
         assert builder.firstName !=null ;
         assert  builder.lastName !=null ;

         this.firstName =builder .firstName ;
         this.lastName =builder.lastName ;
         this.age =builder.age ;


    }


    public static class Builder  implements com.twitter.serial.object.Builder<Person>{
        @Nullable private String firstName;
        @Nullable
        private String lastName;
        private int age;

        @NotNull
        public void setFirstName(@Nullable String firstName) {
            this.firstName = firstName;
        }
        @NotNull
        public void setLastName(@Nullable String lastName) {
            this.lastName = lastName;
        }
        @NotNull
        public void setAge(int age) {
            this.age = age;
        }
        @NotNull
        @Override
        public Person build() {
            return new Person(this);
        }
    }


    /**进行解析和反解析*/
    public static  final class  PersonSerializer extends BuilderSerializer<Person,Builder>{
        @NotNull
        @Override
        protected Builder createBuilder() {
            return new Builder();
        }
        @Override
        protected void deserializeToBuilder(@NotNull SerializationContext context, @NotNull SerializerInput input, @NotNull Builder builder, int versionNumber) throws IOException, ClassNotFoundException {
             builder.firstName =input.readString() ;
             builder.lastName =input.readString() ;
             builder.age =input.readInt();
        }

        @Override
        protected void serializeObject(@NotNull SerializationContext context, @NotNull SerializerOutput output, @NotNull Person person) throws IOException {
            output.writeString(person.firstName);
            output.writeString(person.lastName);
            output.writeInt(person.age);
        }
    }

}
