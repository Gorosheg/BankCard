package com.gorosheg.android.model.recycler

import com.gorosheg.pure.ListItem
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

open class CommonAdapter private constructor(
    private val delegatesManager: AdapterDelegatesManager<List<ListItem>>,
) : ListDelegationAdapter<List<ListItem>>(delegatesManager) {

    constructor(vararg delegates: AdapterDelegate<List<ListItem>>) : this(
        delegatesManager = AdapterDelegatesManager(),
    ) {
        delegates.forEach(delegatesManager::addDelegate)
    }
}