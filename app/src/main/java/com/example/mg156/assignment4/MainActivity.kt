package com.example.mg156.assignment4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RestrictTo
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity(), FrontFragment.OnFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.setTitle("Home")

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,FrontFragment.newInstance(R.id.fragmentFrontLayout.toString()),"Fragment Front").commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.home) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (id == R.id.movie_details) {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFragmentInteraction(v: View){
        when (v.id) {
            R.id.btnAboutMe -> {
                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fragmentContainer, AboutMeFragment.newInstance(R.id.aboutMeLayout.toString()))
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
            R.id.btnMovieDetails -> {
                val intent = Intent(this, RecyclerViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
