package at.ac.tuwien.mnsa.message;

public class Message {
    public enum MessageType {
        TEST((byte) 0x00),
        APDU((byte) 0x01),
        CARD_PRESENT((byte) 0x03),
        ATR((byte) 0x02),
        CONNECT((byte) 0x04),
        CLOSE((byte) 0x05),
        ERROR((byte) 0x7F);

        private final byte b;

        private MessageType(byte b) {
            this.b = b;
        }

        public byte getByteValue() {
            return b;
        }

        public static MessageType valueOf(byte b) throws MessageException {
            if (b == TEST.getByteValue()) {
                return TEST;
            } else if (b == APDU.getByteValue()) {
                return APDU;
            } else if (b == CARD_PRESENT.getByteValue()) {
                return CARD_PRESENT;
            } else if (b == ATR.getByteValue()) {
                return ATR;
            } else if (b == CONNECT.getByteValue()) {
                return CONNECT;
            } else if (b == CLOSE.getByteValue()) {
                return CLOSE;
            } else if (b == ERROR.getByteValue()) {
                return ERROR;
            }
            throw new MessageException("Unknown Type " + String.format("%02x", b));
        }
    }

    private final MessageType type;

    private final byte[] payload;

    public Message(MessageType type, byte[] payload) {
        this.type = type;
        this.payload = payload;
    }

    public Message(MessageType type) {
        this(type, new byte[]{});
    }

    public byte[] getPayload() {
        return payload;
    }

    public MessageType getType() {
        return type;
    }

    public String toString() {
        return "MTY[" + type + "] LN[" + payload.length + "] PY[" + bytesToHex(payload) + "]";
    }

    private String bytesToHex(byte[] bytes) {
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
