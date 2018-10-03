package crossdev64.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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
import crossdev64.settings.GlobalSettingsDlg;
import crossdev64.utils.Stack;

public class MainFrame
	extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GlobalSettings mSettings = GlobalSettings.getInstance();

	/**
	 * Create the frame.
	 */
	public MainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter()
		{
	        public void windowClosing(WindowEvent e)
	        {
	        	onExit();
	        }

	    });		setBounds(100, 100, 450, 300);

	    initApplicationIcon();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		initDockable();
	}

	private void initApplicationIcon()
	{
		List<Image> images = new ArrayList<>();
	
//		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_016x016.png")).getImage());
//		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_032x032.png")).getImage());
//		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_064x064.png")).getImage());
//		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_128x128.png")).getImage());
		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_256x256.png")).getImage());
//		images.add(new ImageIcon(getClass().getResource("/crossdev64/resources/icons/app/commodore_512x512.png")).getImage());
		setIconImages(images);
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
		item.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				onExit();
			}
		});

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
		JMenu root = new JMenu(mSettings.getResourceString("string.project"));
		JMenuItem item;

		item = new JMenuItem(mSettings.getResourceString("string.add_new_item")+"...");
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.add_existing_item")+"...");
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.project_settings")+"...");
		root.add(item);

		oMenuBar.add(root);
	}

	private void createBuildMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu(mSettings.getResourceString("string.build"));
		JMenuItem item;

		item = new JMenuItem(mSettings.getResourceString("string.build_project"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.rebuild"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.clean"));
		root.add(item);

		oMenuBar.add(root);
	}

	private void createDebugMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu(mSettings.getResourceString("string.debug"));
		JMenuItem item;
		JMenu menu;
		
		menu = new JMenu(mSettings.getResourceString("string.windows"));

		item = new JMenuItem(mSettings.getResourceString("string.memory"));
		menu.add(item);
		item = new JMenuItem(mSettings.getResourceString("string.registers"));
		menu.add(item);
		item = new JMenuItem(mSettings.getResourceString("string.breakpoints"));
		menu.add(item);
		root.add(menu);

		item = new JMenuItem(mSettings.getResourceString("string.console"));
		root.add(item);
		root.addSeparator();
		
		item = new JMenuItem(mSettings.getResourceString("string.run"));
		root.add(item);
		item = new JMenuItem(mSettings.getResourceString("string.start_debugging"));
		root.add(item);
		item = new JMenuItem(mSettings.getResourceString("string.stop_debugging"));
		root.add(item);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.single_step"));
		root.add(item);

		item = new JMenuItem(mSettings.getResourceString("string.continue"));
		root.add(item);

		oMenuBar.add(root);
	}

	private void createToolsMenu(JMenuBar oMenuBar, CControl oControl)
	{
		JMenu root = new JMenu(mSettings.getResourceString("string.tools"));
		JMenu menu;
		JMenuItem item;

		item = new JMenuItem(mSettings.getResourceString("string.sprite_editor")+"...");
		root.add(item);
		item = new JMenuItem(mSettings.getResourceString("string.character_editor")+"...");
		root.add(item);

		menu = new JMenu("Layout");
		RootMenuPiece layout = new RootMenuPiece(menu);
		layout.add(new CLayoutChoiceMenuPiece(oControl, true));
		root.add(menu);

		root.addSeparator();

		item = new JMenuItem(mSettings.getResourceString("string.settings")+"...");
		item.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				onGlobalSettings();
			}
		});
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

		RootMenuPiece menu = new RootMenuPiece(mSettings.getResourceString("string.windows"), false);
		menu.add(new SingleCDockableListMenuPiece(oControl));
		menuBar.add(menu.getMenu());

		setJMenuBar(menuBar);
	}

	public void onExit()
	{
		Toolkit t = Toolkit.getDefaultToolkit();
        EventQueue eq = t.getSystemEventQueue();
        eq.postEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	/**
	 * Show the global preference dialog
	 */
	public void onGlobalSettings()
	{
		GlobalSettingsDlg dlg = new GlobalSettingsDlg(this);
		if(!dlg.showModal())
			return;

		System.out.println(Stack.getSourcePosition()+"Settings OK");
	}
}
