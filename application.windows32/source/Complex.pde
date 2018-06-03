class Complex {
  float real;
  float imaginary;

  public Complex(float real, float imaginary) {
    this.real = real;
    this.imaginary = imaginary;
  }

  public float getReal() {
    return real;
  }

  public float getImaginary() {
    return imaginary;
  }

  public Complex add(Complex other) {
    return new Complex(real + other.getReal(), imaginary + other.getImaginary());
  }

  public Complex square() {
    // (a+bi)(c+di)=(ac-bd)+(bc+ad)i.\ 
    return new Complex(real*real - imaginary*imaginary, 2*real*imaginary);
  }

  public float distance() {
    //return abs(real + imaginary);
    return abs((real == 0 ? 0.1 : real) * (imaginary == 0 ? 0.1 : imaginary));
    //return abs(real) + abs(imaginary);
    //return sqrt(real*real + imaginary*imaginary);
  }
}