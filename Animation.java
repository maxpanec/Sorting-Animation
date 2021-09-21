package sorting;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Animation extends Canvas implements Runnable{
	
	private int[] presortNumbers;
	private boolean running = false;
	int[] numbers;
	Sorter sorter;
	Integer compareTime;
		
	public Animation() {
		sorter = new DummySort(this, false);
	}
	
	public void render(int width) {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, getWidth(), getHeight());
		sorter.render(g2, width);
		g2.dispose();
		bs.show();
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}
	public void run(){
		while(running){
			render(getWidth());
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

	public void generateNumbers(int size) {
		Random rnd = new Random();
		sorter = new DummySort(this, false);
		numbers = new int[size];
		presortNumbers = new int[size];
		for(int i = 0; i < size; i++) {
			int num = rnd.nextInt(getHeight()-1);
			numbers[i] = num;
			presortNumbers[i] = num;
		}
	}
	
	public void originalNumbers() {
		for(int i = 0; i < presortNumbers.length; i++) {
			numbers[i] = presortNumbers[i];
		}
		sorter = new DummySort(this, false);
	}
	
}