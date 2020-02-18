package github.zyp.chapter3;

import github.zyp.common.Album;
import github.zyp.common.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h1>第三章练习题</h1>
 */
public class Exercise {
    private List<Artist> artistList = new ArrayList<>();
    private List<Album> albumList = new ArrayList<>();

    private void exercise1a() {
        // a answer
        assert addUp(Stream.of(1,2,3)) == 6;
    }

    private int addUp(Stream<Integer> numbers) {
        return numbers.reduce(0, Integer::sum);
    }

    private void exercise1b() {

        System.out.println(nameWithNationality(artistList.stream()));
    }

    private List<String> nameWithNationality(Stream<Artist> artists) {
        return artists
                .map(artist -> artist.getName() + "/" + artist.getNationality())
                .collect(Collectors.toList());
    }

    private void exercise1c() {

        List<Album> target = albumList.stream()
                .filter(album -> album.getTrackList().size() <= 3)
                .collect(Collectors.toList());
    }

    private void exercise2() {
        long totalMembers = artistList.stream()
                .map(artist -> artist.getMembers().count())
                .reduce(0L, Long::sum)
                .intValue();

    }






}
