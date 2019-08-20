package com.fairphone.assignment.rss.utils

import com.fairphone.assignment.rss.model.BlogItemModel

import java.util.Comparator

/**
 * The SortUserInfoByTitle class implements an application that
 * simply sorts given list.
 */
class SortUserInfoByTitle : Comparator<BlogItemModel> {
    //To be  used to sort the list by ttile name
    override fun compare(list1: BlogItemModel, list2: BlogItemModel): Int {
        return list1.title.compareTo(list2.title)
    }
}
