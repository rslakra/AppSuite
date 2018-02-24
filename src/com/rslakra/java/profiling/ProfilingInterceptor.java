//package com.rslakra.java.profiling;
//
//public class ProfilingInterceptor extends Interceptor {
//	private String profilingKey = "profiling";
//	private boolean mode;
//
//	public String getProfilingKey() {
//		return this.profilingKey;
//	}
//
//	public void setProfilingKey(String profilingKey) {
//		this.profilingKey = profilingKey;
//	}
//
//	@Inject("framework.mode")
//	public void setDevMode(String mode) {
//		this.mode = "true".equals(mode);
//	}
//
//	public String intercept(ActionInvocation invocation) throws Exception {
//		if (this.mode) {
//			Object val = invocation.getInvocationContext().getParameters().get(this.profilingKey);
//			if (val != null) {
//				String sval = (val instanceof String) ? (String) val : ((String[]) (String[]) val)[0];
//				boolean enable = ("yes".equalsIgnoreCase(sval)) || ("true".equalsIgnoreCase(sval));
//				ProfilingUtils.setActive(enable);
//				invocation.getInvocationContext().getParameters().remove(this.profilingKey);
//			}
//		}
//		return invocation.invoke();
//	}
//}