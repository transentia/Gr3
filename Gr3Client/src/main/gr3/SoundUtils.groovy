package gr3

import org.jfugue.Pattern
import org.jfugue.Player

class SoundUtils {

  private static final player = new Player()
  private static final smallPattern = new Pattern("T[Presto] I[Rock_Organ] Db4minH C5majH C5majW")
  private static final bigPattern = new Pattern("T[Presto] I[Rock_Organ] Db4minH C5majH C5majW C5majH Db4minH")

  static smallWin = {-> player.play(smallPattern) }
  static bigWin = {-> player.play(bigPattern) }
}

