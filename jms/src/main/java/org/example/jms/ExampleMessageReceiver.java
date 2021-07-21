package org.example.jms;

import static javax.jms.Session.AUTO_ACKNOWLEDGE;
import static org.example.jms.JmsProvider.QUEUE_NAME;

import io.vavr.Tuple;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import ru.ubrr.it.courses.java.fp.ScopeFunctions;
import ru.ubrr.it.courses.java.fp.TupleUtils;

@Slf4j
@ExtensionMethod({ScopeFunctions.class, TupleUtils.class})
public class ExampleMessageReceiver implements MessageListener, AutoCloseable {

  Connection connection = JmsProvider.getConnectionFactory()
                              .map(ConnectionFactory::createConnection)
                              .apply(Connection::start);

  {
    Session session = Tuple.of(connection, false, AUTO_ACKNOWLEDGE)
                          .applyChecked(Connection::createSession);

    Tuple.of(session, QUEUE_NAME)
        .exchange2(Session::createQueue)
        .applyChecked(Session::createConsumer)
        .with(session1 -> session1.setMessageListener(this));
  }

  @Override
  @SneakyThrows
  public void onMessage(Message message) {
    if (message instanceof TextMessage tm)
      log.info("Message received: {}, Thread: {}", tm.getText(), Thread.currentThread().getName());
  }

  @SneakyThrows
  public void close() {
    connection.close();
  }
}
