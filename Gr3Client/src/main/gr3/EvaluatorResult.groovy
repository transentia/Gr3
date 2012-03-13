package gr3
public enum EvaluatorResult {
  INACTIVE(0.0F), LOSE(0.0F), BIGWIN(2.0F), SMALLWIN(1.5F), OTHER(0.0F)

  Float multiplier
  EvaluatorResult(multiplier) {
    this.multiplier = multiplier
  }

  Float getMultiplier() { return multiplier }
}
