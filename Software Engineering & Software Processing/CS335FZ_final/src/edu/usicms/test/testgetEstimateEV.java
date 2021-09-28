package edu.usicms.test;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.dapeng.usicms.model.UserStory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class testgetEstimateEV {
	UserStory us = new UserStory();

	@DisplayName("CT Test")
	@ParameterizedTest
	@MethodSource("parameterDataProvider")
	void test(int estimate, int E) {
		us.setEstimate(E);
		assertEquals(E, us.getEstimate());
	}

	private static Stream<Arguments> parameterDataProvider() {
		return Stream.of(Arguments.of(-1, -1), Arguments.of(5, 5), Arguments.of(55, 55)

		);
	}

}
