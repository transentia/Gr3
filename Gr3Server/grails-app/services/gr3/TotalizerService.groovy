package gr3

import java.util.concurrent.atomic.AtomicInteger

class TotalizerService {

  boolean transactional = false

  private final tote = new AtomicInteger()

  def getCurrentValue = {
    tote.get()
  }

  def accumulate = {amt ->
    tote.addAndGet(amt)
  }
}
