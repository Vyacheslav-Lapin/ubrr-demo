package org.example.jms;

import static java.util.Collections.singletonList;

import javax.jms.ConnectionFactory;
import lombok.experimental.UtilityClass;
import org.apache.activemq.ActiveMQConnectionFactory;

@UtilityClass
public class JmsProvider {

  public static final String BROKER_URL = "vm://localhost";
  public static final String QUEUE_NAME = "example.queue";

  /**
   * The VM transport allows clients to connect to each other inside
   *                  the VM without the overhead of the network communication.
   */
  public static ConnectionFactory getConnectionFactory() {
    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
    connectionFactory.setTrustedPackages(singletonList("org.example.jms"));
    return connectionFactory;
  }
}
