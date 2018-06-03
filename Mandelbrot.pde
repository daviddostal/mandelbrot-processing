class Mandelbrot {
  public float escapeTime(Complex c, float iterations) {
    Complex z = new Complex(0, 0);
    for (float i = 0; i <= iterations; i++) {
      if (z.distance() > 2)
        return i;
      z = z.square().add(c);
    }
    return -1;
  }
}