import java.io.*;

public class Message implements Serializable {
    private final byte[] message;
    private final long messageID;
    private final String sender;
    private final long lastSequence;

    public Message(long msgID, String sender, byte[] message, long lastSequence) {
        this.message = message;
        this.sender = sender;
        this.messageID = msgID;
        this.lastSequence = lastSequence;
    }

    public long getLastSequence() {
        return lastSequence;
    }

    public byte[] getMsg() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public long getMsgID() {
        return messageID;
    }

    public static byte[] toByteStream(Message message) throws Exception {
        byte[] data;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(message);
            data = byteArrayOutputStream.toByteArray();
        }
        return data;
    }

    public static Message fromByteStream(byte[] data) throws Exception {
        Message message;

        try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(data);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream)) {
            message = (Message) objectInputStream.readObject();
        }

        return message;
    }

    @Override
    public String toString() {
        String msg = null;

        for(int i = 0; i < this.message.length;i++ ){
            msg += this.message[i];
        }

        return "Message: "+ msg + " ID: "+this.messageID+" "+" sender: "+this.sender+" "+"LastSequence: "+ this.lastSequence;
    }
}
