package sorting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ButtonPanel extends JPanel{
	
	private static final int MAX_NUMBER_OF_BARS = 320;
	private static final int MAX_COMPARE_TIME = 2000;
	
	private Animation animation;
	private JTextField time;
	private JTextField bars;
	
	public ButtonPanel(Animation animation) {
		this.animation = animation;
		addComponents();
	}
	
	private void addComponents() {
		JButton bubble = new JButton("Bubble Sort");
		bubble.setFocusable(false);
		JButton selection = new JButton("Selection Sort");
		selection.setFocusable(false);
		JButton insertion = new JButton("Insertion Sort");
		insertion.setFocusable(false);
		JButton quick = new JButton("Quick Sort");
		quick.setFocusable(false);
		JButton merge = new JButton("Merge Sort");
		merge.setFocusable(false);
		JButton previous = new JButton("Previous Numbers");
		previous.setFocusable(false);
		time = new JTextField(11);
		bars = new JTextField(11);
		
		bubble.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.sorter = new BubbleSort(animation);
				animation.sorter.sort();
			}
		});
		add(bubble);
		
		selection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.sorter = new SelectionSort(animation);
				animation.sorter.sort();
			}
		});
		add(selection);
		
		insertion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.sorter = new InsertionSort(animation);
				animation.sorter.sort();
			}
		});
		add(insertion);
		
		quick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.sorter = new QuickSort(animation);
				animation.sorter.sort();
			}
		});
		add(quick);
		
		merge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.sorter = new MergeSort(animation);
				animation.sorter.sort();
			}
		});
		add(merge);
		
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!checkSetUp())
					return;
				animation.originalNumbers();
			}
		});
		add(previous);
		
		time.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int entry;
				try {
					entry = Integer.parseInt(time.getText());
					if(entry < 0) {
						time.setText("Num >= 0");
						return;
					}
					else if (entry > MAX_COMPARE_TIME) {
						time.setText("Num <= " + MAX_COMPARE_TIME);
						return;
					}
					animation.compareTime = entry;
					time.setText("");
				}
				catch(NumberFormatException e) {
					time.setText("Invalid");
				}
			}
		});
		add(new JLabel("Enter Compare Time:"));
		add(time);
		
		bars.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int entry;
				try {
					entry = Integer.parseInt(bars.getText());
					if(entry < 0) {
						bars.setText("Num >= 0");
						return;
					}
					else if (entry > MAX_NUMBER_OF_BARS) {
						bars.setText("Num <= " + MAX_NUMBER_OF_BARS);
						return;
					}
					bars.setText("");
					animation.generateNumbers(entry);
				}
				catch(NumberFormatException e) {
					bars.setText("Invalid");
				}
			}
		});
		add(new JLabel("Enter Number of Elements:"));
		add(bars);
		
	}
	
	private boolean checkSetUp() {
		boolean setUpComplete = true;
		if(animation.compareTime == null) {
			time.setText("Input Value");
			setUpComplete = false;
		}
		if(animation.numbers == null) {
			bars.setText("Input Value");
			setUpComplete = false;
		}
		return setUpComplete;
	}
}
