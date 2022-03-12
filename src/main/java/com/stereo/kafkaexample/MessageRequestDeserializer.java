package com.stereo.kafkaexample;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class MessageRequestDeserializer implements Deserializer<MessageRequest> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public MessageRequest deserialize(String s, byte[] bytes) {
        try {
            if (bytes == null) {
                System.out.println("Null recieved at deserialize");
                return null;
            }

                ByteBuffer buffer = ByteBuffer.wrap(bytes);
                int id = buffer.getInt();

                int sizeOfMessage = buffer.getInt();
                byte[] messageBytes = new byte[sizeOfMessage];
                buffer.get(messageBytes);
                String deserializedName = new String(messageBytes, encoding);

                return new MessageRequest(deserializedName);
        }catch (Exception e) {
            throw new SerializationException("Error when deserialize byte[] to Message");
        }
    }

    /*@Override
    public MessageRequest deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }*/

    @Override
    public void close() {

    }
}
