package jpl.ch07.ex02;

public class TestPrimitive {
	
	public static void main(String[] args) {
		boolean	a = true;
		char	b = 0;
		byte	c = 127;
		short	d = 32767;
		int		e = 2147483647;
		long	f = 9223372036854775807L;
		float	g = 100f;
		double	h = 100d;
		
		//boolean
		
		//char
		b = (char)c;
		b = (char)d;
		b = (char)e;
		b = (char)f;
		b = (char)g;
		b = (char)h;

		//byte
		c = (byte)b;
		c = (byte)d;
		c = (byte)e;
		c = (byte)f;
		c = (byte)g;
		c = (byte)h;
		
		//short
		d = (short)b;
		d = c;
		d = (short)e;
		d = (short)f;
		d = (short)g;
		d = (short)h;
		
		//int
		e = b;
		e = c;
		e = d;
		e = (int)f;
		e = (int)g;
		e = (int)h;
		
		//long
		f = b;
		f = c;
		f = d;
		f = e;
		f = (long)g;
		f = (long)h;
		
		//float
		g = b;
		g = c;
		g = d;
		g = e;
		g = f;
		g = (float)h;
		
		//double
		h = b;
		h = c;
		h = d;
		h = e;
		h = f;
		h = g;
		
	}

}
