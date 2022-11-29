package es.udc.psi.p34lopez.domain.gig.service;

import java.util.List;

import es.udc.psi.p34lopez.domain.gig.Gig;
import es.udc.psi.p34lopez.domain.gig.datasource.GigDatasource;

public class GigServiceImp implements GigService {

    private GigDatasource mDatasource;

    public GigServiceImp(GigDatasource gigDatasource) {

        mDatasource = gigDatasource;
    }

    @Override
    public List<Gig> searchGig(String artistId) {

        return mDatasource.searchGig(artistId);
    }
}
