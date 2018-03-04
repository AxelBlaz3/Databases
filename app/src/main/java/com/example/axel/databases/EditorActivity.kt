package com.example.axel.databases

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_editor.*

class EditorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.editor_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_save -> {
                newStudent()
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun newStudent() {

        val dbhelper = DBHelper(this)
        val id = Integer.parseInt(editText_id.text.toString())
        val name = editText_name.text.toString()
        val section = editText_section.text.toString()
        dbhelper.writableDatabase
        dbhelper.addStudent(Student(id, name, section))
        editText_id.setText("")
        editText_name.setText("")
        editText_section.setText("")
        Toast.makeText(this, "New student added ☺", Toast.LENGTH_SHORT).show()
    }

    fun removeStudent() {
        val dbhelper = DBHelper(this)
        val studentId = editText_id.text.toString()
        dbhelper.writableDatabase
        val result = dbhelper.deleteStudent(studentId)
    }

    fun showAllStudents() {
        val dbhelper = DBHelper(this)
        dbhelper.readAllStudents()
    }
}
