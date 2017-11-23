package bapd.szhua.com.serialdemo;

import com.twitter.serial.serializer.SerializationContext;

/**
 * @author szhua on 2017/11/23/023.
 *         github:https://github.com/szhua
 *         SerialDemo
 *         MySerializationContext
 *         请添加注释说明
 */

public class MySerializationContext implements SerializationContext {
    //serialization and deserialization should be done
    @Override
    public boolean isDebug() {
        return true;
    }
    //serialization and deserialization should be done
    @Override
    public boolean isRelease() {
        return true;
    }
}
