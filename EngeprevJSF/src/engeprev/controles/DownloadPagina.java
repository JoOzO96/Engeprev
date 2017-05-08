package engeprev.controles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DownloadPagina {

	
	public String testaDownloadPagina(String site){
		try {
			System.out.println(site);
			URL url = new URL(site);
			BufferedReader in =
			        new BufferedReader(new InputStreamReader(url.openStream()));

			//BufferedWriter out = new BufferedWriter(new FileWriter(file));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
			
			    // Imprime p&aacute;gina no console
			    System.out.println(inputLine);
				
			    // Grava pagina no arquivo
			   // out.write(inputLine);
			  //  out.newLine();
			}

			in.close();
			//out.flush();
			//out.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	
}
}
