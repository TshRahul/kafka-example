package com.stereo.kafkaexample;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class MessageRequestSerializer implements Serializer<MessageRequest> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, MessageRequest request) {
        int sizeOfMessage;
        byte[] serializedMessage;
        try {
        if (request == null)
            return null;

            serializedMessage = request.getMessage().getBytes(encoding);
            sizeOfMessage = serializedMessage.length;

            ByteBuffer buffer = ByteBuffer.allocate(4+4+sizeOfMessage);
            buffer.putInt(sizeOfMessage);
            buffer.put(serializedMessage);

            return buffer.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Message to byte[]");
        }
    }

    /*@Override
    public byte[] serialize(String topic, Headers headers, MessageRequest data) {
        return new byte[0];
    }*/

    @Override
    public void close() {

    }
}
