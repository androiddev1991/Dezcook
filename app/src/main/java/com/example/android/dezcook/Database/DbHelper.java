package com.example.android.dezcook.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Android on 6/10/2016.
 */
public class DbHelper  extends SQLiteOpenHelper {

    public static final String DBNAME = "database.sqlite";
    private static String DB_PATH;
    private SQLiteDatabase myDataBase;
    private final Context mycontext;

    public DbHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.mycontext = context;
        DB_PATH = context.getFilesDir().getParentFile().getPath() + "/databases/";

    }


    public void createDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {//database is exist
            return;
        }
        else{
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error Copying Database");
            }
        }
    }



    private boolean checkDataBase() {
        SQLiteDatabase DBcheck=null;
        String myPath=DB_PATH+DBNAME;
        try
        {
           DBcheck= SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY);
        }catch (SQLException e){}
        if(DBcheck!=null)
        {
            DBcheck.close();
        }
        return DBcheck!=null;
    }
    private void copyDataBase() throws IOException
    {
        InputStream myInput=
                mycontext.getAssets().open(DBNAME);
        String outFileName=DB_PATH+DBNAME;
        OutputStream myOutPut=new FileOutputStream(outFileName);
        byte[] buffer=new byte[1024];
        int length;
        while ((length=myInput.read(buffer))>0)
        {
            myOutPut.write(buffer,0,length);
        }
        myOutPut.flush();
        myOutPut.close();
        myInput.close();
    }
    public void openDataBase() throws SQLException
    {
        String myPath=DB_PATH+DBNAME;
        myDataBase=SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public synchronized void close()
    {
        if(myDataBase!=null)
            myDataBase.close();
        super.close();
    }
    // It closes the database if it exists, and then calls super.close()
    // which calls close() in the parent class. It is synchronized which means it cannot run in parallel,
    // calls to that method are queud up to avoid corruption of database

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    public SQLiteDatabase getMyDataBase()
    {
        return myDataBase;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}