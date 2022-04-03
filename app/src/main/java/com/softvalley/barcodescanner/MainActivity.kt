package com.softvalley.barcodescanner


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.findNavController
import com.softvalley.barcodescanner.utils.DataStoreHelper
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) {
            val dataStore= DataStoreHelper(this)
            lifecycleScope.launch {
                dataStore.clear()
                val navController= findNavController(R.id.nav_host_fragment)


                navController.navigate(R.id.splashFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}