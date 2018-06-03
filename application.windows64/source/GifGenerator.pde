import gifAnimation.*;

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