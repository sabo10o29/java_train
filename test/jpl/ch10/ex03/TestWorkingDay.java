package jpl.ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch10.ex03.WorkingDay.Week;

public class TestWorkingDay {

	@Test
	public void testGetWorkDayIf() {
		
		assertTrue(WorkingDay.getWorkDayIf(WorkingDay.Week.FRIDAY));
		assertFalse(WorkingDay.getWorkDayIf(WorkingDay.Week.SUNDAY));
		
	}

	@Test
	public void testGetWorkDaySwitch() {
		assertTrue(WorkingDay.getWorkDaySwitch(WorkingDay.Week.FRIDAY));
		assertFalse(WorkingDay.getWorkDaySwitch(WorkingDay.Week.SUNDAY));
	}

}
