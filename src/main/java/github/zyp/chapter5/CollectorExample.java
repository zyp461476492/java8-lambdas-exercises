package github.zyp.chapter5;

import github.zyp.common.Album;
import github.zyp.common.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * <h1>收集器</h1>
 */
public class CollectorExample {

    /**
     * maxBy 和 minBy 允许用户按某种顺序生成一个值
     */
    public Optional<Artist> biggestGroup(Stream<Artist> artists) {
        Function<Artist,Long> getCount = artist -> artist.getMembers().count();
        return artists.max(comparing(getCount));
    }

    /**
     * averagingInt 接受 lambda 表达式作为参数，将流中的整数转换为一个整数
     * 然后计算平均值
     */
    public double averageNumberOfTracks(List<Album> albums) {
        return albums.stream()
                .collect(averagingInt(album -> album.getTrackList().size()));
    }

    /**
     * partitioningBy 收集器
     * 它接受一个流，将它分为两个部分
     *
     * 演示：使用方法引用将艺术家组成的 Stream 分成乐队和独唱歌手两部分
     */
    public Map<Boolean, List<Artist>> bandsAndSolo(Stream<Artist> artists) {
        return artists.collect(partitioningBy(Artist::isSolo));
    }

    /**
     * groupingBy 收集器
     * 可以使用任意值对数据及你想分组
     *
     * 演示：使用主唱对专辑分组
     */
    public Map<Artist, List<Album>> albumsByArtist(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician));
    }

    /**
     * 使用流和收集器格式化艺术家姓名
     */
    public void stringCollector() {
        List<Artist> artists = new ArrayList<>();
        String result =
                artists.stream()
                        .map(Artist::getName)
                        .collect(Collectors.joining(", ", "[", "]"));

    }

    /**
     * 组合收集器
     * groupingBy 先将元素分成块
     * 然后使用下游的另一个收集器（这里用到的 counting）收集每块中的元素，最后将结果映射到 Map。
     */
    public Map<Artist, Long> numberOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                counting()));
    }

    /**
     * 使用收集器求每个艺术家的专辑名
     * mapping 收集器和 map 方法一样，这里的 mapping 收集器也是一个下游收集器
     */
    public Map<Artist, List<String>> nameOfAlbums(Stream<Album> albums) {
        return albums.collect(groupingBy(Album::getMainMusician,
                mapping(Album::getName, toList())));
    }
}
