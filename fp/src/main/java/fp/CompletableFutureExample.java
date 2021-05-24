package fp;

import io.vavr.CheckedFunction0;
import io.vavr.CheckedFunction1;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CompletableFutureExample {

  public static void main(String[] args) {
    Stream.of("A", "B", "C", "D")
        .map(s -> CompletableFuture.supplyAsync(() -> s + s))
        .map(CheckedFunction1.<CompletableFuture<String>,String>of(CompletableFuture::get).unchecked())
//        .map(CompletableFuture::toString)
        .forEach(log::info);
  }
}
