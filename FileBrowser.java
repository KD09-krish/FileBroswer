package files.guiapps;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class FileBrowser extends Frame implements ActionListener
{
	Panel1 p1;
	Panel2 p2;
	Panel3 p3;
	Panel4 p4;
	FileBrowser fb;

	public FileBrowser()
	{
		super("File Browser");
		fb = this;
		p1 = new Panel1();
		p2 = new Panel2();
		p3 = new Panel3();
		p4 = new Panel4();
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.EAST);
		add(p4,BorderLayout.SOUTH);
		p1.t.addActionListener(this);
		p1.b.addActionListener(this);
		p3.l.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				p1.t.setText(p1.t.getText() +"\\"+p3.l.getSelectedItem());
				//fb.actionPerformed(e);
				FileBrowser.this.actionPerformed(e);

			}
		});
		addWindowListener(new WindowAdapter()
		{		
			@Override
				public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		setSize(500,550);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		String path = p1.t.getText();
		File f = new File(path);
		p2.ta.setText("");
		p3.l.removeAll();
		if(f.isFile())
		{
			p4.t.setText("File");
			try
			{
				FileInputStream fis = new FileInputStream(path);
				DataInputStream dis = new DataInputStream(fis);
				String s = "";
				while((s = dis.readLine()) != null)
				{
					p2.ta.append(s + "\n");
				}
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		if(f.isDirectory())
		{
			p4.t.setText("Directory");
			String str[] = f.list();
			for(String s : str)
				p3.l.add(s);
		}
	}
	public static void main(String args[])
	{
		new FileBrowser();
	}
};
class Panel1 extends Panel
{
	TextField t;
	Button b;

	public Panel1()
	{
		add(t = new TextField(15));
		add(b = new Button("Click"));
	}
};
class Panel2 extends Panel
{
	TextArea ta;

	public Panel2()
	{
		add(ta = new TextArea(25,30));
	}
};
class Panel3 extends Panel
{
	List l;

	public Panel3()
	{
		add(l = new List(25));
	}
};
class Panel4 extends Panel
{
	TextField t;

	public Panel4()
	{
		add(t = new TextField(15));
	}
};
