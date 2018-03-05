package com.example.axel.databases

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton2.setOnClickListener {
            val intent = Intent(this, EditorActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        show()
        super.onStart()
    }

    /**
     *  As per developer.android.com guidelines, instant db closing and creating is expensive.
     *  So I'm just making sure that I just close it when the application is closed by the user
     */
    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.operations_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_show -> {
                show()
                return true
            }
            R.id.menu_delete -> {
                delete()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun show() {
        val studentsList = dbHelper.readAllStudents()
        val layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CustomAdapter(studentsList)
    }

    fun delete(){
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Delete student")
        val editText = EditText(this)
        alertDialog.setView(editText)
        alertDialog.setPositiveButton("Ok", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                dbHelper.deleteStudent(editText.text.toString())
                show()
            }

        })
        alertDialog.setNegativeButton("Cancel", object : DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                val alertClose = alertDialog.create()
                alertClose.dismiss()
            }
        })
        val alert = alertDialog.create()
        alert.show()
    }
}
