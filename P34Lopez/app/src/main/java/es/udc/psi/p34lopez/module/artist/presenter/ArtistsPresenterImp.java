package es.udc.psi.p34lopez.module.artist.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.WorkerThread;

import java.util.ArrayList;
import java.util.List;

import es.udc.psi.p34lopez.GlobalApplication;
import es.udc.psi.p34lopez.data.gig.GigDatasourceImp;
import es.udc.psi.p34lopez.dataOffLine.ArtistDao;
import es.udc.psi.p34lopez.dataOffLine.ArtistOffLine;
import es.udc.psi.p34lopez.dataOffLine.MusicDataBaseClient;
import es.udc.psi.p34lopez.domain.artist.Artist;
import es.udc.psi.p34lopez.domain.artist.ArtistArtistOffLineMapper;
import es.udc.psi.p34lopez.domain.artist.service.ArtistService;
import es.udc.psi.p34lopez.domain.artist.service.ArtistServiceImp;
import es.udc.psi.p34lopez.domain.gig.Gig;
import es.udc.psi.p34lopez.domain.gig.service.GigService;
import es.udc.psi.p34lopez.domain.gig.service.GigServiceImp;
import es.udc.psi.p34lopez.module.artist.ArtistsActivity;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModel;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModelMapper;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModelOffLineMapper;

public class ArtistsPresenterImp implements ArtistsPresenter {

    private final static String TAG = ArtistsPresenterImp.class.getSimpleName();

    private ArtistsActivity mView;

    private List<ArtistViewModel> mArtistsViewModels;

    private ArtistService mArtistService = new ArtistServiceImp();
    private GigService mGigService = new GigServiceImp(new GigDatasourceImp());

    public ArtistsPresenterImp(ArtistsActivity view) {

        mView = view;
    }

    @Override
    public void initFlow(String toSearch) {
        //COMPROBAR SI HAY CONEXION, SI NO LLAMAR AL GET DEL DB
        if(isInternetAvailable()){
            new GetArtistsTask(toSearch).execute();
            Log.d("TAG", "Buscar con conexion");
        }else{
            new GetArtistsTaskOffline(toSearch).execute();
            Log.d("TAG", "Buscar sin conexion");
        }

    }

    @Override
    public void onClickArtist() {

    }

    public boolean isInternetAvailable () {
        ConnectivityManager cm = (ConnectivityManager ) mView
                .getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private List<ArtistViewModel> getArtistsViewModel(List<Artist> artists) {

        mArtistsViewModels = new ArtistViewModelMapper(artists).map();
        return mArtistsViewModels;
    }
    private List<ArtistViewModel> getArtistsViewModelOffLine(List<ArtistOffLine> artists) {

        mArtistsViewModels = new ArtistViewModelOffLineMapper(artists).map();
        return mArtistsViewModels;
    }

    private void getLastGigs(List<ArtistViewModel> artistsViewModels) {

        int index = 0;
        for (ArtistViewModel artist : artistsViewModels) {

            new GetGigTask(index++).execute(artist);
        }
    }

    private class GetArtistsTask extends AsyncTask<String, Void, List<Artist>> {
        private String artistName;

        public GetArtistsTask(String artistName) {
            this.artistName = artistName;
        }

        protected List<Artist> doInBackground(String... textToSearch) {

            List<Artist> art = mArtistService.searchArtists(artistName);
            return art;
        }

        protected void onPostExecute(List<Artist> result) {
            //añadirlo a la BD
            new SetArtistsTaskOffline(new ArtistArtistOffLineMapper(result).map()).execute();
            mArtistsViewModels = getArtistsViewModel(result);
            mView.showArtists(mArtistsViewModels);
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

    class GetArtistsTaskOffline extends AsyncTask <String, Void, List<ArtistOffLine>> { // claseinterna

        private String artistName;

        public GetArtistsTaskOffline(String artistName) {
            this.artistName = artistName;
        }
        @Override
        protected List<ArtistOffLine> doInBackground (String... textToSearch) {


            List<ArtistOffLine> artistList = MusicDataBaseClient
                    .getInstance(mView)
                    .getMusicDataBase()
                    .getArtistDao()
                    .getArtists(artistName); // Sustituir por la función necesaria
            return artistList ;
        }
        @Override
        protected void onPostExecute (List<ArtistOffLine> artists) {
            //super.onPostExecute(artists);
            mArtistsViewModels = getArtistsViewModelOffLine(artists);//pasar de artistOffLine a ArtistViewModel
            mView.showArtists(mArtistsViewModels); // Actualizar la UI
        }
    }



    class SetArtistsTaskOffline extends AsyncTask <List<ArtistOffLine>, Void, Void> { // claseinterna

        private List<ArtistOffLine> artistList;

        public SetArtistsTaskOffline(List<ArtistOffLine> artists) {
            this.artistList = artists;
        }
        @Override
        protected Void doInBackground (List<ArtistOffLine>... artistsToWrite) {

            ArtistDao d = MusicDataBaseClient.getInstance(mView)
                    .getMusicDataBase().getArtistDao();

            for(ArtistOffLine artist : artistList){
                d.inserts(artist);
            }

            return null;

        }


    }





}
