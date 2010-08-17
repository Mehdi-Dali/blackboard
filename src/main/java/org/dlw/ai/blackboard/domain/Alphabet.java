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
package org.dlw.ai.blackboard.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dlw.ai.blackboard.Blackboard;
import org.dlw.ai.blackboard.util.SystemConstants;
import org.dlw.ai.blackboard.util.UniversalContext;

/**
 * <p>
 * This class represents the knowledge that the {@link CipherLetter} has
 * <i>plaintext</i> meaning. The instance of this class has no default
 * constructor and can only be created if the strings for the <i>cipher</i>
 * letter and the <i>plaintext</i> letters are understood or assumed.
 * </p>
 * 
 * <p>
 * The {@link Alphabet} tells us that we have knowledge of its meaning in two
 * languages, the problem language (cipher) and the general dialect (english) in
 * our case.
 * </p>
 * 
 * <blockquote><i>Alphabet - "(def.) a set of letters or symbols in a fixed
 * order, used to represent the basic sounds of a language; in particular, the
 * set of letters from A to Z ... Oxford American Dictionary"</i></blockquote>
 * 
 * @author dlwhitehurst
 * 
 */
public class Alphabet extends BlackboardObject {

	/**
	 * Domain object definition, <i>never</i> set as constant. This object
	 * attribute is very important to our understanding, knowledge,
	 * intelligence, etc. because it answers the question "what is it?". And,
	 * how do our senses perceive this impression (image, sound, thought,
	 * experience, smell, touch, etc.)? Therefore, this impression(s) is
	 * different for each individual. Webster, Oxford, and or any other
	 * dictionary provides a definition for us that provides a baseline
	 * impression for our <i>knowledge</i>if we seek this impression and
	 * <i>definition</i> for our own understanding.
	 */
	protected String def;

	/**
	 * Attribute cipher letter knowledge
	 */
	private String cipherLetter;

	/**
	 * Attribute plaintext letter knowledge
	 */
	private String plainLetter;

	/**
	 * Affirmation (statements) made against this alphabet
	 */
	protected Affirmation affirmations = new Affirmation();

	/**
	 * Attribute class logger
	 */
	private final static Log log = LogFactory.getLog(Alphabet.class);

	/**
	 * No default constructor allowed
	 */
	@SuppressWarnings("unused")
	private Alphabet() {
	}

	/**
	 * Loaded constructor only, both letters required
	 * 
	 * @param cipherLetter
	 * @param plainLetter
	 */
	public Alphabet(String cipherLetter, String plainLetter) {
		if (cipherLetter.equals("") && log.isErrorEnabled()) {
			log.error(SystemConstants.EMPTY_CIPHER_LETTER_ERROR);
		}

		if (plainLetter.equals("") && log.isErrorEnabled()) {
			log.error(SystemConstants.EMPTY_PLAIN_LETTER_ERROR);
		}

		this.cipherLetter = cipherLetter;
		this.plainLetter = plainLetter;

	}

	/**
	 * @return the cipherLetter
	 */
	public String getCipherLetter() {
		return cipherLetter;
	}

	/**
	 * @param cipherLetter
	 *            the cipherLetter to set
	 */
	public void setCipherLetter(String cipherLetter) {
		this.cipherLetter = cipherLetter;
	}

	/**
	 * @return the plainLetter
	 */
	public String getPlainLetter() {
		return plainLetter;
	}

	/**
	 * @param plainLetter
	 *            the plainLetter to set
	 */
	public void setPlainLetter(String plainLetter) {
		this.plainLetter = plainLetter;
	}

	/**
	 * @return the affirmations
	 */
	public Affirmation getAffirmations() {
		return affirmations;
	}

	/**
	 * @param affirmations
	 *            the affirmations to set
	 */
	public void setAffirmations(Affirmation affirmations) {
		this.affirmations = affirmations;
	}

	/**
	 * @return the def
	 */
	public String getDef() {
		return def;
	}

	/**
	 * @param def
	 *            the def to set
	 */
	public void setDef(String def) {
		this.def = def;
	}

	public void notifyParticipants() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void register() {
		Blackboard blackboard = (Blackboard) UniversalContext
				.getApplicationContext().getBean("blackboard");
		blackboard.add(this);
	}

	@Override
	public void resign() {
		Blackboard blackboard = (Blackboard) UniversalContext
		.getApplicationContext().getBean("blackboard");
		blackboard.remove(this);
	}

}