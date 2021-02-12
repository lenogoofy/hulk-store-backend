package com.todo1.hulkstore.excepcion;

public class ExisteResultadosExcepcion extends Exception {
	
	private static final long serialVersionUID = -7289474904455295007L;

	public ExisteResultadosExcepcion() {
		super();
	}

	public ExisteResultadosExcepcion(String msg, Object... params) {
		super(String.format(msg, params));
	}

	public ExisteResultadosExcepcion(Exception e, String msg, Object... params) {
		super(String.format(msg, params), e);
	}

	public ExisteResultadosExcepcion(String msg, Exception e) {
		super(msg, e);
	}

	public ExisteResultadosExcepcion(Exception e) {
		super(e.getMessage(), e);
	}

	public ExisteResultadosExcepcion(String msg, String argument) {
		super(String.format(msg, argument));
	}

}
