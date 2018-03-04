package com.example.axel.databases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.axel.databases.StudentContract.StudentEntry
import com.example.axel.databases.StudentContract.StudentEntry.COLUMN_ID
import com.example.axel.databases.StudentContract.StudentEntry.COLUMN_NAME
import com.example.axel.databases.StudentContract.StudentEntry.COLUMN_SECTION
import com.example.axel.databases.StudentContract.StudentEntry.TABLE_NAME

/**
 * Created by Axel on 04-03-2018.
 */
class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun addStudent(student: Student) {
        val values = ContentValues()
        values.put(StudentEntry.COLUMN_ID, student.id)
        values.put(StudentEntry.COLUMN_NAME, student.name)
        values.put(StudentEntry.COLUMN_SECTION, student.section)
        val db = writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun readAllStudents(): ArrayList<Student> {
        val studentsList = ArrayList<Student>()
        val queryToRead = "SELECT * FROM $TABLE_NAME"
        val db = writableDatabase
        val cursor = db.rawQuery(queryToRead, null)
        var id: Int
        var name: String
        var section: String
        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast == false) {
                id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                section = cursor.getString(cursor.getColumnIndex(COLUMN_SECTION))
                studentsList.add(Student(id, name, section))
                cursor.moveToNext()
            }
        }
        cursor.close()
        db.close()
        return studentsList
    }

    fun deleteStudent(studentId: String) {
        val db = writableDatabase
        val selection = "$COLUMN_ID LIKE ?"
        val selectionArgs = arrayOf(studentId)
        db.delete(TABLE_NAME, selection, selectionArgs)
        db.close()
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "StudentDB.db"
        val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT, $COLUMN_SECTION TEXT)"
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}