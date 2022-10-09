package com.hammad.riyadh.di

import com.hammad.riyadh.data.MockData
import com.hammad.riyadh.repository.CoreRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideMockData(): MockData = MockData.Impl()

    @Provides
    fun provideCoreRepository(mockData: MockData): CoreRepository = CoreRepository.Impl(mockData)

}