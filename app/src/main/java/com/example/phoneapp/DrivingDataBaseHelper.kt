// DrivingDataDatabaseHelper.kt
package com.example.phoneapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DrivingDataDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "DrivingData.db"
        private const val DATABASE_VERSION = 1

        const val TABLE_NAME = "test_results"
        const val COLUMN_ID = "_id"
        const val COLUMN_TEST_NUMBER = "test_number"
        const val COLUMN_SPEEDS = "speeds"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TEST_NUMBER INTEGER,
                $COLUMN_SPEEDS TEXT
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Function to insert test data into the database
    fun insertTestData(testNumber: Int, speeds: List<Double>) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TEST_NUMBER, testNumber)
            put(COLUMN_SPEEDS, speeds.joinToString(","))
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Function to retrieve all data from the database
    fun getAllData(): List<TestResult> {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val results = mutableListOf<TestResult>()

        try {
            if (cursor.moveToFirst()) {
                do {
                    // Check if the columns exist
                    val testNumberIndex = cursor.getColumnIndex(COLUMN_TEST_NUMBER)
                    val speedsIndex = cursor.getColumnIndex(COLUMN_SPEEDS)

                    if (testNumberIndex >= 0 && speedsIndex >= 0) {
                        val testNumber = cursor.getInt(testNumberIndex)
                        val speedsString = cursor.getString(speedsIndex)
                        val speeds = speedsString.split(",").map { it.toDouble() }
                        results.add(TestResult(testNumber, speeds))
                    } else {
                        // Handle the case where the columns are not found
                    }
                } while (cursor.moveToNext())
            }
        } catch (e: SQLException) {
            // Handle the exception as needed
        } finally {
            cursor.close()
            db.close()
        }

        return results
    }

    // Function to clear all data in the database
    fun clearAllData() {
        writableDatabase.execSQL("DELETE FROM $TABLE_NAME")
    }
}
