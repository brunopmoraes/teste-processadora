package br.com.teste.processadora.teste;

import java.io.IOException;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.SocketFactory;

import org.apache.commons.io.IOUtils;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {

		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Socket socket = SocketFactory.getDefault().createSocket("localhost", 9999);
						Thread.sleep(5000);
						socket.getOutputStream().write("{\"action\": \"withdraw\",\"cardnumber\":\"1234567890982312\",\"amount\": \"500,00\"}\r\n".getBytes());
						StringWriter writer = new StringWriter();
						IOUtils.copy(socket.getInputStream(), writer, "UTF-8");
						socket.close();
						String theString = writer.toString();
						System.out.println(theString);
					} catch (UnknownHostException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();;
		}
	}
}
