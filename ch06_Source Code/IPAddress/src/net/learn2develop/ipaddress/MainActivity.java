package net.learn2develop.ipaddress;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClickIPv4(View view) {
		Toast.makeText(this, getLocalIpv4Address(), Toast.LENGTH_LONG).show();
	}

	public void onClickIPv6(View view) {
		Toast.makeText(this, getLocalIpv6Address(), Toast.LENGTH_LONG).show();
	}
	
	public String getLocalIpv4Address() {
		try {
			for (Enumeration<NetworkInterface> networkInterfaceEnum = NetworkInterface
					.getNetworkInterfaces(); networkInterfaceEnum.hasMoreElements();) {
				NetworkInterface networkInterface = networkInterfaceEnum.nextElement();
				for (Enumeration<InetAddress> ipAddressEnum = networkInterface
						.getInetAddresses(); ipAddressEnum.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) ipAddressEnum
							.nextElement();
					//---check that it is not a loopback address and
					// it is IPv4---
					if (!inetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {
						return inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("getLocalIpv4Address", ex.toString());
		}
		return null;
	}

	public String getLocalIpv6Address() {
		try {
			for (Enumeration<NetworkInterface> networkInterfaceEnum = NetworkInterface
					.getNetworkInterfaces(); networkInterfaceEnum.hasMoreElements();) {
				NetworkInterface networkInterface = networkInterfaceEnum.nextElement();
				for (Enumeration<InetAddress> ipAddressEnum = networkInterface
						.getInetAddresses(); ipAddressEnum.hasMoreElements();) {
					InetAddress inetAddress = (InetAddress) ipAddressEnum
							.nextElement();
					//---check that it is not a loopback address and
					// it is not IPv4---
					if (!inetAddress.isLoopbackAddress()
							&& !InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("getLocalIpv6Address", ex.toString());
		}
		return null;
	}

}
