package com.fairphone.assignment.rss.utils

import com.fairphone.assignment.rss.model.BlogItemModel
import org.junit.Before
import org.junit.Test
import java.util.*

class SortUserInfoByTitleTest {

    lateinit var response: ArrayList<BlogItemModel>

    @Before
    fun setUp() {
        response = ArrayList<BlogItemModel>()


    }

    @Test
    fun compare() {
        val blogItem1 = BlogItemModel(
            "one",
            "htts://fairphone.com",
            "capital",
            "region",
            "sub region",
            "2"
        )
        val blogItem2 = BlogItemModel(
            "two",
            "htts://fairphone.com",
            "capital",
            "region",
            "sub region",
            "code"
        )
        val blogItem3 = BlogItemModel(
            "three",
            "htts://fairphone.com",
            "capital",
            "region",
            "sub region",
            "code"
        )
        val blogItem4 = BlogItemModel(
            "four",
            "htts://fairphone.com",
            "capital",
            "region",
            "sub region",
            "code"
        )

        response.add(blogItem1)
        response.add(blogItem2)
        response.add(blogItem3)
        response.add(blogItem4)
        Collections.sort(response, SortUserInfoByTitle())

        assert(response[0].title.equals("four"))

    }
}