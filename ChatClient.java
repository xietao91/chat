/**
 * 
 */

/**
  * @author 谢涛
 *
 */
import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ChatClient extends Frame {
	TextField tfTxt=new TextField();
	TextArea taTxt=new TextArea();
	Socket s=null;
	DataOutputStream dos=null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new ChatClient().launchFrame();
	}
	
	public void launchFrame(){
		setLocation(300,400);
		this.setSize(300,300);
		add(tfTxt,BorderLayout.SOUTH);
		add(taTxt,BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdater(){

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO 自动生成的方法存根
				disconnected();
				System.exit(0);
			}
	
		}
				);
		tfTxt.addActionListener(new TFlistener());
		setVisible(true);
		connect();
	}
	public void connect(){
		try {
			 s=new Socket("127.0.0.1",8888);
			 dos=new DataOutputStream(s.getOutputStream());
System.out.println("connected");
		} catch (UnknownHostException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void disconnected(){
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	class TFlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			String str=tfTxt.getText().trim();
			taTxt.setText(str);
			tfTxt.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}
		
	}

}
