package edu.usicms.test;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

import org.dapeng.usicms.model.UserStory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class testgetStoryPointBV {
	UserStory us = new UserStory();

	@DisplayName("CT Test")
	@ParameterizedTest
	@MethodSource("parameterDataProvider")
	void test(int storyPoint, int sp) {
		us.setStoryPoint(sp);
		assertEquals(sp, us.getStoryPoint());
	}

	private static Stream<Arguments> parameterDataProvider() {
		return Stream.of(Arguments.of(-1, -1), Arguments.of(0, 0), Arguments.of(10, 10), Arguments.of(11, 11)

		);
	}

}
