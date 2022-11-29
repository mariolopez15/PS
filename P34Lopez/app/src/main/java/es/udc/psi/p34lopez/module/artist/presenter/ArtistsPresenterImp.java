package es.udc.psi.p34lopez.module.artist.presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.data.gig.GigDatasourceImp;
import es.udc.psi.p34lopez.domain.artist.Artist;
import es.udc.psi.p34lopez.domain.artist.service.ArtistService;
import es.udc.psi.p34lopez.domain.artist.service.ArtistServiceImp;
import es.udc.psi.p34lopez.domain.gig.Gig;
import es.udc.psi.p34lopez.domain.gig.service.GigService;
import es.udc.psi.p34lopez.domain.gig.service.GigServiceImp;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModel;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModelMapper;

public class ArtistsPresenterImp implements ArtistsPresenter {

    private final static String TAG = ArtistsPresenterImp.class.getSimpleName();

    private ArtistsView mView;

    private List<ArtistViewModel> mArtistsViewModels;

    private ArtistService mArtistService = new ArtistServiceImp();
    private GigService mGigService = new GigServiceImp(new GigDatasourceImp());

    public ArtistsPresenterImp(ArtistsView view) {

        mView = view;
    }

    @Override
    public void initFlow() {

        new GetArtistsTask().execute();
    }

    @Override
    public void onClickArtist() {

    }

    private List<ArtistViewModel> getArtistsViewModel(List<Artist> artists) {

        mArtistsViewModels = new ArtistViewModelMapper(artists).map();
        return mArtistsViewModels;
    }

    private void getLastGigs(List<ArtistViewModel> artistsViewModels) {

        int index = 0;
        for (ArtistViewModel artist : artistsViewModels) {

            new GetGigTask(index++).execute(artist);
        }
    }

    private class GetArtistsTask extends AsyncTask<String, Void, List<Artist>> {

        protected List<Artist> doInBackground(String... textToSearch) {

            return mArtistService.searchArtists("Green Day");
        }

        protected void onPostExecute(List<Artist> result) {

            mArtistsViewModels = getArtistsViewModel(result);
            mView.showArtists(mArtistsViewModels);
            getLastGigs(mArtistsViewModels);
        }
    }

    private class GetGigTask extends AsyncTask<ArtistViewModel, Void, List<Gig>> {

        private int mPosition;
        private ArtistViewModel mArtist;

        GetGigTask(int position) {

            mPosition = position;
        }

        protected List<Gig> doInBackground(ArtistViewModel... artist) {

            try {

                Thread.sleep(3000);
                mArtist = artist[0];
                return mGigService.searchGig(mArtist.getId());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return new ArrayList<>();
        }

        protected void onPostExecute(List<Gig> result) {

            mView.updateArtist(mArtist, mPosition);

        }
    }
}
