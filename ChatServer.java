import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;

public class ChatServer {

	public static void main(String[] args) {
		boolean started = false;
		Socket s = null;
		ServerSocket ss = null;
		DataInputStream dis = null;

		// TODO �Զ����ɵķ������
		try {
			ss = new ServerSocket(8888);
		}catch(BindException e){
			System.out.println("�ö˿��ѱ�ռ��");
			System.out.println("ϵͳ���ڰ����رյ�ǰ���г������Ժ�������");
			System.exit(0);
		}catch (IOException e) {
			e.printStackTrace();
		}

		try {
			started = true;
			boolean bconnectted = false;

			while (started) {
				s = ss.accept();
				bconnectted = true;
				System.out.println("a client connected!");
				dis = new DataInputStream(s.getInputStream());
				while (bconnectted) {
					String str = dis.readUTF();
					System.out.println(str);
				}
				// dis.close();
			}
		}catch(EOFException e){
			System.out.println("a client closed!");
		} catch (IOException e1)
		 {
			e1.printStackTrace();
		} finally {
			try {
				if (dis != null)
					dis.close();
				if (s != null)
					s.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}

	}

}
