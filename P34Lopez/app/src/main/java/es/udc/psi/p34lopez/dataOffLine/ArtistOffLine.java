package es.udc.psi.p34lopez.dataOffLine;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "artist")
public class ArtistOffLine {
    @PrimaryKey
    @NonNull
    public String artistId;
    @ColumnInfo(name = "name")
    public String name;

    public ArtistOffLine(String artistId, String name) {
        this.artistId=artistId;
        this.name=name;
    }

    @NonNull
    public String getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }
}
