import gr3.EvaluatorResult

class SimpleEvaluatorService {

  // simple function: a win is when all icons in the middle position are the same
  def evaluate = {lr, mr, rr ->
    // assume that all reels have the same number of icons
    def mrm = mr.model
    int mid = mrm.size() / 2
    def le = lr.model.getElementAt(mid)
    def me = mrm.getElementAt(mid)
    def re = rr.model.getElementAt(mid)

    (le == me) && (me == re) ? EvaluatorResult.SMALLWIN : EvaluatorResult.LOSE
  }
}
