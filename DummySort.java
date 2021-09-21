package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public class DummySort extends Sorter {
	
	boolean sorted;
	
	public DummySort(Animation panel, boolean sorted) {
		super(panel);
		this.sorted = sorted;
	}

	@Override
	public void sort() {

	}

	@Override
	public void render(Graphics2D g2, int width) {
		if(animation.numbers == null)
			return;
		Color color = sorted ? Color.blue : Color.red;
		int barWidth = width / animation.numbers.length;
		for(int i = 0; i < animation.numbers.length; i++) {
			drawRect(g2, color, i, barWidth);
		}
	}

}
