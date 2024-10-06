package com.effective.application

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.effective.bottom_menu.BottomMenuFragment
import com.effective.favorite.FavoriteFragment
import com.effective.home.HomeFragment
import com.effective.navigation.Navigation
import com.effective.response.ResponseFragment
import com.effective.vacancy.VacancyFragment
import dagger.hilt.android.AndroidEntryPoint
import com.effective.bottom_menu.R as BottomRes


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Navigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<BottomMenuFragment>(R.id.main_container)
        }
    }

    private var activeBottomFragmentTag = HomeFragment.TAG
    private var bottomFragmentsMap: MutableMap<String, List<String>> = mutableMapOf(
        HomeFragment.TAG to listOf(),
        FavoriteFragment.TAG to listOf()
    )

    override fun navigateToVacancy(id: String) {
        val bundle = bundleOf("vacancy_id" to id)
        val tag = "vacancy"
        supportFragmentManager.commit {
            add<VacancyFragment>(BottomRes.id.bottom_container, args = bundle, tag = tag)
        }
        val activeFragmentTags = bottomFragmentsMap[activeBottomFragmentTag] ?: emptyList()

        bottomFragmentsMap[HomeFragment.TAG] = buildList {
            addAll(activeFragmentTags)
            add(tag)
        }

    }

    override fun navigateToHome() {
        switchFragmentByTag(HomeFragment.TAG)
    }

    override fun navigateToFavorite() {
        switchFragmentByTag(FavoriteFragment.TAG)
    }

    override fun navigateToResponses() {}

    override fun navigateToProfile() {}

    override fun navigateToMessages() {}

    override fun navigateToResponse(vacancyTitle: String) {
        val bundle = bundleOf(ResponseFragment.ARG to vacancyTitle)

        supportFragmentManager.commit {
            add<ResponseFragment>(
                R.id.main_container,
                args = bundle,
                tag = ResponseFragment.TAG
            )
        }

        val activeFragmentTags = bottomFragmentsMap[activeBottomFragmentTag] ?: emptyList()

        bottomFragmentsMap[HomeFragment.TAG] = buildList {
            addAll(activeFragmentTags)
            add(ResponseFragment.TAG)
        }
    }

    override fun switchFragmentByTag(targetFragmentTag: String) {
        if (activeBottomFragmentTag != targetFragmentTag) {
            supportFragmentManager.commit {
                hideByTag(supportFragmentManager, activeBottomFragmentTag)
                showByTag(supportFragmentManager, targetFragmentTag)
                bottomFragmentsMap.forEach { (keyTag, tagsList) ->
                    if (keyTag == targetFragmentTag) {
                        for (tag in tagsList) {
                            val fragment = supportFragmentManager.findFragmentByTag(tag) ?: continue
                            show(fragment)
                        }
                    } else {
                        for (tag in tagsList) {
                            val fragment = supportFragmentManager.findFragmentByTag(tag) ?: continue
                            hide(fragment)
                        }
                    }
                }
            }
            activeBottomFragmentTag = targetFragmentTag
        }
    }

    override fun prepareBottomNavigation() {
        val favoriteFragment = FavoriteFragment()
        supportFragmentManager.commit {
            add(BottomRes.id.bottom_container, HomeFragment(), HomeFragment.TAG)
            add(BottomRes.id.bottom_container, favoriteFragment, FavoriteFragment.TAG)
            hide(favoriteFragment)
        }
    }

    override fun navigateBack() {
        if (bottomFragmentsMap[activeBottomFragmentTag]?.size == 0) {
            super.onBackPressed()
            finish()
        } else {
            val tags = bottomFragmentsMap[activeBottomFragmentTag] ?: return
            val lastTag = tags.lastOrNull() ?: return
            bottomFragmentsMap[activeBottomFragmentTag] = buildList {
                addAll(tags)
                removeLast()
            }
            supportFragmentManager.commit {
                val fragment = supportFragmentManager.findFragmentByTag(lastTag) ?: return
                remove(fragment)
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("super.onBackPressed()", "androidx.appcompat.app.AppCompatActivity")
    )
    override fun onBackPressed() {
        navigateBack()
    }
}

fun FragmentTransaction.hideByTag(supportFragmentManager: FragmentManager, tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    hide(fragment!!)
}

fun FragmentTransaction.showByTag(supportFragmentManager: FragmentManager, tag: String) {
    val fragment = supportFragmentManager.findFragmentByTag(tag)
    show(fragment!!)
}