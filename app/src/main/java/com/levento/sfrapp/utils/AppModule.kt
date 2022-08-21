package com.levento.sfrapp.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.levento.sfrapp.data.repository.BenefitsRepositoryImpl
import com.levento.sfrapp.data.repository.NewsRepositoryImpl
import com.levento.sfrapp.data.repository.UserRepositoryImpl
import com.levento.sfrapp.interfaces.BenefitsRepository
import com.levento.sfrapp.interfaces.NewsRepository
import com.levento.sfrapp.interfaces.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton





@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Singleton
    @Provides
    fun provideUserRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore
    ): UserRepository = UserRepositoryImpl(auth, db)

}
