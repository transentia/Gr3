package gr3

import java.awt.event.ActionListener
import java.text.NumberFormat
import gr3.EvaluatorResult
import gr3.SoundUtils
import gr3.CondolencesUtils

class Gr3ClientController {
  def model
  def view
  def builder
  def serverService
  def simpleEvaluatorService
 // def droolsEvaluatorService
  def evaluator
  def winningsService

  private static final nf = NumberFormat.getCurrencyInstance()

  def totalizerAction = {evt = null ->
    doOutside {
      model.totalizerValue = nf.format(serverService.totalizerAction())
    }
  }

  // NB: MUST define totalizerAction closure BEFORE this method
  void mvcGroupInit(Map args) {
//    evaluator = droolsEvaluatorService
    evaluator = simpleEvaluatorService

    def timer = new javax.swing.Timer(1500, totalizerAction as ActionListener);
    timer.setInitialDelay(500);
    timer.start();

    try {
      serverService.registerAction()
    }
    catch (Exception e) {
      e.printStackTrace(System.err)
      // just in case...
      try { serverService.unRegisterAction() } catch (Exception x) { x.printStackTrace(System.err) }
      System.err.println("Could not contact Gr3Server. shutting down.")
      app.shutdown()
    }
  }

  def playAction = {evt = null ->
    doOutside {
      def lr = view.leftReel
      def mr = view.middleReel
      def rr = view.rightReel
      lr.activate()
      mr.activate()
      rr.activate()
      edt {
        view.spindle.repaint()
      }

      model.plays++

      def playedAmount = Integer.valueOf(view.playValue.selection.actionCommand)

      def pTote = serverService.playAction(playedAmount)
      model.totalizerValue = nf.format(pTote)
      model.playedValue = nf.format(playedAmount + nf.parse(model.playedValue))

      def res = evaluator.evaluate(lr, mr, rr)
      switch (res) {
        case EvaluatorResult.SMALLWIN:
        case EvaluatorResult.BIGWIN:

          model.playButtonEnabled = false

          def winnings = winningsService.evaluate(res.multiplier, playedAmount)
          model.earnings = nf.format(winnings + nf.parse(model.earnings))

          def wTote = serverService.winAction(playedAmount, winnings)
          model.totalizerValue = nf.format(wTote)

          switch (res) {
            case EvaluatorResult.SMALLWIN:
              model.message = "Congratulations! You just won: ${nf.format(winnings)}."
              SoundUtils.smallWin()
              break
            case EvaluatorResult.BIGWIN:
              model.message = "Hey BIG WINNER! You just won: ${nf.format(winnings)}."
              SoundUtils.bigWin()
              break
          }

          model.playButtonEnabled = true
          break
        default:
          model.message = CondolencesUtils.condolences()
          break
      }
    }
  }

  def exitAction = {evt = null ->
    try { serverService.unRegisterAction() } catch (Exception x) { x.printStackTrace(System.err) }
    app.shutdown()
  }

  def preferencesAction = {evt = null ->
    def (m, v, c) = createMVCGroup('PrefsPanel')
    c.postMvcGroupInit()
  }

  def aboutAction = {evt = null ->
    doOutside {
      def pane = builder.optionPane(icon: builder.imageIcon('/griffon-icon-64x64.png'),
              message: """Welcome to Gr3!

Built with Groovy/Griffon!

Built for 0.95-rc2: 2012 March 11 by bob@transentia.com.au.""")
      def dialog = pane.createDialog(app.windowManager.windows[0], "About Gr3")
      dialog.show()
    }
  }
}
