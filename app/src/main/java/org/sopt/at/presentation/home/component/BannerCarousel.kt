package org.sopt.at.presentation.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.collections.immutable.ImmutableList
import org.sopt.at.R
import org.sopt.at.core.designsystem.theme.ButtonTint
import org.sopt.at.core.designsystem.theme.TextFieldBg

@Composable
fun BannerCarousel(
    bannerImageUrls: ImmutableList<String>,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerImageUrls.size }
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.screen_padding_horizontal)),
            pageSpacing = dimensionResource(R.dimen.content_default_spacing),
            modifier = Modifier
        ) { pageIndex ->
            GlideImage(
                imageModel = bannerImageUrls[pageIndex],
                shimmerParams = ShimmerParams(
                    baseColor = TextFieldBg,
                    highlightColor = ButtonTint,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                contentDescription = "banner poster",
                contentScale = ContentScale.Crop,
                failure = {
                    Text(text = "image request failed.")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .clip(RoundedCornerShape(12.dp)),
            )
        }
    }
}