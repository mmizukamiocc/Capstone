package com.example.mmizukami.capstone;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;

class DBHelper extends SQLiteOpenHelper {

    //TASK 1: DEFINE THE DATABASE VERSION, NAME AND TABLE NAME
    static final String DATABASE_NAME = "PetProtector";
    private static final int DATABASE_VERSION = 1;

    private static final String PETS_TABLE = "Pets";
    private static final String USERS_TABLE = "Users";


    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String PETS_KEY_FIELD_ID = "id";
    private static final String PETS_USER_ID = "user_id";
    private static final String PETS_FIELD_NAME = "pet_name";
    private static final String PETS_FIELD_TYPE = "type";
    private static final String PETS_FIELD_DESCRIPTION = "description";
    private static final String PETS_FIELD_ADOPTION = "adoption";
    private static final String PETS_FIELD_LOST = "lost";
    private static final String PETS_FIELD_IMAGE_URI = "imageURI";

    private static final String USERS_KEY_FIELD_ID = "id";
    private static final String USERS_FIELD_USERNAME = "username";
    private static final String USERS_FIELD_REALNAME = "real_name";
    private static final String USERS_FIELD_EMAIL = "email";
    private static final String USERS_FIELD_PHONE = "phone";
    private static final String USERS_FIELD_PASSWORD = "password";



    public DBHelper(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate (SQLiteDatabase database){

        String createQuery = "CREATE TABLE " + USERS_TABLE + "("
                + USERS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERS_FIELD_USERNAME + " TEXT, "
                + USERS_FIELD_REALNAME + " TEXT, "
                + USERS_FIELD_EMAIL + " TEXT, "
                + USERS_FIELD_PHONE + " TEXT, "
                + USERS_FIELD_PASSWORD + " TEXT" + ")";
        database.execSQL (createQuery);


        createQuery = "CREATE TABLE " + PETS_TABLE + "("
                + PETS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PETS_USER_ID + " INTEGER, "
                + PETS_FIELD_NAME + " TEXT, "
                + PETS_FIELD_TYPE + " TEXT, "
                + PETS_FIELD_DESCRIPTION + " TEXT, "
                + PETS_FIELD_ADOPTION + " INTEGER, "
                + PETS_FIELD_LOST + " INTEGER, "
                + PETS_FIELD_IMAGE_URI + " TEXT, "
                + "FOREIGN KEY(" +PETS_USER_ID +") REFERENCES "
                + USERS_TABLE + "(" +USERS_KEY_FIELD_ID + ")";
        database.execSQL (createQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + PETS_TABLE);


        onCreate(database);
    }

    //********** USER TABLE OPERATIONS:  ADD, GETALL, EDIT, DELETE

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION
        values.put(USERS_FIELD_USERNAME, user.getUserName());

        values.put(USERS_FIELD_REALNAME, user.getRealName()); // task name

        values.put(USERS_FIELD_EMAIL,user.getEmail());

        values.put(USERS_FIELD_PHONE,user.getPhone());

        values.put(USERS_FIELD_PASSWORD,user.getPassword());
        // INSERT THE ROW IN THE TABLE
        db.insert(USERS_TABLE, null, values);

        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = database.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID, USERS_FIELD_USERNAME,USERS_FIELD_REALNAME, USERS_FIELD_EMAIL,USERS_FIELD_PHONE,USERS_FIELD_PASSWORD},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
                usersList.add(user);
            } while (cursor.moveToNext());
        }
        return usersList;
    }

    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(USERS_TABLE, USERS_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(user.getId())});
        db.close();
    }

    public void deleteAllUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE, null, null);
        db.close();
    }

    public void updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USERS_FIELD_USERNAME, user.getUserName());
        values.put(USERS_FIELD_REALNAME, user.getRealName());
        values.put(USERS_FIELD_EMAIL,user.getEmail());
        values.put(USERS_FIELD_PHONE,user.getPhone());
        values.put(USERS_FIELD_PASSWORD,user.getPassword());

        db.update(USERS_TABLE, values, USERS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                USERS_TABLE,
                new String[]{USERS_KEY_FIELD_ID,USERS_FIELD_USERNAME,USERS_FIELD_REALNAME,USERS_FIELD_EMAIL,
                        USERS_FIELD_PHONE,USERS_FIELD_PASSWORD},
                USERS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();
        User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));
        db.close();
        return user;
    }




    //********** PETS TABLE OPERATIONS:  ADD, GETALL, EDIT, DELETE
    public void addPet(Pet pet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //ADD KEY-VALUE PAIR INFORMATION FOR THE TASK DESCRIPTION


        values.put(PETS_USER_ID,pet.getUserId());

        values.put(PETS_FIELD_TYPE,pet.getType());

        values.put(PETS_FIELD_NAME, pet.getPetName());

        values.put(PETS_FIELD_DESCRIPTION,pet.getDescription());

        values.put(PETS_FIELD_ADOPTION,(pet.isAdopted()? 0:1));

        values.put(PETS_FIELD_LOST,(pet.isLost()? 0:1));

        values.put(PETS_FIELD_IMAGE_URI,pet.getImageUri().toString());

        // INSERT THE ROW IN THE TABLE
        db.insert(PETS_TABLE, null, values);


        // CLOSE THE DATABASE CONNECTION
        db.close();
    }

    public ArrayList<Pet> getAllPets() {
        ArrayList<Pet> petsList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        //Cursor cursor = database.rawQuery(queryList, null);
        Cursor cursor = database.query(
                PETS_TABLE,
                new String[]{PETS_KEY_FIELD_ID,PETS_USER_ID, PETS_FIELD_TYPE,PETS_FIELD_NAME, PETS_FIELD_DESCRIPTION,
                        PETS_FIELD_ADOPTION,PETS_FIELD_LOST,PETS_FIELD_IMAGE_URI},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                Pet pet = new Pet(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),(cursor.getInt(5) == 1),(cursor.getInt(6) == 1), Uri.parse(cursor.getString(7)));
                petsList.add(pet);
            } while (cursor.moveToNext());
        }
        return petsList;
    }

    public void deletePet(Pet pet){
        SQLiteDatabase db = this.getWritableDatabase();

        // DELETE THE TABLE ROW
        db.delete(PETS_TABLE, PETS_KEY_FIELD_ID + " = ?",
                new String[] {String.valueOf(pet.getId())});
        db.close();
    }

    public void deleteAllPets()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PETS_TABLE, null, null);
        db.close();
    }


    public void updatePet(Pet pet){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PETS_USER_ID,pet.getUserId());

        values.put(PETS_FIELD_TYPE,pet.getType());

        values.put(PETS_FIELD_NAME, pet.getPetName());

        values.put(PETS_FIELD_DESCRIPTION,pet.getDescription());

        values.put(PETS_FIELD_ADOPTION,pet.isAdopted() ? 0:1);

        values.put(PETS_FIELD_LOST,pet.isLost() ? 0:1);

        values.put(PETS_FIELD_IMAGE_URI,pet.getImageUri().toString());

        db.update(PETS_TABLE, values, PETS_KEY_FIELD_ID + " = ?",
                new String[]{String.valueOf(pet.getId())});
        db.close();
    }


    public Pet getPet(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                PETS_TABLE,
                new String[]{PETS_KEY_FIELD_ID,PETS_USER_ID, PETS_FIELD_TYPE,PETS_FIELD_NAME, PETS_FIELD_DESCRIPTION,
                        PETS_FIELD_ADOPTION,PETS_FIELD_LOST,PETS_FIELD_IMAGE_URI},
                PETS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();
        Pet pet = new Pet(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),
                (cursor.getInt(5) == 1),(cursor.getInt(6) == 1), Uri.parse(cursor.getString(7)));
        db.close();
        return pet;
    }

}
