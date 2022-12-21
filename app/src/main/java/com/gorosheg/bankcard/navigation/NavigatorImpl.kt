package com.gorosheg.bankcard.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.gorosheg.bankcard.R
import com.gorosheg.bankcard.presentation.BankCardFragment

private const val BANK_CARD = "BankCartFragment"

class NavigatorImpl : Navigator {

    override fun navigateToBankCard(activity: FragmentActivity) {
        val fragment = BankCardFragment.newInstance()
        activity.navigateToNextFragment(fragment, BANK_CARD)
    }

    private fun FragmentActivity.navigateToNextFragment(fragment: Fragment, fragmentKey: String) {
        supportFragmentManager.beginTransaction().run {
            add(R.id.fragmentHolder, fragment)
            addToBackStack(fragmentKey)
            commit()
        }
    }
}