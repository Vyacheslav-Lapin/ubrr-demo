package org.example.jms;

import static java.lang.Thread.currentThread;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.example.jms.JmsProvider.QUEUE_NAME;

import io.vavr.Tuple;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import ru.ubrr.it.courses.java.fp.ScopeFunctions;
import ru.ubrr.it.courses.java.fp.TupleUtils;

@Slf4j
@ExtensionMethod({ScopeFunctions.class, TupleUtils.class})
public class ExampleMessageSender implements AutoCloseable {

  Connection connection = JmsProvider
                              .getConnectionFactory()
                              .map(ConnectionFactory::createConnection)
                              .apply(Connection::start);

  Session session = Tuple.of(connection, false, AUTO_ACKNOWLEDGE)
                        .applyChecked(Connection::createSession);

  MessageProducer producer = Tuple.of(session, QUEUE_NAME)
                                 .exchange2(Session::createQueue)
                                 .applyChecked(Session::createProducer);

  public void sendMessage(String message) throws JMSException {
    log.info("Sending message: {}, Thread: {}", message, currentThread().getName());
    producer.send(session.createTextMessage(message));
  }

  @SneakyThrows
  public void close() {
    connection.close();
  }
}
