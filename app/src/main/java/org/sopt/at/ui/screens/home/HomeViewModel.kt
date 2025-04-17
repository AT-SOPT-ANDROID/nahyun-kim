package org.sopt.at.ui.screens.home

import androidx.lifecycle.ViewModel
import org.sopt.at.R
import org.sopt.at.model.Content

class HomeViewModel: ViewModel() {

    val top20Contents = List(4) { contentDummies }.flatten().shuffled() // 20개 더미
    val currentBroadCastContents = contentDummies.shuffled()

    companion object {
        val contentDummies = listOf<Content>(
            Content(
                title = "바니와 오빠들",
                image = R.drawable.img_poster_ex1
            ),
            Content(
                title = "슬기로울 전공의 생활",
                image = R.drawable.img_poster_ex2
            ),
            Content(
                title = "어느날 우리집 현관으로 멸망이 찾아왔다",
                image = R.drawable.img_poster_ex3
            ),
            Content(
                title = "협상의 기술",
                image = R.drawable.img_poster_ex4
            ),
            Content(
                title = "이혼보험",
                image = R.drawable.img_poster_ex5
            )
        )
    }
}