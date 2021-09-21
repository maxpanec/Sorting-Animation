package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class BubbleSort extends Sorter {
	
	private int indexOne = 0;
	private int indexTwo = 1;
	private int sortedElements = 0;

	public BubbleSort(Animation panel) {
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
			if(currentTime >= animation.compareTime / 2) {
				if(nums[j] > nums[j+1]) {
					swap(j, j+1);
				}
			}
			if(currentTime >= animation.compareTime) {
				j++;
				if(j >= nums.length - (i + 1)) {
					j = 0;
					i++;
					sortedElements = i;
				}
				startTime = System.currentTimeMillis();
				indexOne = j;
				indexTwo = j + 1;
			}
		}
		animation.sorter = new DummySort(animation, true);
	}

	@Override
	public void render(Graphics2D g2, int width) {
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i < animation.numbers.length; i++) {
			if(i == indexOne)
				drawRect(g2, Color.orange, i, barWidth);
			else if(i == indexTwo)
				drawRect(g2, Color.green, i, barWidth);
			else if(i >= animation.numbers.length - sortedElements)
				drawRect(g2, Color.blue, i, barWidth);
			else
				drawRect(g2, Color.red, i, barWidth);
		}
	}

}
