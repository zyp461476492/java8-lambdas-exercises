package github.zyp.chapter4;

import github.zyp.common.Artist;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Exercise4 {

    private void exercise1() {
        Performance performance;
    }

    /** 该接口表示艺术家的演出——专辑或演唱会 */
    public interface Performance {
        String getName();
        Stream<Artist> getMusicians();
        default Stream<Artist> getAllMusicians() {
            return getMusicians()
                    .flatMap(artist -> Stream.concat(Stream.of(artist), artist.getMembers()));
        };
    }

    private void exercise3() {

    }

    public class Artists {
        private List<Artist> artists;
        public Artists(List<Artist> artists) {
            this.artists = artists;
        }

        public Optional<Artist> getArtist(int index) {
            if (index < 0 || index >= artists.size()) {
                return Optional.empty();
            }
            return Optional.of(artists.get(index));
        }
        private void indexException(int index) {
            throw new IllegalArgumentException(index +
                    "doesn't correspond to an Artist");
        }
        public String getArtistName(int index) {
            Optional<Artist> artist = getArtist(index);
            return artist.map(Artist::getName)
                    .orElse("unknown");
        }
    }
}
