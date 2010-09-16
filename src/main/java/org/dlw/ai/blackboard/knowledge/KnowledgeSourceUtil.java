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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dlw.ai.blackboard.Blackboard;
import org.dlw.ai.blackboard.Controller;
import org.dlw.ai.blackboard.domain.Antecedent;
import org.dlw.ai.blackboard.domain.Assertion;
import org.dlw.ai.blackboard.domain.Assumption;
import org.dlw.ai.blackboard.domain.CipherLetter;
import org.dlw.ai.blackboard.domain.Consequent;
import org.dlw.ai.blackboard.domain.Sentence;
import org.dlw.ai.blackboard.domain.Word;
import org.dlw.ai.blackboard.rule.Rule;
import org.dlw.ai.blackboard.rule.RuleFactory;
import org.dlw.ai.blackboard.rule.RuleType;
import org.dlw.ai.blackboard.util.Logger;
import org.dlw.ai.blackboard.util.ReflectionUtil;
import org.dlw.ai.blackboard.util.SentenceUtil;

/**
 * The KnowledgeSourceUtil class is used to provide static methods to stage-load
 * data into all KnowledgeSources
 * 
 * @author dlwhitehurst
 * @version 1.0.0-RC
 * 
 */
public final class KnowledgeSourceUtil {

    /**
     * Attribute commons-logging class instance
     */
    private static final Log log = LogFactory.getLog(KnowledgeSourceUtil.class);

    /**
     * Attribute class logger
     */
    private static Logger logger;

    /**
     * Hidden constructor
     */
    private KnowledgeSourceUtil() {
    }

    /**
     * Public method to load any {@link KnowledgeSource} with blackboard context
     * 
     * @param ks
     *            the {@link KnowledgeSource} being loaded
     * @param type
     *            the {@link KnowledgeSourceType} enum
     * @param controller
     *            the {@link org.dlw.ai.blackboard.Controller} of the problem
     *            domain
     * @param blackboard
     *            the {@link org.dlw.ai.blackboard.Blackboard} data structure
     * @return
     */
    public static KnowledgeSource loadContext(KnowledgeSource ks,
            Controller controller, Blackboard blackboard) {

        ks.setBlackboard(blackboard);
        ks.setController(controller);

        return ks;
    }

    /**
     * Public method to load {@link Rule} objects for any
     * {@link KnowledgeSource}
     * 
     * @param ks
     * @param type
     * @return
     */
    public static KnowledgeSource loadRules(KnowledgeSource ks,
            KnowledgeSourceType type) {

        ArrayList<Rule> rules = new ArrayList<Rule>();
        Rule rule;

        switch (type) {

        case COMMON_PREFIX_KNOWLEDGE_SOURCE:
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.COMMON_PREFIX_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case COMMON_SUFFIX_KNOWLEDGE_SOURCE:
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.COMMON_SUFFIX_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case CONSONANT_KNOWLEDGE_SOURCE: // 3
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.CONSONANT_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case DIRECT_SUBSTITUTION_KNOWLEDGE_SOURCE: // 4
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.DIRECT_SUBSTITUTION_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case DOUBLE_LETTER_KNOWLEDGE_SOURCE: // 5
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.DOUBLE_LETTER_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case LEGAL_STRING_KNOWLEDGE_SOURCE: // 6
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.LEGAL_STRING_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case LETTER_FREQUENCY_KNOWLEDGE_SOURCE: // 7
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.LETTER_FREQUENCY_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case PATTERN_MATCHING_KNOWLEDGE_SOURCE: // 8
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.PATTERN_MATCHING_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case SENTENCE_STRUCTURE_KNOWLEDGE_SOURCE: // 9
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.SENTENCE_STRUCTURE_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case SMALL_WORD_KNOWLEDGE_SOURCE: // 10
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.SMALL_WORD_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case SOLVED_KNOWLEDGE_SOURCE: // 11
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.SOLVED_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case VOWEL_KNOWLEDGE_SOURCE: // 12
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.VOWEL_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        case WORD_STRUCTURE_KNOWLEDGE_SOURCE: // 13
            rule = RuleFactory
                    .createRule(KnowledgeSourceType.WORD_STRUCTURE_KNOWLEDGE_SOURCE);
            rules.add(rule);
            ks.setRules(rules);
            break;

        default:
            // TODO - do something for sure
        }

        return ks;
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
        List<Rule> rules = ks.getRules();

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