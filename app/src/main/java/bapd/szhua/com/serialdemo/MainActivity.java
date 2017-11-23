package bapd.szhua.com.serialdemo;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.twitter.serial.serializer.CollectionSerializers;
import com.twitter.serial.serializer.CoreSerializers;
import com.twitter.serial.serializer.Serializer;
import com.twitter.serial.stream.bytebuffer.ByteBufferSerial;
import com.twitter.serial.stream.legacy.LegacySerial;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
* @author szhua
* */
public class MainActivity extends AppCompatActivity {

    private byte[]  bytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //============================Twitter.Serial=====================================================

        SubObject subObject = new SubObject("1", "szhua");
        ExampleObject exampleObject = new ExampleObject(2, "fff", "www.ffff", subObject);

        LegacySerial serial = new LegacySerial();
        TextView text = findViewById(R.id.content);

        // object==>bytes ;
        findViewById(R.id.objec_to_serial).setOnClickListener((View view) -> {
            try {
                bytes = serial.toByteArray(exampleObject, ExampleObject.SERIALIZER);
                text.setText(new String(bytes) + "=====Size:" + bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //bytes ==>object;
        findViewById(R.id.serial_to_object).setOnClickListener(view -> {
            try {
                ExampleObject obj = serial.fromByteArray(bytes, ExampleObject.SERIALIZER);
                text.setText(obj.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        LegacySerial serial2 = new LegacySerial();
        //For example, to serialize a list of Strings, you can use:
        Serializer<List<String>> a  = CollectionSerializers.getListSerializer(CoreSerializers.STRING);

       findViewById(R.id.objec_to_serial_pa).setOnClickListener(view -> {
           String[] data =new String[]{"今天","mingitan","fwefwe","fdsfsdf","werjwer"} ;
           try {
               bytes = serial2.toByteArray(Arrays.asList(data),a);
               text.setText(new String(bytes, "UTF8"));
           } catch (IOException e) {
               e.printStackTrace();
           }
       });
       findViewById(R.id.serial_to_object_pa).setOnClickListener(view -> {
           try {
               List<String> data = serial2.fromByteArray(bytes, a);
               text.setText(data.get(0));
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }
       });



    }
}
