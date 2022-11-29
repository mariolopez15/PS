package es.udc.psi.p34lopez.module.artist;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import es.udc.psi.p34lopez.R;
import es.udc.psi.p34lopez.module.BaseActivity;
import es.udc.psi.p34lopez.module.artist.adapter.ArtistAdapter;
import es.udc.psi.p34lopez.module.artist.presenter.ArtistsPresenter;
import es.udc.psi.p34lopez.module.artist.presenter.ArtistsPresenterImp;
import es.udc.psi.p34lopez.module.artist.presenter.ArtistsView;
import es.udc.psi.p34lopez.module.artist.viewmodel.ArtistViewModel;

public class ArtistsActivity extends BaseActivity implements ArtistsView {

    private static final String TAG = ArtistsActivity.class.getSimpleName();

    @BindView(R.id.artists_list)
    RecyclerView mRecycler;

    @BindView(R.id.artists_empty_list)
    TextView mEmptyView;

    private ArtistAdapter mAdapter;

    private ArtistsPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();

        mPresenter = new ArtistsPresenterImp(this);
        mPresenter.initFlow();
    }

    @Override
    public void showArtists(List<ArtistViewModel> artists) {

        mAdapter.setItems(artists);
        mEmptyView.setVisibility(View.GONE);
        mRecycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void showEmptyView() {

        mEmptyView.setVisibility(View.VISIBLE);
        mRecycler.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

        Toast.makeText(this, R.string.error_general, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateArtist(ArtistViewModel artist,
                             int position) {

        mAdapter.updateItem(artist, position);
    }

    private void setUpView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(linearLayoutManager);

        mAdapter = new ArtistAdapter();
        mRecycler.setAdapter(mAdapter);
    }
}
