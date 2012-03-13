class WinningsService {

  // you get back 1.5* what you put in...
  def evaluate = {mult, playedAmount->
    (Integer)(mult * playedAmount)
  }
}
