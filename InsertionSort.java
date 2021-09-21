package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class InsertionSort extends Sorter {

	private int sortedElements = 0;
	private int keyIndex = 1;
	private boolean showKey = true;
	
	public InsertionSort(Animation panel) {
		super(panel);
	}

	@Override
	public void sort() {
		int[] nums = animation.numbers;
		int i = 1;
		int j = i - 1;
		int key = nums[i];
		boolean innerSort = true;
		long startTime = System.currentTimeMillis();
		long currentTime;
		while(i < nums.length) {
			currentTime = System.currentTimeMillis() - startTime;
			if(currentTime >= animation.compareTime / 2 && innerSort) {
				showKey = false;
				j = i - 1;
				key = nums[i];
				startTime = System.currentTimeMillis();
				while(true) {
					currentTime = System.currentTimeMillis() - startTime;
					if(currentTime >= animation.compareTime) {
						if(j >= 0 && nums[j] > key) {
							nums[j+1] = nums[j];
							j--;
						}
						else
							break;
						startTime = System.currentTimeMillis();
					}
				}
				showKey = true;
				nums[j+1] = key;
				keyIndex = j + 1;
				innerSort = false;
				startTime = System.currentTimeMillis();
			}
			else if(currentTime >= animation.compareTime / 2 && !innerSort) {
				innerSort = true;
				i++;
				keyIndex = i;
				sortedElements = i;
				startTime = System.currentTimeMillis();
			}
		}
		animation.sorter = new DummySort(animation, true);
	}

	@Override
	public void render(Graphics2D g2, int width) {
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i < animation.numbers.length; i++) {
			if(i <= sortedElements && i != keyIndex)
				drawRect(g2, Color.blue, i, barWidth);
			else if(i > sortedElements && i != keyIndex)
				drawRect(g2, Color.red, i, barWidth);
			else if(i == keyIndex && showKey)
				drawRect(g2, Color.pink, i, barWidth);
			else
				drawRect(g2, Color.red, i, barWidth);
		}
	}

}
