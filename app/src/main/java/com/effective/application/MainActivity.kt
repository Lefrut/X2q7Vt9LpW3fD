package com.effective.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.effective.bottom_menu.BottomMenuFragment
import com.effective.home.HomeFragment
import com.effective.navigation.BottomNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.effective.bottom_menu.R as BottomRes


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BottomMenuFragment>(R.id.main_container)
        }
    }

    private var activeBottomFragment: Fragment = HomeFragment.Singleton

    override fun navigateToHome() {
        switchFragment(HomeFragment.Singleton)
    }

    override fun navigateToFavorite() {
    }

    override fun navigateToResponses() {
    }

    override fun navigateToProfile() {
    }

    override fun navigateToMessages() {
    }

    override fun <T : Fragment> switchFragment(targetFragment: T) {
        if (activeBottomFragment != targetFragment) {
            supportFragmentManager.commit {
                hide(activeBottomFragment)
                show(targetFragment)
            }
            activeBottomFragment = targetFragment
        }
    }

    override fun prepareBottomNavigation() {
        supportFragmentManager.commit {
            add(BottomRes.id.bottom_container, HomeFragment.Singleton)
        }
    }
}