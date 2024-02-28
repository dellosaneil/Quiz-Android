package com.thelazybattley.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.thelazybattley.common.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {

    private const val COLLECTION_NAME = "rizal-quiz-app"


    @Singleton
    @Provides
    fun providesFirebase() = Firebase.firestore

    @Singleton
    @Provides
    fun provideCollection(firestore: FirebaseFirestore) = run {
        val path = if (BuildConfig.DEBUG) {
            "debug"
        } else {
            "prod"
        }
        firestore.collection(COLLECTION_NAME).document(path)
    }
}
