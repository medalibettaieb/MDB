package tn.esprit.ejbMDBProjectJboss7.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: Mdbb
 * 
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/queue/ESPRITQueue") }, mappedName = "ESPRITQueue")
public class MyFirstMDB implements MessageListener {

	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		try {
			String messageReloded = textMessage.getText();
			System.out.println("i've receved a message it says : "
					+ messageReloded);
		} catch (JMSException e) {
			System.out.println("ooooof not again ");
		}

	}

}
