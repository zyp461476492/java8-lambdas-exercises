package github.zyp.chapter3;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * <h1>Stream 常用操作</h1>
 */
public class CommonUsedOp {

    /**
     * <h2>collect(toList())</h2>
     * collect(toList()) 方法由 Stream 里的值生成一个列表，是一个及早求值操作。
     */
    private void collect() {
        List<String> collected = Stream.of("a", "b", "c")
                .collect(toList());
        assert CollectionUtils.isEqualCollection(Arrays.asList("a", "b", "c"), collected);
    }

    /**
     * <h2>map</h2>
     * 如果有一个函数可以将一种类型的值转换成另外一种类型，
     * map 操作就可以 使用该函数，将一个流中的值转换成一个新的流。
     * map(x) = x -> y
     */
    private void map() {
        List<String> collected = Stream.of("a", "b", "hello")
                .map(String::toUpperCase)
                .collect(toList());
        // map 的参数实际上一个 Function<T,R> 函数接口
        Function<Integer, Integer> param;
        assert CollectionUtils.isEqualCollection(Arrays.asList("A", "B", "HELLO"), collected);
    }

    /**
     * <h2>filter</h2>
     * 遍历数据并检查其中的元素时，可尝试使用 Stream 中提供的新方法 filter
     * 惰性求值方法
     */
    private void filter() {
        List<String> beginningWithNumbers = Stream.of("a", "1abc", "abc1")
                .filter(value -> Character.isDigit(value.charAt(0))).collect(toList());
        // filter 的参数实际上是一个 Predicate 函数接口
        Predicate<Integer> param;
        assert CollectionUtils.isEqualCollection(Collections.singletonList("1abc"), beginningWithNumbers);
    }

    /**
     * <h2>flatMap</h2>
     * flatMap 方法可用 Stream 替换值，然后将多个 Stream 连接成一个 Stream
     */
    private void flatMap() {
        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(Collection::stream).collect(toList());

        assert CollectionUtils.isEqualCollection(Arrays.asList(1, 2, 3, 4), together);
    }
}
