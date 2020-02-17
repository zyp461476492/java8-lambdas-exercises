package github.zyp.chapter3;


import github.zyp.common.Artist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chapter3 {

    private List<Artist> allArtists = new ArrayList<>();

    /**
     * <h2>外部迭代和内部迭代</h2>
     */
    private void example1() {
        // 外部迭代
        int count = 0;
        Iterator<Artist> iterator = allArtists.iterator();
        while (iterator.hasNext()) {
            Artist artist = iterator.next();
            if (artist.isFrom("London")) {
                count++;
            }
        }
        // stream 是描述内部迭代
    }

    /**
     * <h2>Stream 惰性求值</h2>
     * 像 filter 这样只描述 Stream，最终不产生新集合的方法叫作惰性求值方法；
     * 而像 count 这样最终会从 Stream 产生值的方法叫作及早求值方法。
     */
    private void example2() {
        long count = allArtists.stream()
                // 惰性求值，只描述 Stream 不产生新集合
                .filter(artist -> artist.isFrom("London"))
                // 会从 Stream 中产生值，及早求值法
                .count();
    }
}
