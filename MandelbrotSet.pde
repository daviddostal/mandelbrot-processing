Mandelbrot mandelbrot = new Mandelbrot();
GifsGenerator gif = new GifsGenerator(this, "mandelbrot");

void setup() {
  size(800, 800);
  background(230, 230, 255);
  gif.addGenerator(80,40,true);
  gif.addGenerator(750,25,true);
}

float iterations = 1;
int speed = 1;

void draw() {
  strokeWeight(speed);
  background(230, 230, 255);
  for (float i = 0; i < width; i+=speed) {
    for (float j = 0; j < width; j+=speed) {
      float m_x = map(i, 0, width, -2.2, 0.6);
      float m_y = map(j, 0, height, -1.4, 1.4);
      float escaped_in = mandelbrot.escapeTime(new Complex(m_x, m_y), iterations);
      if (escaped_in != -1) {
        float brightness = 255 - (escaped_in/(iterations + 1)) * 255;
        stroke(sqrt(brightness) /2 , brightness, sqrt(brightness) * 5);
      } else {
        stroke(230, 230, 255);
      }
      point(i, j);
    }
  }
  iterations++;
  gif.recordFrame();
}