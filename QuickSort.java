package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class QuickSort extends Sorter {

	private boolean[] sortedElements;
	private int currentPivot = -1;
	private int currentCheck = -1;
	private int currentSwapWith = -1;
	
	public QuickSort(Animation panel) {
		super(panel);
		sortedElements = new boolean[panel.numbers.length];
	}

	@Override
	public void sort() {
		recursiveHelper(animation.numbers, 0, animation.numbers.length - 1);
		animation.sorter = new DummySort(animation, true);
	}
	
	private void recursiveHelper(int[] nums, int low, int high) {
		if(low < high) {
			int pivot = pivotIndex(nums, low, high);
			recursiveHelper(nums, low, pivot - 1);
			recursiveHelper(nums, pivot + 1, high);
		}
		else {
			int index = high == -1 ? low : high;
			sortedElements[index] = true;
		}
	}
	
	private int pivotIndex(int[] nums, int low, int high) {
		int pivot = nums[high];
		currentPivot = high;
		int i = low -1;
		long startTime = System.currentTimeMillis();
		int j = low;
		currentCheck = j;
		currentSwapWith = i;
		//3 phases of animation
		//thirdSort tracks which phase is happening
		int thirdSort = 1;
		long currentTime;
		while(j <= high - 1) {
			currentTime = System.currentTimeMillis() - startTime;
			if(currentTime >= animation.compareTime / 3 && thirdSort == 1) {
				if(nums[j] < pivot) {
					i++;
					currentSwapWith=i;
				}
				thirdSort = 2;
			}
			if(currentTime >= 2 * animation.compareTime / 3 && thirdSort == 2) {
				if(nums[j] < pivot) {
					swap(i, j);
				}
				thirdSort = 3;
			}
			if(currentTime >= animation.compareTime && thirdSort == 3) {
				j++;
				currentSwapWith = -1;
				currentCheck = j;
				thirdSort = 1;
				startTime = System.currentTimeMillis();
			}
		}
		swap(i + 1, high);
		sortedElements[i+1] = true;
		return i + 1;
	}

	@Override
	public void render(Graphics2D g2, int width) {
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i<animation.numbers.length; i++) {
			if(sortedElements[i])
				drawRect(g2, Color.blue, i, barWidth);
			else if(i==currentPivot)
				drawRect(g2, Color.gray, i, barWidth);
			else if(i==currentCheck)
				drawRect(g2, Color.yellow, i, barWidth);
			else if(i==currentSwapWith)
				drawRect(g2, Color.green, i, barWidth);
			else
				drawRect(g2, Color.red, i, barWidth);
		}
	}
}
