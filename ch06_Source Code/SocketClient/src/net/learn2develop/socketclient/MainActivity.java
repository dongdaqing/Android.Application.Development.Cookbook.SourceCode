package net.learn2develop.socketclient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	static final String SERVER_IP = "192.168.1.5";
	static final int SERVER_PORT = 5000;

	Handler handler = new Handler();
	static Socket socket;

	PrintWriter printWriter;

	
	TextView textView1;
	EditText editText1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView1 = (TextView) findViewById(R.id.textView1);
		editText1 = (EditText) findViewById(R.id.editText1);
		textView1.setMovementMethod(new ScrollingMovementMethod());
	}

	@Override
	protected void onStart() {
		super.onStart();
		Thread clientThread = new Thread(new ClientThread());
		clientThread.start();
	}

	@Override
	protected void onStop() {
		super.onStop();
		try {
			socket.shutdownInput();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onClick(View view) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				printWriter.println(editText1.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public class ClientThread implements Runnable {
		public void run() {
			try {
				InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

				handler.post(new Runnable() {
					@Override
					public void run() {
						textView1.setText(textView1.getText()
								+ "Connecting to the server");
					}
				});

				socket = new Socket(serverAddr, SERVER_PORT);				
				try {
					printWriter = new PrintWriter(new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream())),
							true);

					//---get an InputStream object to read from the server---
					BufferedReader br = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));

					try {
						//---read all incoming data terminated with a \n
						// char---
						String line = null;
						while ((line = br.readLine()) != null) {
							final String strReceived = line;

							handler.post(new Runnable() {
								@Override
								public void run() {
									textView1.setText(textView1.getText()
											+ "\n" + strReceived);
								}
							});
						}

						//---disconnected from the server---
						handler.post(new Runnable() {
							@Override
							public void run() {
								textView1.setText(textView1.getText()
										+ "\n" + "Client disconnected");
							}
						});

					} catch (Exception e) {
						final String error = e.getLocalizedMessage();
						handler.post(new Runnable() {
							@Override
							public void run() {
								textView1.setText(textView1.getText() + "\n" + error);
							}
						});
					}

				} catch (Exception e) {
					final String error = e.getLocalizedMessage();
					handler.post(new Runnable() {
						@Override
						public void run() {
							textView1.setText(textView1.getText() + "\n" + error);
						}
					});
				}

				handler.post(new Runnable() {
					@Override
					public void run() {
						textView1.setText(textView1.getText()
								+ "\n" + "Connection closed.");
					}
				});

			} catch (Exception e) {
				final String error = e.getLocalizedMessage();
				handler.post(new Runnable() {
					@Override
					public void run() {
						textView1.setText(textView1.getText() + "\n" + error);
					}
				});
			}
		}
	}
}
