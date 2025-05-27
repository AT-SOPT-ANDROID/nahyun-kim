package org.sopt.at.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.toImmutableList
import org.sopt.at.R
import org.sopt.at.core.designsystem.component.textfield.CommonTextField
import org.sopt.at.core.designsystem.theme.ATSOPTANDROIDTheme
import org.sopt.at.core.designsystem.theme.TvingTheme

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchWord by viewModel.searchWord.collectAsStateWithLifecycle()
    val searchResultNicknameList by viewModel.searchResult.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SearchScreen(
        paddingValues = paddingValues,
        searchWord = searchWord,
        onSearchWordChange = viewModel::updateSearchNickname,
        nicknameSearch = viewModel::searchNickname,
        searchResult = searchResultNicknameList.toImmutableList()
    )
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    searchWord: String,
    onSearchWordChange: (String) -> Unit,
    nicknameSearch: () -> Unit,
    searchResult: ImmutableList<String>
) {
    Column(
        modifier = Modifier
            .background(TvingTheme.colors.background)
            .padding(paddingValues)
            .padding(
                horizontal = dimensionResource(R.dimen.screen_padding_horizontal),
                vertical = dimensionResource(R.dimen.screen_padding_vertical)
            )
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CommonTextField(
            placeholder = "검색할 사용자 닉네임을 입력해보세요.",
            value = searchWord,
            onValueChange = onSearchWordChange,
            keyboardActions = KeyboardActions {
                nicknameSearch() // 닉네임 검색 진행
            }
        )
        Spacer(Modifier.height(10.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(
                items = searchResult,
                key =  { index, _ -> index },
            ) { index, nickname ->
                Text(
                    nickname,
                    color = TvingTheme.colors.basicWhite,
                    style = TvingTheme.typography.body
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    ATSOPTANDROIDTheme {
        SearchScreen(
            paddingValues = PaddingValues(),
            searchWord = "",
            onSearchWordChange = { },
            nicknameSearch = { },
            searchResult = immutableListOf("김나현", "김나현2")
        )
    }
}