package com.school.amit.schoolapplication;

import android.content.Context;
import android.database.Cursor;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class DB extends SQLiteOpenHelper {

	//The Android's default system path of your application database.
	private static String DB_PATH 			= "data/data/com.school.amit.schoolapplication/databases/";
	private static String DB_NAME 			= "SchoolApp";
	private static String DATABASE_TABLE	= "School";
    static final String KEY_ROWID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";

	private final	Context			context;
	private 		SQLiteDatabase	db;


	// constructor
	public DB(Context context) {

		super( context , DB_NAME , null , 1);
		this.context = context;

	}


	// Creates a empty database on the system and rewrites it with your own database.
	public void create() throws IOException{

		boolean dbExist = checkDataBase();

		if(dbExist){
			//do nothing - database already exist
		}else{

			// By calling this method and empty database will be created into the default system path
			// of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
		}

	}

	// Check if the database exist to avoid re-copy the data
	private boolean checkDataBase(){

		SQLiteDatabase checkDB = null;

		try{


			String path = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);

		}catch(SQLiteException e){

			// database don't exist yet.
			e.printStackTrace();

		}

		if(checkDB != null){

			checkDB.close();

		}

		return checkDB != null ? true : false;
	}

	// copy your assets db to the new system DB
 	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	//Open the database
	public boolean open() {

		try {
			String myPath = DB_PATH + DB_NAME;
			db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
			return true;

		} catch(SQLException sqle) {
			db = null;
			return false;
		}

	}

	@Override
	public synchronized void close() {

		if(db != null)
			db.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

    public Cursor getAllSchool()
    {
       return db.query("School", new String[] {"schoolId","schoolName",
                }, null, null, null, null, null);
    }

    public Cursor getAllSchoolFromId(int[] schoolId)
    {
//        return db.query("School", new String[] {"schoolId","schoolName",
//        }, "schoolId=?", schoolId, null, null, null);
        String query="select schoolId, schoolName from School ";
        query=querybuild(query,schoolId,"schoolId");
        Log.i("db-getAllSchoolFromId", query );
        Cursor s=null;
                try{
                    s=db.rawQuery(query,null);
                }
                catch (SQLiteException e)
                {
                    Log.i("error","db");
                }
        return s;
    }

    public Cursor getAllCategoryFilters()
    {
        return db.query("Category",new String[]{"categoryName"},null,null,null,null,null);
    }

    public Cursor getAllLocations()
    {
        return db.query("Location",new String[]{"locationId,locationName"},null,null,null,null,null);
    }

    public Cursor getAllFavorite()
    {
        return db.query("School",new String[]{"schoolId","schoolName"},"schoolFavorite=?", new String[]{"1"},null,null,null);
    }


//    public Cursor getfilterApply(String[] locationId,int[] categoryId)
//    {
//        String query =null;
//        String temp[]=new String[categoryId.length];
//        temp=inttostring(categoryId);
//       // display(locationId);
//        display(temp);
//        if(categoryId!=null && locationId!=null)
//        {
//            query="select schoolId from schoolCategory ";
//            query=querybuild(query,temp,"categoryId");
//            query=query+" INTERSECT select schoolId from schoolLocations ";
//           query= querybuild(query,locationId,"locationId");
//        }
//        else if(categoryId!=null && locationId==null)
//        {
//            query="select schoolId from schoolCategory";
//            query=querybuild(query,temp,"categoryId");
//        }
//        else if (categoryId==null && locationId!=null)
//        {
//            query="select schoolId from schoolLocaitons";
//            query=querybuild(query,locationId,"locaitonId");
//        }
//        else if (categoryId==null && locationId==null)
//        {
//            query="select schoolId from  schoolLocations  UNION  select schoolId from schoolCategory ";
//        }
//        Log.i("db", query + "onclick()");
//        //String query = "select schoolId from  schoolLocations  where locationId='8' INTERSECT  select schoolId from schoolCategory where categoryId='2'";
//        return db.rawQuery(query,null);
//    }


    public Cursor getfilterApply(int[] locationId,int[] categoryId)
    {
        String query =null;
        display(locationId);
        display(categoryId);

        if(categoryId.length !=0 && locationId.length !=0)
        {
            query="select schoolId from SchoolCategory ";
            query=querybuild(query,categoryId,"categoryId");
            query=query+" INTERSECT select schoolId from SchoolLocations ";
            query= querybuild(query,locationId,"locationId");
        }
        else if(categoryId.length!=0 && locationId.length==0)
        {
            query="select schoolId from SchoolCategory ";
            query=querybuild(query,categoryId,"categoryId");
        }
        else if (categoryId.length==0 && locationId.length !=0)
        {
            query="select schoolId from SchoolLocations ";
            query=querybuild(query,locationId,"locationId");
        }
        else if (categoryId.length==0 && locationId.length==0)
        {
            query="select schoolId from  SchoolLocations  UNION  select schoolId from SchoolCategory ";
        }
        Log.i("db", query );
        //String query = "select schoolId from  SchoolLocations  where locationId='8' INTERSECT  select schoolId from schoolCategory where categoryId='2'";
        return db.rawQuery(query,null);
        //return db.rawQuery("select schoolId from SchoolCategory where (categoryId='4' OR categoryId = '2') INTERSECT select schoolId from SchoolLocations where (locationId='1' OR locationId = '2' OR locationId = '3' OR locationId = '4' OR locationId = '5' OR locationId = '6' OR locationId = '7')",null);
    }


    private void display(int[] temp) {

        for (int i=0;i<temp.length;i++) {

            Log.i("id=",""+temp[i]);

        }
    }


    private String querybuild(String query, int[] array,String str) {

        query=query+"where ("+str+"='"+array[0]+"'";
        for (int i=1; i<array.length;i++)
        {
            query=query+" OR "+str+" = '"+array[i] +"'";
        }
        query=query+")";
        return query;
    }

    private String[] inttostring(int [] temp)
    {
        String []temparr= new String[temp.length];
        for(int i=0;i<temp.length;i++)
            temparr[0]=Integer.toString(i);
        return  temparr;
    }

    public Cursor getAllSchoolAddressId(int id) {
        return db.rawQuery("select * from SchoolAddress",null);
    }

//    "select schoolId from  schoolLocation  where locationId='1' INTERSECT  select schoolId from schoolCategory where categoryId='1'"
//    select schoolId from SchoolCategory where ( categoryId="1" OR categoryId="2" OR categoryId="14") INTERSECT select schoolId from SchoolLocations where ( locationId="8" OR locationId="9");
   // select schoolName from School INNER JOIN SchoolLocations   on School.schoolID=SchoolLocations.locationID where (SchoolLocations.locationID = "1" OR SchoolLocations.LocationID="2") AND School.schoolFavorite="1" ;
   // select schoolName from School INNER JOIN SchoolLocations   on School.schoolID=SchoolLocations.locationID AND SchoolCategory.ID where SchoolLocations.locationID = "1" OR SchoolLocations.LocationID="2"   ;
	// PUBLIC METHODS TO ACCESS DB CONTENT
	// -----------------------------------------------------------------------------------------------------------------


	// Get locations
//	public List<Location> getLocations() {
//
//		List<Location> locations = null;
//`
//
//			String			query	= "SELECT * FROM " + TABLE_LOCATION;
//			SQLiteDatabase	db		= SQLiteDatabase.openDatabase( DB_PATH + DB_NAME , null, SQLiteDatabase.OPEN_READWRITE);
//			Cursor			cursor	= db.rawQuery(query, null);
//
//			// go over each row, build elements and add it to list
//			locations = new LinkedList<Location>();
//
//			if (cursor.moveToFirst()) {
//				do {
//
//					Location location	= new Location();
//					location.id			= Integer.parseInt(cursor.getString(0));
//					location.lat		= Double.parseDouble(cursor.getString(1));
//					location.lng		= Double.parseDouble(cursor.getString(2));
//					location.name		= cursor.getString(3);
//
//					locations.add(location);
//
//				} while (cursor.moveToNext());
//			}
//		} catch(Exception e) {
//			// sql error
//		}
//
//		return locations;
//	}

    // Get Schools
//	public List<School> getSchools() {
//
//		List<Schools> School = null;
//
//		try {
//
//			String			query	= "SELECT * FROM " + TABLE_LOCATION;
//			SQLiteDatabase	db		= SQLiteDatabase.openDatabase( DB_PATH + DB_NAME , null, SQLiteDatabase.OPEN_READWRITE);
//			Cursor			cursor	= db.rawQuery(query, null);
//
//			// go over each row, build elements and add it to list
//			locations = new LinkedList<Location>();
//
//			if (cursor.moveToFirst()) {
//				do {
//
//					School location	= new Location();
//					School.id			= Integer.parseInt(cursor.getString(0));
//					Schoos.lat		= Double.parseDouble(cursor.getString(1));
//					School.lng		= Double.parseDouble(cursor.getString(2));
//					Schoos.name		= cursor.getString(3);
//
//					locations.add(location);
//
//				} while (cursor.moveToNext());
//			}
//		} catch(Exception e) {
//			// sql error
//		}
//
//		return locations;
//	}
}