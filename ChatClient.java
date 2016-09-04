/**
 * 
 */

/**
  * @author л��
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
		// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public void disconnected(){
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
	}
	class TFlistener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO �Զ����ɵķ������
			String str=tfTxt.getText().trim();
			taTxt.setText(str);
			tfTxt.setText("");
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
		}
		
	}

}
