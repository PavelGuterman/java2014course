package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class GUI extends Thread {
	Display display;
	Shell shell;

	public GUI() {

	}

	private void initComponents() {
		display = new Display();
		shell = new Shell(display);

		shell.setText("Hello");

		shell.setSize(400, 400);
		shell.setLayout(new GridLayout(2, true));

		final Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("bt1");

		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("bt2");

		button1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));
		button2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1,
				1));

		final Text text1 = new Text(shell, SWT.BORDER);
		text1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		button1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				text1.setText(((Button) e.widget).getText());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		button2.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				text1.setText(((Button) e.widget).getText());

			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		final Canvas canvas = new Canvas(shell, SWT.BORDER);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		final Maze maze = new Maze();

		canvas.addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {

				int mazeRows = maze.getRows();
				int mazeColoms = maze.getColoms();

				Canvas c = (Canvas) e.widget;
				c.setBackground(new Color(null, 255, 255, 255));
				//e.gc.setBackground(new Color(null, 0, 0, 0));
				
				int Max_x=c.getSize().x;
				int Max_y=c.getSize().y;
				
				int rx=Math.round(Max_x/mazeRows);
				int ry=Math.round(Max_y/mazeColoms);
				
				
				for (int i = 0; i < mazeColoms; i++) {
					for (int j = 0; j < mazeRows; j++) {
						if (maze.getPoin(i, j) == maze.getWall()) {
							e.gc.setBackground(new Color(null, 0, 0, 0));
							e.gc.fillRectangle(j*rx, i*ry, rx, ry);	
							//e.gc.drawLine(j*rx, i*ry,(j+1)*rx, (i+1)*ry);
						}else{
							e.gc.setBackground(new Color(null, 224, 224, 224));
							e.gc.fillRectangle(j*rx, i*ry, rx, ry);
							//e.gc.drawLine(j*rx, i*ry,(j+1)*rx, (i+1)*ry);
						}
					}
				}
			}
		});

		shell.open();
	}

	public void run() {
		initComponents();

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch()) {
				display.sleep();

			}
		}
		display.dispose();

	}
}
