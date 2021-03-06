package at.ac.tuwien.mnsa.nokiaspi;

import at.ac.tuwien.mnsa.comm.SerialConnection;
import at.ac.tuwien.mnsa.comm.SerialConnectionException;
import at.ac.tuwien.mnsa.message.MessageException;
import org.apache.log4j.Logger;

import javax.smartcardio.Card;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;

public class NokiaTerminal extends CardTerminal {

    public final static String NAME = "NokiaTerminal.Terminal";
    private static NokiaCard card = null;
    private final Logger logger = Logger.getLogger(NokiaTerminal.class);

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Card connect(String string) throws CardException {
        openConnectionIfClosed();
        return card;
    }

    @Override
    public boolean isCardPresent() throws CardException {
        openConnectionIfClosed();

        return card.isPresent();
    }

    @Override
    public boolean waitForCardPresent(long l) throws CardException {
        return true;
    }

    @Override
    public boolean waitForCardAbsent(long l) throws CardException {
        return false;
    }

    private void openConnectionIfClosed() throws CardException {
        try {
            if (card == null) {
                SerialConnection connection = SerialConnection.open();
                card = new NokiaCard(connection);
                logger.info("Successfully established serial connection to phone");
            }
        } catch (SerialConnectionException ex) {
            throw new CardException(ex);
        } catch (MessageException ex) {
            throw new CardException(ex);
        }
    }
}
