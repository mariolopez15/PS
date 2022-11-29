package es.udc.psi.p34lopez.domain.artist.service;

import java.util.List;

import es.udc.psi.p34lopez.data.artist.ArtistDatasourceImp;
import es.udc.psi.p34lopez.domain.artist.Artist;
import es.udc.psi.p34lopez.domain.artist.datasource.ArtistDatasource;

public class ArtistServiceImp implements ArtistService {

    private ArtistDatasource mDatasource = new ArtistDatasourceImp();

    @Override
    public List<Artist> searchArtists(String textToSearch) {

        return mDatasource.searchArtists(textToSearch);
    }
}
