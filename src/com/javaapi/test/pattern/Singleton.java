package com.javaapi.test.pattern;

public class Singleton {
  private int a;

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  private Singleton() {
  }

  /*
   * // private static Singleton singleton = new Singleton(); // // public
   * static Singleton getSingleton() { // if (singleton == null) { // singleton
   * = new Singleton(); // } // return singleton; // }
   */
  private static final class Holder {
    private static final Singleton singleton = new Singleton();
  }

  public static Singleton getSingleton() {
    return Holder.singleton;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      new Thread(new Runnable() {
        @Override
        public void run() {
          System.out.println(Singleton.getSingleton());
        }
      });
    }
  }
}
