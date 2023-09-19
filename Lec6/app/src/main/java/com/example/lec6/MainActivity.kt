package com.example.lec6

import android.app.ProgressDialog.BUTTON_NEGATIVE
import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import android.view.View.OnCreateContextMenuListener
import android.view.ContextMenu
import android.widget.Button
import android.widget.PopupMenu


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<Button>(R.id.btn)
        registerForContextMenu(listView)

        val popupView = findViewById<Button>(R.id.popup)

       popupView.setOnClickListener{
           val popupMenu = PopupMenu(this@MainActivity,popupView)
           popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
           popupMenu.setOnMenuItemClickListener { menuItem ->

               Toast.makeText(this@MainActivity, "You Clicked " + menuItem.title, Toast.LENGTH_SHORT).show()
               true
           }
           popupMenu.show()
       }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item2 -> Toast.makeText(this,"This is item2",Toast.LENGTH_SHORT).show();
            R.id.item3 -> Toast.makeText(this,"This is item3",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        val inflater = menuInflater;
        inflater.inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.action_item2 -> {
                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_item3 -> {
                Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_item4 -> {

                Toast.makeText(this, "item4", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_sub_item1 -> {
                Toast.makeText(this, "sub item1", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_sub_item2 -> {
                Toast.makeText(this, "sub item2", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


}