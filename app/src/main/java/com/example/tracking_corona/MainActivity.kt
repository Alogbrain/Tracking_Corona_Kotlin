package com.example.tracking_corona

import Fragment.ListDetail
import Fragment.Tracking
import Fragment.info_treatment
import Fragment.map.Map
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

//    private lateinit var viewPager: ViewPager
    private lateinit var bottomNavigationView: BottomNavigationView
    var currentFragment : Fragment = Tracking()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        replaceFragment(Tracking())


        bottomNavigationView =findViewById(R.id.navBottom)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId){
                R.id.item_home ->{
//                    viewPager.currentItem =0
                    replaceFragment(Tracking())
                    currentFragment = Tracking()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_list ->{
//                    viewPager.currentItem =1
                    replaceFragment(ListDetail())
                    currentFragment = ListDetail()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.item_map ->{
//                    viewPager.currentItem =2
                    replaceFragment(Map())
                    currentFragment = Map()
                    return@setOnNavigationItemSelectedListener true
                }
                else ->{
//                    viewPager.currentItem =3
                    replaceFragment(info_treatment())
                    currentFragment = info_treatment()
                    return@setOnNavigationItemSelectedListener true
                }
            }

        }
    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer,fragment)
        fragmentTransaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            true
        } else super.onKeyDown(keyCode, event)
    }

}
