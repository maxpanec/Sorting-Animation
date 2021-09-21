package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class MergeSort extends Sorter {

	private int compareIndex = -1;
	
	public MergeSort(Animation panel) {
		super(panel);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sort() {
		recursiveHelper(animation.numbers, 0, animation.numbers.length - 1);
		animation.sorter = new DummySort(animation, true);
	}
	
	private void recursiveHelper(int[] nums, int start, int end) {
		if(start<end) {
			int mid = (start+end)/2;
			recursiveHelper(nums,start,mid);
			recursiveHelper(nums,mid+1,end);
			merge(nums,start,mid,end);
		}
	}
	
	private void merge(int nums[], int start, int mid, int end) {
		int[] numsTemp = nums.clone();
		int i = start, j = mid + 1;
		int arrayIndex = start;
		compareIndex = arrayIndex;
		long startTime = System.currentTimeMillis();
		long currentTime;
		while(true) {
			currentTime = System.currentTimeMillis() - startTime;
			if(currentTime >= animation.compareTime) {
				if(numsTemp[i] <= numsTemp[j]) {
					nums[arrayIndex] = numsTemp[i];
					arrayIndex++;
					i++;
				}
				else {
					nums[arrayIndex] = numsTemp[j];
					arrayIndex++;
					j++;
				}
				startTime = System.currentTimeMillis();
				if(!(i <= mid && j <= end)) {
					break;
				}
				compareIndex=arrayIndex;
			}	
		}
		while(i <= mid) {
			compareIndex = arrayIndex;
			nums[arrayIndex] = numsTemp[i];
			arrayIndex++;
			i++;
		}
		while(j <= end) {
			compareIndex = arrayIndex;
			nums[arrayIndex] = numsTemp[j];
			arrayIndex++;
			j++;
		}
		compareIndex=-1;
	}

	@Override
	public void render(Graphics2D g2, int width) {
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i < animation.numbers.length; i++) {
			if(i == compareIndex)
				drawRect(g2, Color.green, i, barWidth);
			else
				drawRect(g2, Color.red, i, barWidth);
		}
	}

}
