package es.udc.psi.p34lopez.dataOffLine;

import android.content.Context;

import androidx.room.Room;

public class MusicDataBaseClient {

    private Context mCtx;
    private static MusicDataBaseClient mInstance;
    private MusicDataBase musicDatabase ; //our app database object
    private MusicDataBaseClient (Context mCtx) {
        this.mCtx = mCtx;
//creating the app database with Room database builder
        musicDatabase = Room.databaseBuilder(mCtx, MusicDataBase.class, "MusicDataBase").build();
// see also option .allowMainThreadQueries() (NOT recommended)
    }
    public static synchronized MusicDataBaseClient getInstance (Context mCtx) {
        if (mInstance == null)
            mInstance = new MusicDataBaseClient(mCtx);
        return mInstance;
    }
    public MusicDataBase getMusicDataBase () {
        return musicDatabase ;
    }

}
