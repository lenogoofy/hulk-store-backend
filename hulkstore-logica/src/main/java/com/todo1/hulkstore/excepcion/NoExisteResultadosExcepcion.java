package com.todo1.hulkstore.excepcion;

public class NoExisteResultadosExcepcion extends Exception {
	
	private static final long serialVersionUID = -7289474904455295007L;

	public NoExisteResultadosExcepcion() {
		super();
	}

	public NoExisteResultadosExcepcion(String msg, Object... params) {
		super(String.format(msg, params));
	}

	public NoExisteResultadosExcepcion(Exception e, String msg, Object... params) {
		super(String.format(msg, params), e);
	}

	public NoExisteResultadosExcepcion(String msg, Exception e) {
		super(msg, e);
	}

	public NoExisteResultadosExcepcion(Exception e) {
		super(e.getMessage(), e);
	}

	public NoExisteResultadosExcepcion(String msg, String argument) {
		super(String.format(msg, argument));
	}

}
