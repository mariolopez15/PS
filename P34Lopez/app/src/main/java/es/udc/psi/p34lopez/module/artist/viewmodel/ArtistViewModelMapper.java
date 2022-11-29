package es.udc.psi.p34lopez.module.artist.viewmodel;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.domain.artist.Artist;

public class ArtistViewModelMapper {

    private List<Artist> mArtists;

    public ArtistViewModelMapper(List<Artist> artists) {

        mArtists = artists;
    }

    public List<ArtistViewModel> map() {

        List<ArtistViewModel> artists = new ArrayList<>();
        for (Artist artist : mArtists) {
            artists.add(new ArtistViewModel(artist.getId(), artist.getName()));
        }
        return artists;
    }
}
