package es.udc.psi.p34lopez.module.artist.viewmodel;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.dataOffLine.ArtistOffLine;
import es.udc.psi.p34lopez.domain.artist.Artist;

public class ArtistViewModelOffLineMapper {
    private List<ArtistOffLine> mArtists;

    public ArtistViewModelOffLineMapper(List<ArtistOffLine> artists) {
        mArtists = artists;

    }

    public List<ArtistViewModel> map() {

        List<ArtistViewModel> artists = new ArrayList<>();
        for (ArtistOffLine artist : mArtists) {
            artists.add(new ArtistViewModel(artist.getArtistId(), artist.getName()));
        }
        return artists;
    }
}
