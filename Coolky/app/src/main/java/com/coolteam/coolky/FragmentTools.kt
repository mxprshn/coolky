package com.coolteam.coolky

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class FragmentTools {
    companion object {
        fun changeFragment(fragmentToOpen: Fragment, manager: FragmentManager) {
            manager
                .beginTransaction()
                .replace(R.id.frameLayout , fragmentToOpen)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        }
    }
}