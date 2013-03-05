package tn.esprit.clientJMSJboss7.jmsClient;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class TestJMS {

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		final String QUEUE_LOOKUP = "jms/queue/ESPRITQueue";
		final String CONNECTION_FACTORY = "ConnectionFactory";
		try {
			Context context = new InitialContext();
			System.out.println("Context Initilalisé..............");
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup("jms/RemoteConnectionFactory");
			QueueConnection connection = factory.createQueueConnection("daly",
					"azerty");
			QueueSession session = connection.createQueueSession(false,
					QueueSession.AUTO_ACKNOWLEDGE);
			Queue queue = (Queue) context.lookup(QUEUE_LOOKUP);
			QueueSender sender = session.createSender(queue);
			TextMessage message = session.createTextMessage();
			message.setText("Hello EJB3 MDB its me your best client !!!");
			sender.send(message);
			System.out.println("1. Sent TextMessage to the Queue");
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
