package cw2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MergeSortTest {

	@Test
	void test() {
		MergeSort ms = new MergeSort();
		Object arr[][] = {
				{4, "d"},
				{1, "a"},
				{2, "b"},
		};
		int num = arr.length;
		ms.sort(arr, 0, num-1, 1, "Ascending");
		Object actualOutput[][] = {
				{1, "a"},
				{2, "b"},
				{4, "d"}
		};
		
		assertEquals(arr[0][0], actualOutput[0][0]);
	}

}
