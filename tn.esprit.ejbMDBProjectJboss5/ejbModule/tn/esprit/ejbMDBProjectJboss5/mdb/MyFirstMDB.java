package tn.esprit.ejbMDBProjectJboss5.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "/queue/daly") }, mappedName = "daly")
public class MyFirstMDB implements MessageListener {

	public void onMessage(Message message) {

		TextMessage textMessage = (TextMessage) message;
		try {
			String regionRecu = textMessage.getText();
			System.out.println("la meilleur raigion en tunisie est : "
					+ regionRecu);
		} catch (JMSException e) {
			System.out.println("ahhhh ... ");
		}

	}

}
