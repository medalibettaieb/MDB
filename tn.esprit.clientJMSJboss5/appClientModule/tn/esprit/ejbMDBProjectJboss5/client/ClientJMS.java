package tn.esprit.ejbMDBProjectJboss5.client;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ClientJMS {

	private Session session;
	private MessageProducer producer;

	public boolean envoi(String client) throws Exception {
		Connection connection = null;
		try {
			Context initial = new InitialContext();
			ConnectionFactory cf = (ConnectionFactory) initial
					.lookup("ConnectionFactory");
			Destination destination = (Destination) initial
					.lookup("/queue/daly");
			connection = cf.createConnection("guest", "guest");
			connection.setClientID(client);
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			producer = session.createProducer(destination);
			connection.start();
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("makthar");
			producer.send(textMessage);
			System.out.println("message sent ...");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				session.close();
				connection.close();
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		new ClientJMS().envoi("guest");
	}
}
