package com.example.fruitapp.remote

import com.example.fruitapp.Either
import com.example.fruitapp.Either.*
import com.example.fruitapp.Failure
import com.example.fruitapp.Failure.ServerFailure
import retrofit2.Response
import java.io.IOException

suspend fun <T> execute(getResponse:suspend ()->Response<T>) : Either<Failure, T> =
    try {
        parse(getResponse())
    } catch (exception: IOException){
        Left(ServerFailure)
    }

private fun <T> parse(response: Response<T>): Either<Failure, T> =
    if(response.isSuccessful){
        response.body()?.let {
            Right(it)
        } ?: Left (ServerFailure)
    } else {
        Left(ServerFailure)
    }

