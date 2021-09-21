package sorting;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Sorter {
	
	Animation animation;
	
	public Sorter(Animation animation) {
		this.animation = animation;
	}
	
	public abstract void sort();
	
	public abstract void render(Graphics2D g2, int width);
	
	public void drawRect(Graphics2D g2, Color color, int position, int width) {
		g2.setColor(color);
		g2.fillRect(width*position, 0, width, animation.numbers[position]);
		g2.setColor(Color.black);
		g2.drawRect(width*position, 0, width, animation.numbers[position]);
	}
	
	public void swap(int first, int second) {
		int temp = animation.numbers[first];
		animation.numbers[first] = animation.numbers[second];
		animation.numbers[second] = temp;
	}
}
