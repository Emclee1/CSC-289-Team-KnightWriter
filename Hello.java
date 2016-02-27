package org.o7planning.tutorial.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;

public class Hello
{

	protected Shell shell;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			Hello window = new Hello();
			window.open();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
		shell = new Shell();
		shell.setSize(1200, 700);
		shell.setText("SWT Application");
		
		Button btnAdvancedSearch = new Button(shell, SWT.NONE);
		btnAdvancedSearch.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnAdvancedSearch.setBounds(30, 81, 114, 35);
		btnAdvancedSearch.setText("Advanced");
		
		Button btnSearch = new Button(shell, SWT.NONE);
		btnSearch.setBounds(30, 134, 114, 30);
		btnSearch.setText("Search");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(30, 37, 114, 26);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton.setBounds(30, 183, 114, 30);
		btnNewButton.setText("Random Recipe");
		
		Button btnMyFavorites = new Button(shell, SWT.NONE);
		btnMyFavorites.setBounds(30, 237, 114, 30);
		btnMyFavorites.setText("My Favorites");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setBounds(30, 290, 114, 30);
		btnNewButton_1.setText("New Ingredient");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setBounds(30, 368, 114, 30);
		btnNewButton_2.setText("Import");
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setBounds(30, 415, 114, 30);
		btnNewButton_3.setText("Export");
		
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setBounds(30, 486, 114, 30);
		btnNewButton_4.setText("Help");
		
		Button btnNewButton_5 = new Button(shell, SWT.NONE);
		btnNewButton_5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_5.setBounds(30, 533, 114, 30);
		btnNewButton_5.setText("About");
		
		Group grpSearchBy = new Group(shell, SWT.NONE);
		grpSearchBy.setText("Search by");
		grpSearchBy.setBounds(379, 164, 165, 158);
		
		Button btnRadioButton = new Button(grpSearchBy, SWT.RADIO);
		btnRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnRadioButton.setBounds(23, 35, 111, 20);
		btnRadioButton.setText("Recipe Name");
		
		Button btnRadioButton_1 = new Button(grpSearchBy, SWT.RADIO);
		btnRadioButton_1.setBounds(23, 62, 111, 20);
		btnRadioButton_1.setText("Ingredient");
		
		Button btnRadioButton_2 = new Button(grpSearchBy, SWT.RADIO);
		btnRadioButton_2.setBounds(23, 88, 111, 20);
		btnRadioButton_2.setText("Calories");
		
		Button btnRadioButton_3 = new Button(grpSearchBy, SWT.RADIO);
		btnRadioButton_3.setBounds(23, 114, 111, 20);
		btnRadioButton_3.setText("Difficulty");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(379, 353, 153, 28);
		
		Button btnNewButton_6 = new Button(shell, SWT.NONE);
		btnNewButton_6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		btnNewButton_6.setBounds(379, 415, 153, 30);
		btnNewButton_6.setText("Veiw Selected Recipe");
		
		Button btnNewButton_7 = new Button(shell, SWT.NONE);
		btnNewButton_7.setBounds(379, 486, 153, 30);
		btnNewButton_7.setText("Clear Search Results");
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(617, 164, 506, 423);
		
		Label label = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(270, 121, 2, 524);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(1158, 121, 2, 524);
		
		Label label_2 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(270, 641, 890, 2);
		
		Label label_3 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_3.setBounds(270, 121, 890, 2);

	}
}
