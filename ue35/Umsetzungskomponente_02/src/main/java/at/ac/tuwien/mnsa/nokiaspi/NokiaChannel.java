package at.ac.tuwien.mnsa.nokiaspi;

import java.nio.ByteBuffer;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

/**
 * CardChannel implementation class.
 */
public class NokiaChannel extends CardChannel {

	private NokiaCard card;
	private int channel;

	public NokiaChannel(NokiaCard card, int channel) {
		this.card = card;
		this.channel = channel;
	}

	public Card getCard() {
		return card;
	}

	public int getChannelNumber() {
		return channel;
	}

	public ResponseAPDU transmit(CommandAPDU capdu) throws CardException {
		return card.transmitCommand(capdu);
	}

	public int transmit(ByteBuffer bb, ByteBuffer bb1) throws CardException {
		ResponseAPDU response = transmit(new CommandAPDU(bb));
		byte[] binaryResponse = response.getBytes();
		bb1.put(binaryResponse);
		return binaryResponse.length;
	}

	/**
	 * Do nothing.
	 */
	public void close() throws CardException {

	}

}
