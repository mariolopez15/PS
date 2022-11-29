package es.udc.psi.p34lopez.domain.gig.datasource;

import java.util.List;

import es.udc.psi.p34lopez.domain.gig.Gig;

public interface GigDatasource {

    List<Gig> searchGig(String artistId);
}
