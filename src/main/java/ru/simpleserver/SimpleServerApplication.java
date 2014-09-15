package ru.simpleserver;

import org.apache.wicket.markup.html.SecurePackageResourceGuard;
import org.apache.wicket.markup.resolver.WicketContainerResolver;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.resource.DynamicJQueryResourceReference;
import org.apache.wicket.settings.IRequestCycleSettings;

import java.util.TimeZone;

public class SimpleServerApplication extends WebApplication
{    	
	@Override
	public Class<HomePage> getHomePage() {
		return HomePage.class;
	}

	@Override
	public void init() {
		super.init();

    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Novosibirsk"));

    getMarkupSettings().setStripWicketTags(true);
    getMarkupSettings().setStripComments(true);
    getMarkupSettings().setCompressWhitespace(true);
    getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
    getMarkupSettings().setDefaultAfterDisabledLink("");
    getMarkupSettings().setDefaultBeforeDisabledLink("");
    getPageSettings().addComponentResolver(new WicketContainerResolver());
    getJavaScriptLibrarySettings().setJQueryReference(new DynamicJQueryResourceReference());
    getRequestCycleSettings().setRenderStrategy(IRequestCycleSettings.RenderStrategy.ONE_PASS_RENDER);
    SecurePackageResourceGuard guard = (SecurePackageResourceGuard) getResourceSettings().getPackageResourceGuard();
    guard.addPattern("+*.map");
	}
}
