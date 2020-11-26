package runtime.pojo.schema;

import lombok.SneakyThrows;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import runtime.pojo.Version1Pojo;

import java.io.IOException;

/**
 * @author Apache-x | A You Ok
 * @version 1.0
 * @Description
 * @date 2020/5/29 15:26
 */
public class Version1PojoSchema implements DeserializationSchema<Version1Pojo>, SerializationSchema<Version1Pojo> {

    private static final long serialVersionUID = 6154188370181669758L;

    @Override
    public byte[] serialize(Version1Pojo userTagsInfo) {
        return userTagsInfo.toString().getBytes();
    }


    @SneakyThrows
    @Override
    public Version1Pojo deserialize(byte[] bytes) throws IOException {
        return Version1Pojo.jsonFromClass(new String(bytes));
    }

    @Override
    public boolean isEndOfStream(Version1Pojo userTagsInfo) {
        return false;
    }

    @Override
    public TypeInformation<Version1Pojo> getProducedType() {
        return TypeInformation.of(Version1Pojo.class);
    }
}

