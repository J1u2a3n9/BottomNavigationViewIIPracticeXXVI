package ucb.edu.bo.bottomnavigationviewii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.IdRes
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    lateinit var mainPageAdapter: MainPageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar2:Toolbar=findViewById(R.id.toolbar2)
        var main_view_pager:ViewPager=findViewById(R.id.main_view_pager)
        var bottomNavigationView:BottomNavigationView=findViewById(R.id.bottomNavigationView)
        //Initialize components/views
        mainPageAdapter = MainPageAdapter(supportFragmentManager)
        setSupportActionBar(toolbar2)
        mainPageAdapter.setItems(arrayListOf( MainScreen.PROFILE, MainScreen.HOME))
        main_view_pager.adapter = mainPageAdapter
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
        main_view_pager.addOnPageChangeListener( object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                val selectedScreen = mainPageAdapter.getItems()[position]
                selectBottomNavigationViewMenuItem(selectedScreen.menuItemId)
                supportActionBar?.setTitle(selectedScreen.titleStringId)
            }
        })
    }

    private fun selectBottomNavigationViewMenuItem(@IdRes menuItemId: Int) {
        var bottomNavigationView:BottomNavigationView=findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemReselectedListener(null)
        bottomNavigationView.selectedItemId = menuItemId
        bottomNavigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        getMainScreenForMenuItem(item.itemId)?.let {
            scrollToScreen(it)
            supportActionBar?.setTitle(it.titleStringId)
            return true
        }
        return false
    }

    private fun scrollToScreen(it: MainScreen) {
        var main_view_pager:ViewPager=findViewById(R.id.main_view_pager)
        val screenPosition = mainPageAdapter.getItems().indexOf(it)
        if(screenPosition != main_view_pager.currentItem) {
            main_view_pager.currentItem = screenPosition
        }
    }
}
