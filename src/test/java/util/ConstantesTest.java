package util;

import org.junit.Assert;
import org.junit.Test;

import util.Constantes;




public class ConstantesTest {


	@Test
	public void valoresConstatenes() {
		String appUrl = Constantes.getAppUrl();
		System.out.println(appUrl);
		String appAssetsUrl = Constantes.getAppAssetsUrl();
		System.out.println(appAssetsUrl);
		String appJsUr = Constantes.getAppJsUrl();
		System.out.println(appJsUr);
		String appImgUrl = Constantes.getAppImgUrl();
		System.out.println(appImgUrl);
		String appCssUrl = Constantes.getAppCssUrl();
		System.out.println(appCssUrl);
		int numberOfRowsPerPage = Constantes.getNumberOfRowsPerPage();
		System.out.println(numberOfRowsPerPage);
		String sessionMsg = Constantes.getSessionMsg();
		System.out.println(sessionMsg);
		String databaseConfDir = Constantes.getDatabaseConfDir();
		System.out.println(databaseConfDir);
		String preUrl = Constantes.getPreUrl();
		System.out.println(preUrl);
		String appGuaridaoUrl = Constantes.getAppGuardiaoUrl();
		System.out.println(appGuaridaoUrl);
		
		Assert.assertNotNull(appUrl);
		Assert.assertNotNull(appAssetsUrl);
		Assert.assertNotNull(appJsUr);
		Assert.assertNotNull(appImgUrl);
		Assert.assertNotNull(appCssUrl);
		Assert.assertNotNull(numberOfRowsPerPage);
		Assert.assertNotNull(sessionMsg);
		Assert.assertNotNull(databaseConfDir);
		Assert.assertNotNull(preUrl);
		Assert.assertNotNull(appGuaridaoUrl);
	}
	
	
}
