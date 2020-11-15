package com.example.fruitapp

sealed class Failure {
    object ServerFailure: Failure()
    abstract class FeatureFailure: Failure()
}
