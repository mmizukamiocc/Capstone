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
    private static final String SMS_TABLE = "SMSSender";

    private static final String RELATIONS_TABLE = "Relations";

    //TASK 2: DEFINE THE FIELDS (COLUMN NAMES) FOR THE TABLE
    private static final String PETS_KEY_FIELD_ID = "id";
    //private static final String PETS_USER_ID = "user_id";
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

    private static final String RELATIONS_KEY_FIELD_ID = "id";
    private static final String RELATIONS_FIELD_PET = "pet";
    private static final String RELATIONS_FIELD_USER = "user";


    private static final String DATABASE_TABLE = "Contacts";
    private static final String KEY_FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE_NUMBER = "phone_number";



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
                //+ PETS_USER_ID + " INTEGER, "
                + PETS_FIELD_NAME + " TEXT, "
                + PETS_FIELD_TYPE + " TEXT, "
                + PETS_FIELD_DESCRIPTION + " TEXT, "
                + PETS_FIELD_ADOPTION + " INTEGER, "
                + PETS_FIELD_LOST + " INTEGER, "
                + PETS_FIELD_IMAGE_URI + " TEXT, "
              //  + "FOREIGN KEY(" +PETS_USER_ID +") REFERENCES "
                + USERS_TABLE + "(" +USERS_KEY_FIELD_ID + ")";
        database.execSQL (createQuery);

        createQuery = "CREATE TABLE " + RELATIONS_TABLE + "("
                + RELATIONS_KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + RELATIONS_FIELD_PET + " TEXT, "
                + RELATIONS_FIELD_USER + " TEXT" + ")";

        database.execSQL(createQuery);
        createQuery = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIELD_NAME + " TEXT, "
                + FIELD_PHONE_NUMBER + " TEXT)";
        database.execSQL(createQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          int oldVersion,
                          int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + PETS_TABLE);
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);

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


        //values.put(PETS_USER_ID,pet.getUserId());

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
                new String[]{PETS_KEY_FIELD_ID, PETS_FIELD_TYPE,PETS_FIELD_NAME, PETS_FIELD_DESCRIPTION,
                        PETS_FIELD_ADOPTION,PETS_FIELD_LOST,PETS_FIELD_IMAGE_URI},
                null,
                null,
                null, null, null, null );

        //COLLECT EACH ROW IN THE TABLE
        if (cursor.moveToFirst()){
            do {
                Pet pet = new Pet(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),(cursor.getInt(4) == 1),(cursor.getInt(5) == 1), Uri.parse(cursor.getString(6)));
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

        //values.put(PETS_USER_ID,pet.getUserId());

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
                new String[]{PETS_KEY_FIELD_ID, PETS_FIELD_TYPE,PETS_FIELD_NAME, PETS_FIELD_DESCRIPTION,
                        PETS_FIELD_ADOPTION,PETS_FIELD_LOST,PETS_FIELD_IMAGE_URI},
                PETS_KEY_FIELD_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null );

        if (cursor != null)
            cursor.moveToFirst();
        Pet pet = new Pet(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3),
                (cursor.getInt(4) == 1),(cursor.getInt(5) == 1), Uri.parse(cursor.getString(6)));
        db.close();
        return pet;
    }


    //********** SMS TABLE OPERATIONS:  ADD, GET ALL, GET 1, DELETE

    public void addContact(Contact contacts) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // add key-value
        values.put(FIELD_NAME, contacts.getName());
        values.put(FIELD_PHONE_NUMBER, contacts.getPhone());

        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    public ArrayList<Contact> getAllContacts() {
        ArrayList<Contact> contactsList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0); // index column
                String name = cursor.getString(1);
                String phoneNumber = cursor.getString(2);

                Contact newContact = new Contact(id, name, phoneNumber);

                contactsList.add(newContact);
            }
            while(cursor.moveToNext());
        }

        db.close();

        return contactsList;
    }

    public Contact getContact(String name) {
        Contact contact = new Contact();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE,
                new String[] {KEY_FIELD_ID, FIELD_NAME, FIELD_PHONE_NUMBER},
                FIELD_NAME + " = ?", new String[]{name}, null, null, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = cursor.getInt(0); // index column
                String mName = cursor.getString(1);
                String phone = cursor.getString(2);

                contact.setName(mName);
                contact.setPhone(phone);
                contact = new Contact(id, mName, phone);
            }
            while(cursor.moveToNext());
        }
        db.close();
        return contact;
    }

    public void deleteContact(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_FIELD_ID + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }
}
