package com.camping.common;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.primefaces.context.RequestContext;

/**
 * @author Xiasong Chen A00291322
 * 
 * @date 16 Aug 2022 09:06:29
 * @version 1.0
 */
public abstract class ContextMocker extends RequestContext {
	private ContextMocker() {
	}

	private static final Release RELEASE = new Release();

	private static class Release implements Answer<Void> {
		@Override
		public Void answer(InvocationOnMock invocation) throws Throwable {
			setCurrentInstance(null);
			return null;
		}
	}

	public static RequestContext mockRequestContext() {
		RequestContext context = Mockito.mock(RequestContext.class);
		setCurrentInstance(context);
		Mockito.doAnswer(RELEASE).when(context).release();
		return context;
	}
}
