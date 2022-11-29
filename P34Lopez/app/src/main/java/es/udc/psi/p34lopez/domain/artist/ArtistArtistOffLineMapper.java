package es.udc.psi.p34lopez.domain.artist;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.dataOffLine.ArtistOffLine;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModel;

public class ArtistArtistOffLineMapper {
    private List<Artist> mArtists;

    public ArtistArtistOffLineMapper(List<Artist> artists) {
        mArtists = artists;

    }

    public List<ArtistOffLine> map() {

        List<ArtistOffLine> artists = new ArrayList<>();
        for (Artist artist : mArtists) {
            artists.add(new ArtistOffLine(artist.getId(), artist.getName()));
        }
        return artists;
    }
}
