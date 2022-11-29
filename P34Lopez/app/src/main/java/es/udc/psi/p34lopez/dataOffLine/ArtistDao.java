package es.udc.psi.p34lopez.dataOffLine;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserts(ArtistOffLine... artists);

    @Query("SELECT * FROM artist WHERE name LIKE :name" )
    public List<ArtistOffLine> getArtists (String name);

}
