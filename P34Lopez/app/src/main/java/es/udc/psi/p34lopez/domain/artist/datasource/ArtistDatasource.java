package es.udc.psi.p34lopez.domain.artist.datasource;

import java.util.List;

import es.udc.psi.p34lopez.domain.artist.Artist;

public interface ArtistDatasource {

    List<Artist> searchArtists(String textToSearch);
}
