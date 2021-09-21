package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class SelectionSort extends Sorter {
	
	private int minIndex = 0;
	private int currentIndex = 1;
	private int sortedElements = 0;
	
	public SelectionSort(Animation panel) {
		super(panel);
	}

	@Override
	public void sort() {
		int[] nums = animation.numbers;
		int i = 0;
		int j = 0;
		long startTime = System.currentTimeMillis();
		while(i < nums.length - 1) {
			long currentTime = System.currentTimeMillis() - startTime;
			if(currentTime >= animation.compareTime) {
				if(nums[minIndex] > nums[j+1]) {
					minIndex = j + 1;
				}
				j++;
				if(j >= nums.length - 1) {
					swap(minIndex, i);
					i++;
					sortedElements = i;
					j = i;
					minIndex = j;
				}
				currentIndex = j + 1;
				startTime = System.currentTimeMillis();
			}
		}
		animation.sorter = new DummySort(animation, true);
	}

	@Override
	public void render(Graphics2D g2, int width) {
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i < animation.numbers.length; i++) {
			if(i == minIndex)
				drawRect(g2, Color.pink, i, barWidth);
			else if(i == currentIndex)
				drawRect(g2, Color.yellow, i, barWidth);
			else if(i < sortedElements)
				drawRect(g2, Color.blue, i, barWidth);
			else
				drawRect(g2, Color.red, i, barWidth);
		}
	}

}
