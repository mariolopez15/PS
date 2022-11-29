package es.udc.psi.p34lopez.module.artist.presenter;

import java.util.List;

import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModel;

public interface ArtistsView {

    void showArtists(List<ArtistViewModel> artists);

    void showEmptyView();

    void showError();

    void updateArtist(ArtistViewModel artist, int position);
}
