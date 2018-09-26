package crossdev64.main;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import bibliothek.gui.dock.common.CControl;
import bibliothek.gui.dock.common.CGrid;
import bibliothek.gui.dock.common.DefaultSingleCDockable;
import bibliothek.gui.dock.common.SingleCDockable;
import bibliothek.gui.dock.common.menu.CLayoutChoiceMenuPiece;
import bibliothek.gui.dock.common.menu.SingleCDockableListMenuPiece;
import bibliothek.gui.dock.facile.menu.RootMenuPiece;
import crossdev64.settings.GlobalSettings;

public class MainFrame
	extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GlobalSettings mSettings = Application.getSettings();

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
		createMenubar(control);
		
		getContentPane().add(control.getContentArea());
		
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

	private void createFileMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu(mSettings.getResourceString("string.file"));
		JMenuItem item;

		item = new JMenuItem(mSettings.getResourceString("string.new"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.open_file")+"...");
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.open_project")+"...");
		root.add(item);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.close"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.close_project"));
		root.add(item);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.exit"));
		root.add(item);

		oMenuBar.add(root);
	}

	private void createEditMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu(mSettings.getResourceString("string.edit"));
		JMenuItem item;

		item = new JMenuItem(mSettings.getResourceString("string.undo"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.redo"));
		root.add(item);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.cut"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.copy"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.paste"));
		root.add(item);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.select_all"));
		root.add(item);

		oMenuBar.add(root);
	}

	private void createProjectMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu("Project");
		JMenuItem item;

		item = new JMenuItem("Add New Item...");
		root.add(item);

		item = new JMenuItem("Add Existing Item...");
		root.add(item);

		item = new JMenuItem("Project Settings...");
		root.add(item);

		oMenuBar.add(root);
	}

	private void createBuildMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu("Build");
		JMenuItem item;

		item = new JMenuItem("Build Project");
		root.add(item);

		item = new JMenuItem("Rebuild");
		root.add(item);

		item = new JMenuItem("Clean");
		root.add(item);

		oMenuBar.add(root);
	}

	private void createDebugMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu("Debug");
		JMenuItem item;
		JMenu menu;
		
		menu = new JMenu("Windows");

		item = new JMenuItem("Breakpoints");
		menu.add(item);
		item = new JMenuItem("Memory");
		menu.add(item);
		root.add(menu);
		root.addSeparator();
		
		item = new JMenuItem("Run");
		root.add(item);
		item = new JMenuItem("Start Debugging");
		root.add(item);
		item = new JMenuItem("Stop Debugging");
		root.add(item);

		root.addSeparator();

		item = new JMenuItem("Single Step");
		root.add(item);

		item = new JMenuItem("Continue");
		root.add(item);

		oMenuBar.add(root);
	}

	private void createToolsMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu("Tools");
		JMenu menu;
		JMenuItem item;

		item = new JMenuItem("Sprite Editor...");
		root.add(item);
		item = new JMenuItem("Character Editor...");
		root.add(item);

		menu = new JMenu("Layout");
		RootMenuPiece layout = new RootMenuPiece(menu);
		layout.add(new CLayoutChoiceMenuPiece(oControl, true));
		root.add(menu);

		root.addSeparator();

		item = new JMenuItem("Settings...");
		root.add(item);

		oMenuBar.add(root);
	}

	private void createMenubar(CControl oControl)
	{
		JMenuBar menuBar = new JMenuBar();

		// Create global menus
		createFileMenu(menuBar, oControl);
		createEditMenu(menuBar, oControl);
		createProjectMenu(menuBar, oControl);
		createBuildMenu(menuBar, oControl);
		createDebugMenu(menuBar, oControl);
		createToolsMenu(menuBar, oControl);

		RootMenuPiece menu = new RootMenuPiece("Windows", false);
		menu.add(new SingleCDockableListMenuPiece(oControl));
		menuBar.add(menu.getMenu());

		setJMenuBar(menuBar);
	}
}
