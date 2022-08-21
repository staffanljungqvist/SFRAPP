package com.levento.sfrapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.levento.sfrapp.data.repository.BenefitsRepositoryImpl
import com.levento.sfrapp.feature_benefits.domain.repository.BenefitsRepository
import com.levento.sfrapp.feature_news.data.repository.NewsRepositoryImpl
import com.levento.sfrapp.feature_news.domain.repository.NewsRepository
import com.levento.sfrapp.feature_user.data.repository.UserRepositoryImpl
import com.levento.sfrapp.feature_user.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton





@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()


    @Provides
    @Singleton
    fun provideBenefitsRepository(firestore: FirebaseFirestore) : BenefitsRepository {
        return BenefitsRepositoryImpl(firestore)
    }

    @Provides
    @Singleton
    fun provideNewsRepository() : NewsRepository {
        return NewsRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): UserRepository = UserRepositoryImpl(auth, db)

}
