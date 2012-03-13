import org.drools.KnowledgeBaseFactory

import org.drools.builder.KnowledgeBuilderFactory
import org.drools.builder.ResourceType

import org.drools.io.ResourceFactory

import gr3.Evaluation

class DroolsEvaluatorService {
  static final RULES = 'pokie.drl'

  final kbase

  DroolsEvaluatorService() {
    println "Drools: compiler: ${System.getProperty("drools.dialect.java.compiler")}"
    println "Drools: mvel evaluation strict mode: ${System.getProperty("drools.dialect.mvel.strict")}"

    def kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

// this will parse and compile in one step
    kbuilder.add(ResourceFactory.newClassPathResource(RULES,
            DroolsEvaluatorService.class), ResourceType.DRL);

// Check the builder for errors
    if (kbuilder.hasErrors()) {
      def errs = kbuilder.getErrors().toString()
      def msg = "Unable to compile '${RULES}': ${errs}"
      System.err.println(msg);
      throw new RuntimeException(msg);
    }

// get the compiled packages (which are serializable)
    def pkgs = kbuilder.getKnowledgePackages();

// add the packages to a knowledgebase (deploy the knowledge packages).
    kbase = KnowledgeBaseFactory.newKnowledgeBase();
    kbase.addKnowledgePackages(pkgs);
  }

  def evaluate = {lr, mr, rr ->
    def ksession = kbase.newStatelessKnowledgeSession();

    def mrm = mr.model
    int mid = mrm.size() / 2
    def le = lr.model.getElementAt(mid)
    def me = mrm.getElementAt(mid)
    def re = rr.model.getElementAt(mid)

    def e = new Evaluation(leftReelValue: le, middleReelValue: me, rightReelValue: re, special: le.toString().contains('attenzione'))

    ksession.execute(e)

    e.getResult()
  }

}
