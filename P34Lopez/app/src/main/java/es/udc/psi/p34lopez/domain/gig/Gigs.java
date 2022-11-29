package es.udc.psi.p34lopez.domain.gig;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Gigs {

    @SerializedName("setlist")
    private List<Gig> gigs;

    public Gigs(List<Gig> gigs) {

        this.gigs = gigs;
    }

    public List<Gig> getGigs() {

        return gigs;
    }
}
