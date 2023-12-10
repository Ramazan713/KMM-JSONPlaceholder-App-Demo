package com.example.kmmjsonplaceholderapp.di

import com.example.kmmjsonplaceholderapp.core.data.repo.PostRepoImpl
import com.example.kmmjsonplaceholderapp.core.data.repo.UserRepoImpl
import com.example.kmmjsonplaceholderapp.core.domain.repo.PostRepo
import com.example.kmmjsonplaceholderapp.core.domain.repo.UserRepo
import org.koin.dsl.module

internal val repoModule = module {

    single<UserRepo> {
        UserRepoImpl(get(),get(),get())
    }

    single<PostRepo> {
        PostRepoImpl(get(),get(),get())
    }
}