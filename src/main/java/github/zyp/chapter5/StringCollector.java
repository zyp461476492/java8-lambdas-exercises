package github.zyp.chapter5;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * <h1>自定义字符串收集器</h1>
 * Collector<T, A, R>
 *     T:待收集元素
 *     A:累加器
 *     R:累加结果
 */
public class StringCollector implements Collector<String, StringCombiner, String> {
    private final String prefix;
    private final String suffix;
    private final String delimiter;

    public StringCollector(String prefix, String suffix, String delimiter) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.delimiter = delimiter;
    }

    /**
     * A function that creates and returns a new mutable result container.
     *
     * Supplier 是创建容器的工厂
     *
     * @return a function which returns a new, mutable result container
     */
    @Override
    public Supplier<StringCombiner> supplier() {
        return () -> new StringCombiner(delimiter, prefix, suffix);
    }

    /**
     * A function that folds a value into a mutable result container.
     *
     * @return a function which folds a value into a mutable result container
     */
    @Override
    public BiConsumer<StringCombiner, String> accumulator() {
        return StringCombiner::add;
    }

    /**
     * A function that accepts two partial results and merges them.  The
     * combiner function may fold state from one argument into the other and
     * return that, or may return a new result container.
     *
     * @return a function which combines two partial results into a combined
     * result
     */
    @Override
    public BinaryOperator<StringCombiner> combiner() {
        return StringCombiner::merge;
    }

    /**
     * finisher 方法返回收集操作的最终结果
     */
    @Override
    public Function<StringCombiner, String> finisher() {
        return StringCombiner::toString;
    }

    /**
     * characteristics 方法定义了特征。
     */
    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
