package di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

import repo.user.UserRepo
import repo.user.UserRepoImpl

import service.user.UserService
import service.user.UserServiceImpl

val userModule = module {
    singleOf(::UserRepoImpl) bind UserRepo::class
    singleOf(::UserServiceImpl) bind UserService::class
}