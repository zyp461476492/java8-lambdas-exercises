package github.zyp.chapter5;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;

/**
 * <h1>第五章习题</h1>
 */
public class Exercise5 {

    private void exercise2a() {
        Stream<String> names = Stream.of("John Lennon", "Paul McCartney",
                "George Harrison", "Ringo Starr", "Pete Best", "Stuart Sutcliffe");
        // 收集器模式
        byCollector2a(names);
        // reduce 函数
        Optional<String> reduce = names.reduce((acc, name) -> {
            return (Comparator.comparing(String::length).compare(acc, name) >= 0 ? acc : name);
        });
    }

    private void byCollector2a(Stream<String> names) {
        Function<String, Integer> getLength = String::length;
        Optional<String> maxLength = names.max(Comparator.comparing(getLength));
        maxLength.ifPresent(System.out::println);
    }

    private void exercise2b() {
        Stream<String> names = Stream.of("John", "Paul", "George", "John",
                "Paul", "John");
        Map<String, Long> wordCount = names.collect(Collectors.groupingBy(name -> name, counting()));
    }
}
