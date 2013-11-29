package at.ac.tuwien.mnsa.message;

public class APDUMessage implements Message<byte[]> {
	
	private final byte[] payload;

	public APDUMessage(byte[] payload) {
		this.payload = payload;
	}

	@Override
	public byte getIdentifier() {
		return 2;
	}

	@Override
	public byte[] getPayload() {
		return payload;
	}
	
}
