package org.sopt.at.presentation.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import org.sopt.at.R
import org.sopt.at.ui.theme.ButtonTint
import org.sopt.at.ui.theme.TextFieldBg
import org.sopt.at.ui.theme.White

@Composable
fun RecommendContent(
    title: String,
    isSupportRanking: Boolean,
    isShowMoreButton: Boolean,
    imageUrls: List<String>,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.screen_padding_horizontal)),
    ) {
        Text( // 타이틀
            text = title,
            style = MaterialTheme.typography.titleSmall,
        )
        Spacer(Modifier.weight(1f))
        if (isShowMoreButton) { // 더보기
            Text(
                text = stringResource(R.string.more),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
    Spacer(Modifier.height(dimensionResource(R.dimen.recommend_spacing_between_title_and_contents)))
    LazyRow( // 추천 목록
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = dimensionResource(R.dimen.screen_padding_horizontal))
    ) {
        itemsIndexed(imageUrls) { index, content ->
            ContentItem(
                ranking = if (isSupportRanking) index + 1 else null,
                imageUrl = content
            )
        }
    }
}

@Composable
fun ContentItem(
    modifier: Modifier = Modifier,
    ranking: Int?,
    imageUrl: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Bottom
    ) {
        if (ranking != null) {
            Text( // 랭킹
                text = ranking.toString(),
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = White,
                fontStyle = FontStyle.Italic,
                letterSpacing = (-2).sp,
                modifier = modifier.height(80.dp)
            )
        }
        GlideImage(
            imageModel = imageUrl,
            shimmerParams = ShimmerParams(
                baseColor = TextFieldBg,
                highlightColor = ButtonTint,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            contentDescription = "content poster",
            contentScale = ContentScale.Crop,
            failure = {
                Text(text = "image request failed.")
            },
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}