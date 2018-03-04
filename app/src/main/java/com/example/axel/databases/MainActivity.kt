package com.example.axel.databases

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
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
        super.onStart()
        show()
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
        }
        return super.onOptionsItemSelected(item)
    }

    fun show() {
        val studentsList = dbHelper.readAllStudents()
        val layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CustomAdapter(studentsList)
    }
}
