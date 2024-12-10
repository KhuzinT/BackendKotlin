package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

import repo.post.PostRepo
import repo.post.PostRepoImpl

import service.post.PostService
import service.post.PostServiceImpl

val postModule = module {
    singleOf(::PostRepoImpl) bind PostRepo::class
    singleOf(::PostServiceImpl) bind PostService::class
}