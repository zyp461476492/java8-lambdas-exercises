package github.zyp.chapter6;

import github.zyp.common.Album;
import github.zyp.common.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <h1>第六章：数据并行化</h1>
 * 并发是两个任务共享时间段
 * 并行则是两个任务在同一时间发生
 *
 * 数据并行化是指将数据分成块，为每块
 * 数据分配单独的处理单元。
 *
 * 任务并行化和数据并行化的不同
 *   任务并行化，线程不同，任务各异
 */
public class Chapter6 {
    List<Album> albums = new ArrayList<>();

    private final int N = 10000;

    Random random = new Random();

    /**
     * <h2>并行化流操作</h2>
     */
    private int example1() {
        // parallelStream 就是并行化操作
        return albums.parallelStream()
                .flatMap(Album::getTracks)
                .mapToInt(Track::getLength)
                .sum();
    }

    /**
     * <h2>利用并行化流模拟 蒙特卡洛模拟法</h2>
     */
    public Map<Integer, Double> parallelDiceRolls() {
        double fraction = 1.0 / N;
        return IntStream.range(0, N)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side,
                        Collectors.summingDouble(n -> fraction)));
    }

    /**
     * <h2>模拟扔骰子</h2>
     * @return 返回两次骰子和
     */
    private IntFunction<Integer> twoDiceThrows() {
        return i -> {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            int firstThrow = random.nextInt(1, 7);
            int secondThrow = random.nextInt(1, 7);
            return firstThrow + secondThrow;
        };
    }


}
