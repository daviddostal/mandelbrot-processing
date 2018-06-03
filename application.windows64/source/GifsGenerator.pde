import java.util.ArrayList;

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