package com.example.axel.databases

import android.provider.BaseColumns

/**
 * Created by Axel on 04-03-2018.
 */
object StudentContract{

    object StudentEntry : BaseColumns {
        val TABLE_NAME = "Student"
        val COLUMN_ID = "_id"
        val COLUMN_NAME = "Name"
        val COLUMN_SECTION = "Section"
    }
}