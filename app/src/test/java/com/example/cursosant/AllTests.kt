package com.example.cursosant

import com.example.cursosant.common.utils.FormatUtilsTest
import com.example.cursosant.weather.domain.DataSourceTest
import com.example.cursosant.weather.model.RemoteDatabaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(FormatUtilsTest::class, RemoteDatabaseTest::class, DataSourceTest::class)
class AllTests {}