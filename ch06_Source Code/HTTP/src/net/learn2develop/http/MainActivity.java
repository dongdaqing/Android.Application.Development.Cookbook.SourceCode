package net.learn2develop.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private String DownloadText(String URL) {
		int BUFFER_SIZE = 2000;
		InputStream in = null;
		try {			
			//in = OpenHttpGETConnection(URL);
			in = OpenHttpPOSTConnection(URL);
		} catch (Exception e) {
			Log.d("Networking", e.getLocalizedMessage());
			return "";
		}

		InputStreamReader isr = new InputStreamReader(in);
		int charRead;
		String str = "";
		char[] inputBuffer = new char[BUFFER_SIZE];
		try {
			while ((charRead = isr.read(inputBuffer)) > 0) {
				// ---convert the chars to a String---
				String readString = String
						.copyValueOf(inputBuffer, 0, charRead);
				str += readString;
				inputBuffer = new char[BUFFER_SIZE];
			}
			in.close();
		} catch (IOException e) {
			Log.d("Networking", e.getLocalizedMessage());
			return "";
		}
		return str;		
	}

	private class DownloadTextTask extends AsyncTask<String, Void, String> {
		protected String doInBackground(String... urls) {
			return DownloadText(urls[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG).show();
			Log.d("DownloadTextTask", result);
		}
	}

	// ---Connects using HTTP GET---
	public static InputStream OpenHttpGETConnection(String url) {
		InputStream inputStream = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			inputStream = httpResponse.getEntity().getContent();
		} catch (Exception e) {
			Log.d("", e.getLocalizedMessage());
		}
		return inputStream;
	}

	//---Connects using HTTP POST---
	public InputStream OpenHttpPOSTConnection(String url) {
		InputStream inputStream = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			//---set the headers---
			httpPost.addHeader("Host", "www.webservicex.net");
			httpPost.addHeader("Content-Type",
					"application/x-www-form-urlencoded");

			//---the key/value pairs to post to the server---
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
			nameValuePairs.add(new BasicNameValuePair("FromCurrency", "EUR"));
			nameValuePairs.add(new BasicNameValuePair("ToCurrency", "USD"));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			HttpResponse httpResponse = httpclient.execute(httpPost);
			inputStream = httpResponse.getEntity().getContent();
		} catch (Exception e) {
			Log.d("OpenHttpPOSTConnection", e.getLocalizedMessage());
		}
		return inputStream;
	}
	
	
	
	
    private Bitmap DownloadImage(String URL)
    {        
        Bitmap bitmap = null;
        InputStream in = null;        
        try {
            in = OpenHttpGETConnection(URL);
            bitmap = BitmapFactory.decodeStream(in);
            in.close();
        } catch (Exception e) {
            Log.d("DownloadImage", e.getLocalizedMessage());            
        }
        return bitmap;                
    }
        
    private class DownloadImageTask extends AsyncTask
    <String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            return DownloadImage(urls[0]);
        }
        
        protected void onPostExecute(Bitmap result) {
            ImageView img = (ImageView) findViewById(R.id.img);
            img.setImageBitmap(result);
        }
    }

	
	
	
    
    private String WordDefinition(String word) {
        InputStream in = null;
        String strDefinition = "";
        try {
            in = OpenHttpGETConnection( 
                "http://services.aonaware.com/DictService/" + 
                "DictService.asmx/Define?word=" + word);
            Document doc = null;
            DocumentBuilderFactory dbf = 
                DocumentBuilderFactory.newInstance();
            DocumentBuilder db;            
            try {
                db = dbf.newDocumentBuilder();
                doc = db.parse(in);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }            
            doc.getDocumentElement().normalize(); 
            
            //---retrieve all the <Definition> elements---
            NodeList definitionElements = 
                doc.getElementsByTagName("Definition"); 
            
            //---iterate through each <Definition> elements---
            for (int i = 0; i < definitionElements.getLength(); i++) { 
                Node itemNode = definitionElements.item(i); 
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) 
                {            
                    //---convert the Definition node into an Element---
                    Element definitionElement = (Element) itemNode;

                    //---get all the <WordDefinition> elements under 
                    // the <Definition> element---
                    NodeList wordDefinitionElements = 
                            definitionElement.
                            getElementsByTagName("WordDefinition");

                    strDefinition = "";
                    //---iterate through each <WordDefinition> 
                    // elements---
                    for (int j = 0; j < 
                    wordDefinitionElements.getLength(); j++) {
                        //---get all the child nodes under the 
                        // <WordDefinition> element---
                        NodeList textNodes = 
                                (wordDefinitionElements.item(j)).
                                getChildNodes();
                        strDefinition += 
                                ((Node) 
                                textNodes.item(0)).getNodeValue() + 
                                ". \n";                            
                    }
                } 
            }
        } catch (Exception e) {
            Log.d("NetworkingActivity", e.getLocalizedMessage());
        }   
        //---return the definitions of the word---
        return strDefinition;
    }

    private class AccessWebServiceTask extends AsyncTask
    <String, Void, String> {
        protected String doInBackground(String... urls) {
            return WordDefinition(urls[0]);
        }
        
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), result, 
            Toast.LENGTH_LONG).show();
        }
    }

    
    

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*
		//---using HTTP GET---
		new DownloadTextTask()
		.execute("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate?FromCurrency=EUR&ToCurrency=USD");
        */
		
		
		//---using HTTP POST---
		new DownloadTextTask()
		.execute("http://www.webservicex.net/CurrencyConvertor.asmx/ConversionRate");
		
		
		/*
        //---download image---
        new DownloadImageTask().execute(
                "http://www.mayoff.com/5-01cablecarDCP01934.jpg");
		*/
		
		/*
        //---access a Web Service using GET---
        new AccessWebServiceTask().execute("cool");
        */
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
