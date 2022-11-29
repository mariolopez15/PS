package es.udc.psi.p34lopez.domain.artist.service;

import java.util.List;

import es.udc.psi.p34lopez.domain.artist.Artist;

public interface ArtistService {

    List<Artist> searchArtists(String textToSearch);
}
