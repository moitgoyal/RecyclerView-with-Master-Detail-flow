package com.example.mg156.assignment4

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import com.google.gson.Gson

class  RecyclerViewActivity : AppCompatActivity() , RecyclerViewFragment.OnFragmentInteractionListener {

    lateinit var recFragment: Fragment
    var mTwoPane = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        supportActionBar?.setTitle("Movie Details")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieList = Gson().fromJson(movies , Array <MovieDataClass>::class.java ).toMutableList()
        posterTable = mutableMapOf()
        posterTable["Fight Club"] = R.drawable.fight_club
        posterTable["Forrest Gump"] = R.drawable.forrest_gump
        posterTable["The Godfather"] = R.drawable.godfather
        posterTable["The Godfather: Part II"] = R.drawable.godfather_2
        posterTable["Psycho"] = R.drawable.psycho
        posterTable["Pulp Fiction"] = R.drawable.pulp_fiction
        posterTable["Schindler's List"] = R.drawable.schindler_list
        posterTable["The Shawshank Redemption"] = R.drawable.shawshank_redemption
        posterTable["The Dark Knight"] = R.drawable.the_dark_knight
        posterTable["The Green Mile"] = R.drawable.the_green_mile


        if (savedInstanceState == null) {
            recFragment = RecyclerViewFragment.newInstance(R.id.recViewFragmentLayout.toString())
        }

        if(findViewById<FrameLayout>(R.id.recViewFragContainerSlave) != null){
            mTwoPane = true
            supportFragmentManager.beginTransaction().replace(R.id.recViewFragContainer,
                    recFragment).addToBackStack(null).commit()

            supportFragmentManager.beginTransaction().replace(R.id.recViewFragContainerSlave,
                    MovieFragment.newInstance(movieList[0],posterTable.get(movieList[0].title)!!)).addToBackStack(null).commit()
        }
        else{
            mTwoPane = false
            supportFragmentManager.beginTransaction().replace(R.id.recViewFragContainer,
                    recFragment).addToBackStack(null).commit()
        }

    }


    override fun onFragmentInteraction(movie : MovieDataClass){
        if(mTwoPane == true){
            supportFragmentManager.beginTransaction().replace(R.id.recViewFragContainerSlave,
                    MovieFragment.newInstance(movie,posterTable.get(movie.title)!!)).addToBackStack(null).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.recViewFragContainer, MovieFragment.newInstance(movie,posterTable.get(movie.title)!!)).addToBackStack(null).commit()
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        supportFragmentManager.putFragment(outState, "recFragment", recFragment)
    }
}
