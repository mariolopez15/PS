package es.udc.psi.p34lopez.dataOffLine;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = { ArtistOffLine.class})
public abstract class MusicDataBase extends RoomDatabase {
    // ArtistDao is a class annotated with @Dao.
    abstract public ArtistDao getArtistDao();

}