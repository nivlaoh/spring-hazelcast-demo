package com.hokaicheng.serialization;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import com.hokaicheng.model.ExternalUser;
import com.hokaicheng.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ExternalUserSerializer implements StreamSerializer<ExternalUser> {
    private static final Logger LOG = LoggerFactory.getLogger(ExternalUserSerializer.class);

    @Override
    public void write(ObjectDataOutput output, ExternalUser externalUser) throws IOException {
        output.writeUTF(StringUtil.marshall(externalUser));
    }

    @Override
    public ExternalUser read(ObjectDataInput input) throws IOException {
        return StringUtil.unmarshall(input.readUTF(), ExternalUser.class);
    }

    @Override
    public int getTypeId() {
        return 10;
    }

    @Override
    public void destroy() {
    }
}
