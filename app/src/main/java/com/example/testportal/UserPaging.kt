package com.example.testportal

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testportal.network.response.DataItem
import com.example.testportal.network.retrofit.ApiService

class UserPaging(private val apiService: ApiService) : PagingSource<Int, DataItem>() {

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(page, 10)

            val stories = responseData.data ?: emptyList()  // Handle nullability here

            LoadResult.Page(
                data = stories,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (stories.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

}