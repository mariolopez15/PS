package es.udc.psi.p34lopez.domain.gig.service;

import java.util.List;

import es.udc.psi.p34lopez.domain.gig.Gig;

public interface GigService {

    List<Gig> searchGig(String artistId);
}
