package github.zyp.chapter5;

import github.zyp.common.Artist;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>高级集合和收集器类</h1>
 */
public class Chapter5 {

    /**
     * <h2>方法引用</h2>
     * 此 Java 8 为 Lambda 表达式提供了一个简写语法
     */
    private void example1() {
        List<Integer> list = new ArrayList<>();
        // 正常
        list = list.stream()
                .map(x -> x + 1)
                .collect(Collectors.toList());
        // 简写
        Stream.of(Arrays.asList(1,2,3))
                .map(List::size);
    }

    /**
     * <h2>元素顺序</h2>
     * 有部分流是顺序的，有部分流是无序的
     * 无序的流可以通过中间操作（sorted）变成有序的
     * 有序的流也可以通过（unordered）变成无序的
     */
    private void example2() {

    }

    /**
     * Java 8 引入了一个新方法 computeIfAbsent
     * 值不存在时使用该 Lambda 表达式计算新值
     */
    public Artist getArtist(String name) {
        Map<String, Artist> artistCache = new HashMap<>();
        return artistCache.computeIfAbsent(name, this::readArtistFromDB);
    }

    private Artist readArtistFromDB(String name) {
        return null;
    }


}
