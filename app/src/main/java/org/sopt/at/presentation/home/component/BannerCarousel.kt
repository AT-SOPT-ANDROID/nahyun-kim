package org.sopt.at.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.sopt.at.R

@Composable
fun BannerCarousel() {
    val slideList = listOf<Int>(
        R.drawable.img_poster_ex1,
        R.drawable.img_poster_ex2,
        R.drawable.img_poster_ex3,
        R.drawable.img_poster_ex4,
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { slideList.size }
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.screen_padding_horizontal)),
            pageSpacing = dimensionResource(R.dimen.content_default_spacing),
            modifier = Modifier
        ) { page ->
            Image(
                painter = painterResource(slideList[page]),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
    }
}