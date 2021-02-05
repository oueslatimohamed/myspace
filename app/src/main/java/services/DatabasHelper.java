package services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Planet;
import model.User;

public class DatabasHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "mySpace.db";

    // tables names
    private static final String TABLE_USER = "user";
    private static final String TABLE_PLANET = "planet";

    // User Table Columns names
    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String USER_LAST_NAME = "user_lastname";
    private static final String USER_AGE = "user_age";
    private static final String USER_LOGIN = "user_login";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_SCORE = "user_score";

    // planet Table Columns names
    private static final String PLANET_ID = "planet_id";
    private static final String PLANET_NAME = "planet_name";
    private static final String PLANET_IMAGE_URL = "planet_url";
    private static final String PLANET_INFO = "planet_info";
    private static final String PLANET_COLOR = "planet_color";

    // create user table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_NAME + " TEXT,"
            + USER_LAST_NAME + " TEXT," + USER_AGE + " TEXT," + USER_LOGIN + " TEXT,"
            + USER_PASSWORD + " TEXT," +USER_SCORE+ " INTEGER DEFAULT 0" +")";

    // create planet table sql query
    private String CREATE_PLANET_TABLE = "CREATE TABLE " + TABLE_PLANET + "("
            + PLANET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PLANET_NAME + " TEXT,"
            + PLANET_IMAGE_URL + " TEXT," + PLANET_INFO + " TEXT," + PLANET_COLOR + " TEXT"
            +")";

    // drop table user sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    // drop table planet sql query
    private String DROP_PLANET_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLANET;

    public DatabasHelper(Context context){
        super(context ,DATABASE_NAME,null,DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER_TABLE);
        sqLiteDatabase.execSQL(CREATE_PLANET_TABLE);

        Log.i("DataBase", "Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // If table is already exist
        sqLiteDatabase.execSQL(DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(DROP_PLANET_TABLE);
        onCreate(sqLiteDatabase);
        Log.i("DataBase", "Tables deleted");

    }

    // Void to add user to user table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(USER_NAME, user.getmName());
        values.put(USER_LAST_NAME, user.getmLastname());
        values.put(USER_AGE, user.getmAge());
        values.put(USER_LOGIN, user.getmLogin());
        values.put(USER_PASSWORD, user.getmPassword());
        values.put(USER_SCORE, user.getmScore());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    // Void to add user to user table
    public void addPlanet(Planet planet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PLANET_NAME, planet.getmName());
        values.put(PLANET_IMAGE_URL, planet.getmImageurl());
        values.put(PLANET_INFO, planet.getmDescription());
        values.put(PLANET_COLOR, planet.getmColor());

        // Inserting Row
        db.insert(TABLE_PLANET, null, values);
        db.close();
    }
    // Void to get all planet
    public List<Planet> getAllPlanet() {
        // array of columns to fetch
        String[] columns = {
                PLANET_ID,
                PLANET_NAME,
                PLANET_IMAGE_URL,
                PLANET_INFO,
                PLANET_COLOR
        };
        // sorting orders
        String sortOrder =
                PLANET_ID + " ASC";
        List<Planet> plantList = new ArrayList<Planet>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        Cursor cursor = db.query(TABLE_PLANET, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        cursor.moveToFirst();
        while( ! cursor.isAfterLast() ) {
            Planet planet = new Planet( cursor.getInt( 0 ), cursor.getString( 1 ),
                    cursor.getString( 3 ),  cursor.getString( 4 ) , cursor.getInt(2)  );
            plantList.add( planet );
            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        // return user list
        return plantList;
    }

    // Void to check user
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {USER_ID};
        String selection = USER_LOGIN + "=?" + " and " + USER_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_USER, columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0){
            return true;
        } else {
            return false;
        }

    }

    // void to get user
    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {USER_ID,
                        USER_NAME, USER_LAST_NAME , USER_AGE , USER_LOGIN , USER_PASSWORD , USER_SCORE}, USER_LOGIN + "=?",
                new String[] { String.valueOf(username) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User();

        if(cursor.moveToFirst()){
            user.setId(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            user.setmName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
            user.setmLastname(cursor.getString(cursor.getColumnIndex(USER_LAST_NAME)));
            user.setmAge(cursor.getString(cursor.getColumnIndex(USER_AGE)));
            user.setmLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setmPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setmScore(cursor.getInt(cursor.getColumnIndex(USER_SCORE)));
        }
        // return contact
        return user;
    }

    // void to update score of user
    public boolean updateScores(int score, String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_SCORE,score);
        db.update(TABLE_USER, contentValues, USER_LOGIN + " = ? " ,
                new String[]{String.valueOf(username)});

        return true;
    }


}
