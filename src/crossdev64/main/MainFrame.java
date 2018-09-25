package crossdev64.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.menu.SingleCDockableListMenuPiece;
import bibliothek.gui.dock.facile.menu.RootMenuPiece;

public class MainFrame
	extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		initDockable();
	}

	private void initDockable()
	{
		CControl control = new CControl(this);
		
		add(control.getContentArea());
		
		CGrid grid = new CGrid(control);
		grid.add( 0, 0, 1, 2, createDockable("White", Color.WHITE));

		// Source view
		grid.add( 1, 0, 1, 1, createDockable("Green", Color.GREEN));
		grid.add( 1, 0, 1, 1, createDockable("Yellow", Color.YELLOW));
	
		// Watch view
		grid.add( 2, 0, 1, 2, createDockable("Blue", Color.BLUE));

		// Output
		grid.add( 1, 1, 1, 1, createDockable("Black", Color.BLACK));
		control.getContentArea().deploy(grid);
		
		RootMenuPiece menu = new RootMenuPiece("File", false);
		menu.add(new SingleCDockableListMenuPiece(control));

		//menu.add(new String("Test"));
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu.getMenu());

		menu = new RootMenuPiece("Edit", false);
		menu.add(new SingleCDockableListMenuPiece(control));

		//menu.add(new String("Test"));
		menuBar.add(menu.getMenu());

		setJMenuBar(menuBar);
	}

	protected SingleCDockable createDockable(String title, Color color)
	{
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		//panel.setBackground(color);
		DefaultSingleCDockable dockable = new DefaultSingleCDockable(title, title, panel);
		dockable.setCloseable(true);

		return dockable;
	}
}
