package draw;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;

import com.baidu.ai.aip.utils.GsonUtils;
import com.bejson.pojo.Face_list;
import com.bejson.pojo.JsonRootBean;

import test.FaceDetect;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class table {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					table window = new table();
					window.frame.setVisible(true);
					window.frame.setTitle("智能化人脸识别");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		

		}
		
	

	/**
	 * Create the application.
	 */
	public table() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 312, 401);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton button_2 = new JButton("\u56FE\u7247\u5BFC\u51FA\u5730\u5740");
		button_2.setBackground(Color.LIGHT_GRAY);
		button_2.setForeground(Color.MAGENTA);
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url2 =null;
			    JFileChooser chooser2 = new JFileChooser();
			    JFrame parent2 = new JFrame();
			    int reVal = chooser2.showOpenDialog(parent2);
			    if(reVal==JFileChooser.APPROVE_OPTION) {
			    	url2 = chooser2.getSelectedFile().getAbsolutePath();
			    	String newurl2 = url2;
		JButton btnNewButton = new JButton("\u5BFC\u5165\u56FE\u7247...");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String url =null;
			    JFileChooser chooser = new JFileChooser();
			    JFrame parent = new JFrame();
			    int reVal = chooser.showOpenDialog(parent);
			    if(reVal==JFileChooser.APPROVE_OPTION) 
			    	url = chooser.getSelectedFile().getAbsolutePath();
				
			    	String resultjson = FaceDetect.faceDetect(url);
			    	//System.out.println(resultjson);
			    	JsonRootBean rootBean = GsonUtils.fromJson(resultjson, JsonRootBean.class);
			    	Face_list list = rootBean.result.face_list[0];
			    	
			    	
					
					
			    
					//System.out.println("年龄："+list.getAge());
					
			    	int y = (int)list.getLocation().getTop();
			    	int x = (int)list.getLocation().getLeft();
			    	int width = (int)list.getLocation().getWidth();
			    	int heigh = (int)list.getLocation().getHeight();
			    	try {
						InputStream in = new FileInputStream(url);
					} catch (FileNotFoundException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}//图片路径
					BufferedImage image = null;
					try {
						image = ImageIO.read(new File(url));
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Graphics g = image.getGraphics();
					//获取图片大小，确定写入数据的位置（暂时为右下角）
					int he = image.getHeight();
					int wi = image.getWidth();
					double down = he/7;
					double left = wi/7;
					System.out.println("图片的大小：宽："+wi+"高："+he);
					g.setColor(Color.green);
					g.drawRect(x, y, list.getLocation().getHeight(), list.getLocation().getWidth());
					g.setColor(Color.white);
					g.setFont(new Font("隶书", Font.BOLD,23));
					g.drawString("年龄："+list.getAge(),x,y);
					g.drawString("颜值："+list.getBeauty(),x,y+heigh);
					//其他的属性
					g.setColor(Color.green);
					g.setFont(new Font("隶书",Font.BOLD,20));
					g.drawString("情感："+list.getEmotion().getType(),wi/2+80,(int)(he-down-20));
					g.drawString("性别："+list.getGender().getType(),wi/2+80,(int)(he-down));
					g.drawString("表情："+list.getExpression().getType(),wi/2+80,(int)(he-down+20));
					g.drawString("脸型："+list.getFace_shape().getType(),wi/2+80,(int)(he-down+40));
					g.drawString("人种："+list.getRace().getType(), wi/2+80,(int)(he-down-40));
					
					
					
					
					
					
					
					
					java.sql.PreparedStatement ptmt = null;
				    FileOutputStream out = null;
					try {
						out = new FileOutputStream(newurl2);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    try {
						ImageIO.write(image,"jpeg",out);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
//				  创建窗口并显示标注好的人脸图片
					JFrame f =new JFrame("图片分析");
					f.setSize(image.getWidth(),image.getHeight());
					f.setLocation(0, 0);
					f.getContentPane().setLayout(null);
					JLabel label =new JLabel();
					ImageIcon i = new ImageIcon(newurl2);
					label.setIcon(i);
					label.setBounds(0, 0,i.getIconWidth(),i.getIconHeight());
					f.getContentPane().add(label);
					f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					f.setVisible(true);
				    System.out.println("成功！");
			    	
			    
				    btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						}
					});
					btnNewButton.setFont(new Font("宋体", Font.BOLD, 25));
					btnNewButton.setBounds(33, 26, 232, 37);
					frame.getContentPane().add(btnNewButton);
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
				
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.BOLD, 25));
		btnNewButton.setBounds(33, 26, 232, 37);
		frame.getContentPane().add(btnNewButton);
		

			    }
			}
		});
		
		
		JButton button = new JButton("\u8BBE\u7F6E\u7A97\u53E3\u80CC\u666F");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String url1 =null;
			    JFileChooser chooser1 = new JFileChooser();
			    JFrame parent1 = new JFrame();
			    int reVal = chooser1.showOpenDialog(parent1);
			    if(reVal==JFileChooser.APPROVE_OPTION) 
			    	url1 = chooser1.getSelectedFile().getAbsolutePath();
			    
			    ImageIcon img = new ImageIcon(url1);
			    JLabel imgLabel = new JLabel(img);
			    frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
			    imgLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
			    Container cp = frame.getContentPane();
				cp.setLayout(null);
				((JPanel) cp).setOpaque(false);
				
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setFont(new Font("宋体", Font.BOLD, 25));
		button.setBounds(33, 101, 232, 37);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u8BBE\u7F6E\u7A97\u53E3\u56FE\u6807");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				
				String url2 =null;
			    JFileChooser chooser2 = new JFileChooser();
			    JFrame parent2 = new JFrame();
			    int reVal = chooser2.showOpenDialog(parent2);
			    if(reVal==JFileChooser.APPROVE_OPTION) 
			    	url2 = chooser2.getSelectedFile().getAbsolutePath();
				ImageIcon imageIcon=new ImageIcon(url2);
				frame.setIconImage(imageIcon.getImage());
				
				
				
				
				
				
				
			}
		});
		button_1.setFont(new Font("宋体", Font.BOLD, 25));
		button_1.setBounds(33, 182, 232, 37);
		frame.getContentPane().add(button_1);
		
		
		
		
		
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setFont(new Font("宋体", Font.BOLD, 25));
		button_2.setBounds(33, 264, 232, 37);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel = new JLabel("\u6CE8\u610F\uFF1A\u5BFC\u5165\u5BFC\u51FA\u56FE\u7247\u53EA\u80FD\u4F7F\u7528*.jpg\u683C\u5F0F");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(10, 326, 289, 28);
		frame.getContentPane().add(lblNewLabel);
		
		

		
	}
}
