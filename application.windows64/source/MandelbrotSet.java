import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import gifAnimation.*; 
import java.util.ArrayList; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class MandelbrotSet extends PApplet {

Mandelbrot mandelbrot = new Mandelbrot();
GifsGenerator gif = new GifsGenerator(this, "mandelbrot");

public void setup() {
  
  background(230, 230, 255);
  //gif.addGenerator(80,40,true);
  //gif.addGenerator(750,25,true);
}

float iterations = 1;
int speed = 1;

public void draw() {
  strokeWeight(speed);
  background(230, 230, 255);
  for (float i = 0; i < width; i+=speed) {
    for (float j = 0; j < width; j+=speed) {
      float m_x = map(i, 0, width, -2.2f, 0.6f);
      float m_y = map(j, 0, height, -1.4f, 1.4f);
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
    return abs((real == 0 ? 0.1f : real) * (imaginary == 0 ? 0.1f : imaginary));
    //return abs(real) + abs(imaginary);
    //return sqrt(real*real + imaginary*imaginary);
  }
}


class GifGenerator {
  GifMaker gifMaker;
  int recordedFrames;
  int animationFrames;
  String filename; 
  int delay;
  boolean completed = false;

  public GifGenerator(PApplet sketch, String filename, int delay, int frames, boolean repeat) {
    this.animationFrames = frames;
    this.recordedFrames = 0;
    this.filename = filename;
    this.delay = delay;
    this.gifMaker = new GifMaker(sketch, filename, 10);
    this.gifMaker.setRepeat(repeat == true ? 0 : 1);
  }

  public void recordFrame() {
    if (!completed) {
      if (recordedFrames < animationFrames) {
        gifMaker.setDelay(delay);
        gifMaker.addFrame();
        recordedFrames++;
        println(filename + ": frame " + recordedFrames + " of " + animationFrames);
      } else {
        gifMaker.finish();
        println(filename + " saved");
        completed = true;
      }
    }
  }
  
  public boolean isCompleted() {
    return this.completed;
  }
}



class GifsGenerator {
  PApplet sketch;
  String filename;
  ArrayList<GifGenerator> generators = new ArrayList<GifGenerator>();

  public GifsGenerator(PApplet sketch, String filename) {
    this.sketch = sketch;
    this.filename = filename;
  }

  public void addGenerator(int delay, int frames, boolean repeat) {
    generators.add(new GifGenerator(sketch, filename + "-" + delay + "ms" + ".gif", delay, frames, repeat));
  }

  public void recordFrame() {
    for (GifGenerator gen : generators) {
      if (!gen.isCompleted())
        gen.recordFrame();
    }
  }
}

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

  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "MandelbrotSet" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
