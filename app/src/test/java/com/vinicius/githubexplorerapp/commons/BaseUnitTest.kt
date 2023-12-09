package com.vinicius.githubexplorerapp.commons

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
}