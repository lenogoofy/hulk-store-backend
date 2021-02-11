package com.todo1.hulkstore.excepcion;

public class NoGuardadoExcepcion extends Exception {

	private static final long serialVersionUID = -3044365949373380395L;

	public NoGuardadoExcepcion() {
		super();
	}

	public NoGuardadoExcepcion(String msg, Object... params) {
		super(String.format(msg, params));
	}

	public NoGuardadoExcepcion(Exception e, String msg, Object... params) {
		super(String.format(msg, params), e);
	}

	public NoGuardadoExcepcion(String msg, Exception e) {
		super(msg, e);
	}

	public NoGuardadoExcepcion(Exception e) {
		super(e.getMessage(), e);
	}

	public NoGuardadoExcepcion(String msg, String argument) {
		super(String.format(msg, argument));
	}
}
