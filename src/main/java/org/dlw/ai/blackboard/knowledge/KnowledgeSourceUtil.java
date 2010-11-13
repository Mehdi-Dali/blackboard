/**
 * Copyright 2010 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */
package org.dlw.ai.blackboard.knowledge;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dlw.ai.blackboard.dao.RuleSetDao;
import org.dlw.ai.blackboard.dao.hibernate.RuleSetDaoHibernate;
import org.dlw.ai.blackboard.domain.Assertion;
import org.dlw.ai.blackboard.domain.Assumption;
import org.dlw.ai.blackboard.domain.Sentence;
import org.dlw.ai.blackboard.exception.RuleSetNameNotFoundException;
import org.dlw.ai.blackboard.exception.UnknownKnowledgeSourceException;
import org.dlw.ai.blackboard.rule.Rule;
import org.dlw.ai.blackboard.rule.RuleSet;
import org.dlw.ai.blackboard.util.Logger;
import org.dlw.ai.blackboard.util.MessageConstants;
import org.dlw.ai.blackboard.util.UniversalContext;

/**
 * The KnowledgeSourceUtil class is used to provide static methods to stage-load
 * data into all KnowledgeSources. This class is not part of the API because it 
 * is only used to assist in the implementation of the example cryptogram problem.
 * 
 * @author <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version 1.0.0-RC
 * 
 */
public final class KnowledgeSourceUtil {

    private RuleSetDao dao; 
    
    /**
     * Attribute commons-logging class instance
     */
    private static final Log log = LogFactory.getLog(KnowledgeSourceUtil.class);

    /**
     * Attribute class logger
     */
    private static Logger logger;

    /**
     * Default constructor
     */
    public KnowledgeSourceUtil() {
        dao = (RuleSetDaoHibernate) UniversalContext
        .getApplicationContext().getBean("ruleSetDao");

    }

    /**
     * @param ks
     *    the {@link org.dlw.ai.blackboard.knowledge.KnowledgeSource}
     * @param name
     *  the String name
     */
    public void loadRuleSet(KnowledgeSource ks, String name) {

        /**
         * Initialize logger
         */
        logger = Logger.getInstance();
        logger.wrap(log);

        /**
         * Create a RuleSet
         */
        RuleSet ruleSet = null;
        try {
            ruleSet = dao.getRuleSetByName(name);
        } catch (RuleSetNameNotFoundException e) {
            logger.info("System error: RuleSet name provided does not exist");
            e.printStackTrace();
        } 
        
        /**
         * Set RuleSet on KnowledgeSource
         */
        ks.setRuleSet(ruleSet);
    }

    /**
     * Public method to output a response after a KnowledgeSource evaluation has
     * been made.
     * @param knowledgeSource
     *      the {@link KnowledgeSource} 
     * @param response
     *      the String response
     */
    public static void evaluationResponse(KnowledgeSource knowledgeSource,
            String response) {

        /**
         * Timestamp
         */
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());

        /**
         * Initialize logger
         */
        logger = Logger.getInstance();
        logger.wrap(log);

        logger
                .info("##############################################################################");
        logger.info("## " + knowledgeSource.toString() + ":EVALUATION "
                + currentTimestamp);
        logger
                .info("##############################################################################");

        logger.info(response);
        logger
                .info("##############################################################################");

    }

    /**
     * Public method to consider rules based on {@link org.dlw.ai.blackboard.rule.RuleType}
     * 
     * @param ks
     *  the {@link KnowledgeSource}
     * @param sentence
     *  the {@link org.dlw.ai.blackboard.domain.Sentence} object
     * @param methodMessage
     *  the String message if RuleType METHOD
     * @param conversionMessage
     *  the String message if RuleType CONVERSION
     */
    public static void considerRules(KnowledgeSource ks, Sentence sentence,
            String methodMessage, String conversionMessage) {
        List<Rule> rules = null; //ks.getRules();
/*
        for (Rule rule : rules) {
            Antecedent antecedent = rule.getAntecedent();
            Consequent consequent = rule.getConsequent();
            RuleType ruleType = rule.getRuleType();

            switch (ruleType) {

            case METHOD:
                if (ReflectionUtil.isAntecedent(antecedent
                        .getFullyQualifiedClass(), antecedent.getMethodName())) {

                    KnowledgeSourceUtil.evaluationResponse(ks,
                            methodMessage);

                    ReflectionUtil.execConsequent(consequent
                            .getFullyQualifiedClass(), consequent
                            .getMethodName());
                }
                break;

            case CONVERSION:
                List<Word> words = SentenceUtil.getWords(sentence);
                List<CipherLetter> letters;

                for (Word word : words) {
                    letters = SentenceUtil.getLetters(word);

                    for (CipherLetter letter : letters) {
                        for (Rule convRule : rules) {

                            String cipher = convRule.getBefore();
                            String plainText = convRule.getAfter();

                            if (letter.value().equals(cipher)) {
                                makeAssertion(ks, cipher, plainText);
                                Assertion assertion = new Assertion();
                                assertion.setCipherLetter(cipher);
                                assertion.setPlainLetter(plainText);
                                assertion
                                        .setReason(conversionMessage);

                                Stack<Assumption> stack = letter
                                        .getAffirmation().getStatements();
                                stack.add(assertion);
                                KnowledgeSourceUtil.evaluationResponse(ks,
                                        conversionMessage + rule.getBefore() + " = "
                                                + rule.getAfter() + ".");
                            }
                        }

                    }
                }
                break;
            }
        }
        */
    }

    /**
     * Private method to store an assertion
     * 
     * @param ks
     *  the {@link KnowledgeSource} object
     * @param cipher
     *  the String cipher letter
     *  @param plainText
     *  the String plain text letter
     */
    private static void makeAssertion(KnowledgeSource ks, String cipher, String plainText) {

        /**
         * Create and load an Assertion
         */
        Assertion assertion = new Assertion();
        assertion.setCipherLetter(cipher);
        assertion.setPlainLetter(plainText);
        assertion
                .setReason("An assertion was given to describe a direct substitution.");

        /**
         * Create a data-structure to hold Assumptions
         */
        ConcurrentLinkedQueue<Assumption> queue = new ConcurrentLinkedQueue<Assumption>();

        /**
         * Load our Assertion
         */
        queue.add(assertion);

        /**
         * Add Assumption/Assertion queue to this specific knowledge source
         */
        ks.setPastAssumptions(queue);

    }

}