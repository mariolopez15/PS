package es.udc.psi.p34lopez.domain.artist;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artists {

    @SerializedName("count")
    private int count;

    @SerializedName("artists")
    private List<Artist> artists;

    public Artists(int count,
                   List<Artist> artists) {

        this.count = count;
        this.artists = artists;
    }

    public int getCount() {

        return count;
    }

    public List<Artist> getArtists() {

        return artists;
    }
}
