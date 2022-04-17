package com.qualizeal.community.web.api;

import com.qualizeal.community.config.TestConfig;

import java.util.function.Function;

public interface ElementCondition<T> extends Function<TestConfig, T> {

}
